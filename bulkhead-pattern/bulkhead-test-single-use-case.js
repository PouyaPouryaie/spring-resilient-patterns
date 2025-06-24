import http from 'k6/http';
import { sleep } from 'k6';

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
    http.get('http://localhost:8080/products/1');
    sleep(1);
}

export function productsTest() {
    http.get('http://localhost:8080/products');
    sleep(1);
}

/* for test:
docker run --rm --network=host -v $(pwd):/scripts -e SCENARIO_NAME=perf_test -i grafana/k6:1.0.0 run /scripts/bulkhead-test-single-use-case.js
 */