# Resilient Pattern for Microservices

## Need for Resiliency
Microservices are distributed in nature. When you work with distributed systems, always remember this number one rule – anything could happen. <rb>
We might be dealing with network issues, service unavailability, application slowness etc. <br> 
An issue with one system might affect another system behavior/performance. Dealing with any such unexpected failures/network issues could be difficult to solve.

Ability of the system to recover from such failures and remain functional makes the system more resilient. <br> 
It also avoids any cascading failures to the downstream services.

## Bulkhead Pattern
A ship is split into small multiple compartments using Bulkheads. Bulkheads are used to seal parts of the ship to prevent entire ship from sinking in case of flood. <br>
Similarly failures should be expected when we design software. <br>
The application should be split into multiple components and resources should be isolated in such a way that failure of one part is not affecting the other.