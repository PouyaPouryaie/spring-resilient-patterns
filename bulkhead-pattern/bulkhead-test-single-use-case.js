import http from 'k6/http';
import { check, sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

const scenarioName = __ENV.SCENARIO_NAME || 'perf_test';

export let options = {
    scenarios: {
        [scenarioName]: getScenarioConfig(scenarioName),
    },
};

function getScenarioConfig(name) {
    switch (name) {
        case 'perf_test':
            return {
                executor: 'constant-vus',
                vus: 15,
                duration: '30s',
                startTime: '0s',
                exec: 'perfTest',
            };
        case 'products':
            return {
                executor: 'constant-vus',
                vus: 5,
                duration: '20s',
                startTime: '10s',
                exec: 'productsTest',
            };
        default:
            throw new Error(`Unknown scenario: ${name}`);
    }
}

export function perfTest() {

    const randomUUID = uuidv4();
    const params = {
        headers: {
            'Content-Type': 'application/json',
            'X-UUID': randomUUID,
        },
    };

    http.get('http://localhost:8080/products/1', params);
    check(res, {
        'is status 200': (r) => r.status === 200,
        'Response header exists: X-UUID': (r) => {
            return 'X-UUID' in r.headers;
        },
        'Response header matches sent UUID': (r) => {
            // Check if the value of the 'x-request-id' header matches the UUID we sent
            return r.headers['X-UUID'] === randomUUID;
        },
    });
    sleep(1);
}

export function productsTest() {

    const randomUUID = uuidv4();
    const params = {
        headers: {
            'Content-Type': 'application/json',
            'X-UUID': randomUUID,
        },
    };

    http.get('http://localhost:8080/products', params);
    check(res, {
        'is status 200': (r) => r.status === 200,
        'Response header exists: X-UUID': (r) => {
            return 'X-UUID' in r.headers;
        },
        'Response header matches sent UUID': (r) => {
            // Check if the value of the 'x-request-id' header matches the UUID we sent
            return r.headers['X-UUID'] === randomUUID;
        },
    });
    sleep(1);
}

/* for test:
docker run --rm --network=host -v $(pwd):/scripts -e SCENARIO_NAME=perf_test -i grafana/k6:1.0.0 run /scripts/bulkhead-test-single-use-case.js
 */