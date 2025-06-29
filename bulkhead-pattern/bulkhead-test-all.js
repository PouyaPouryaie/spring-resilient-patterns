import http from 'k6/http';
import { check, sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export let options = {
    scenarios: {
        perf_test: {
            executor: 'constant-vus',
            vus: 15,
            duration: '30s',
            startTime: '0s',
            exec: 'perfTest', // Run this function
        },
        products: {
            executor: 'constant-vus',
            vus: 5,
            duration: '20s',
            startTime: '10s',
            exec: 'productsTest', // Run this function
        },
    },
};

export function perfTest() {

    const randomUUID = uuidv4();
    const params = {
        headers: {
            'Content-Type': 'application/json',
            'X-UUID': randomUUID,
        },
    };

    const res = http.get('http://localhost:8080/products/1', params);
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