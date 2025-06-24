import http from 'k6/http';
import { sleep } from 'k6';

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
    http.get('http://localhost:8080/products/1');
    sleep(1);
}

export function productsTest() {
    http.get('http://localhost:8080/products');
    sleep(1);
}