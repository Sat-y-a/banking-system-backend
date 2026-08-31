# Banking System Backend

Portfolio-safe backend reference for a mini banking system.

## Contribution scope
- Controller-Service-Repository architecture
- Customer and account management
- Deposit, withdrawal and transaction history
- Spring Security + JWT authentication/authorization
- CUSTOMER / ADMIN RBAC
- MySQL/JPA persistence
- Validation and centralized exception handling
- JUnit/Mockito testing

## Important production boundary
This is a portfolio/reference implementation, not a real banking core. A production financial platform additionally requires strong concurrency control, idempotency, double-entry accounting, immutable audit trails, encryption and key management, fraud controls, reconciliation, regulatory compliance, HA/DR and independent security testing.

See `docs/PROJECT-DOCUMENTATION.md` and the Word technical documentation for architecture and diagrams.
