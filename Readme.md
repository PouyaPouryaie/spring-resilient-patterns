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
- Maven/Gradle

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
