# Resilient Patterns for Microservices

This project demonstrates common **resilience patterns** used in microservices architecture to make systems more fault-tolerant and stable. Each pattern is implemented as an individual, self-contained module.

## 📌 Why Resiliency Matters

In a distributed system, failures are inevitable. Network latency, service downtime, or unexpected errors in one service can impact the performance or availability of others. Without safeguards, these issues can cascade through the system, causing widespread failures.

To build robust and reliable microservices, we use **resiliency patterns** that help services:

- Stay functional even when parts of the system fail
- Isolate faults to prevent cascading failures
- Recover gracefully from transient issues

---

## ✅ Implemented Patterns

### 1. Bulkhead Pattern

**Concept:**  
Inspired by ship design, where compartments (bulkheads) prevent flooding from spreading, the Bulkhead pattern isolates resources in software to limit the impact of a failure.

**Goal:**  
Prevent one part of the system from exhausting all resources and bringing down other parts.

**Implementation:**
- This module contains two services: `product-service` and `rating-service`.
- The `product-service` calls the `rating-service` to retrieve product ratings.
- The `rating-service` is intentionally slowed down to simulate a performance issue.
- Without bulkheading, `product-service` becomes slow due to blocked threads.
- With bulkheading applied, the slow response from `rating-service` won’t affect the whole `product-service`.

**Note**:
- In Resilience4j, the @Bulkhead annotation's `type` attribute determines which kind of isolation strategy is applied to protect your resource:
  - There are two types: `Bulkhead.Type.SEMAPHORE` and `Bulkhead.Type.THREADPOOL`
  - When to Use Each
    - Use SEMAPHORE (Default type):
      - For fast, local logic 
      - In low-latency environments 
      - When thread overhead is a concern (since it uses the same calling thread)
    - Use THREADPOOL:
      - For remote service calls (REST APIs, DBs)
      - When you want to offload execution to a different thread 
      - For timeouts and fallbacks in parallel execution

**Test:**
- Download k6 image: `docker pull grafana/k6:1.0.0`
- Run test: `docker run --rm --network=host -v $(pwd):/scripts -e SCENARIO_NAME=perf_test -i grafana/k6:1.0.0 run /scripts/bulkhead-test.js`

---

## 🚧 Upcoming Patterns

The following patterns will be added soon:

- Circuit Breaker
- Retry
- Rate Limiter
- Timeout

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Resilience4j
- Maven
- Docker
- JS and K6

---

## 📂 Project Structure
```
resilient-patterns/
├── bulkhead/
│ ├── product-service/
│ └── rating-service/
├── circuit-breaker/ (coming soon)
├── retry/ (coming soon)
├── rate-limiter/ (coming soon)
└── timeout/ (coming soon)
```
---

## 📄 License

This project is licensed under the MIT License.

---

## 🙌 Contributions

Feel free to open issues or submit pull requests if you'd like to contribute to this project or suggest improvements!

---
check this link: https://www.vinsguru.com/bulkhead-pattern/