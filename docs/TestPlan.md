# Software Test Plan
## Project: N11.com E-Commerce Web Application — Test Automation

### 1. Introduction

**Purpose:** This document defines the testing strategy, scope, resources, schedule, and deliverables for the N11.com E-Commerce Web Application test automation project. The objective is to ensure the application meets functional and non-functional requirements.

**Scope:** Testing includes user registration/login, product search, product filtering, cart operations, checkout process, order history, and non-functional validations (performance, security).

**Definitions:**
- **SUT** – System Under Test (n11.com)
- **BDD** – Behavior Driven Development
- **CI/CD** – Continuous Integration / Continuous Deployment
- **POM** – Page Object Model

---

### 2. Test Items

Modules to be tested:
- Authentication Module (Login)
- Product Catalog Module (Search & Filter)
- Shopping Cart Module
- Checkout & Payment Module
- Order History Module

---

### 3. Features to be Tested

**Functional Testing:**
- Valid and invalid login scenarios (data-driven)
- Product search with valid, invalid, and empty queries
- Product filtering by price range
- Add/remove items from cart
- Increase/decrease cart item quantity
- Checkout page navigation and validation
- Order history page access

**Non-Functional Testing:**
- Browser compatibility (Chrome, Firefox)
- Basic performance validation (page load time via Navigation Timing API)
- Security validation (session timeout via cookie clearing)

---

### 4. Features Not to be Tested

- Third-party payment gateway internal logic
- Mobile native application
- Database internal performance tuning
- Actual purchase completion (checkout is validated without placing orders)

---

### 5. Test Strategy

**Test Levels:**
- System Testing
- Regression Testing (`@regression` tag)
- Smoke Testing (`@smoke` tag)

**Test Types:**
- Functional Testing
- Negative Testing
- Boundary Value Testing
- Data-Driven Testing

**Automation Strategy:**
- Selenium WebDriver 4.18.1 for UI automation
- TestNG 7.9.0 for test execution and grouping
- Cucumber 7.15.0 for BDD feature files
- Page Object Model (POM) design pattern
- Data-driven testing using Cucumber Scenario Outline + Examples
- Cross-browser testing (Chrome & Firefox)

---

### 6. Test Environment

**Hardware:**
- Minimum 8GB RAM, Windows 10/11

**Software:**
- Java 17
- Maven 3.x
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- Cucumber 7.15.0
- WebDriverManager 5.7.0
- Chrome, Firefox & Edge browsers

**Version Control:**
- Git
- GitHub repository for source control

---

### 7. CI/CD and GitHub Integration

**Repository Management:**
- All automation code is stored in GitHub.
- Feature branches are used for new features.
- Pull Requests are required before merging to main branch.

**CI Pipeline (GitHub Actions):**
- Triggers on every push and pull request.
- Steps:
  1. Checkout code
  2. Install Java 17 & Maven
  3. Install Chrome & Firefox
  4. Build project
  5. Execute TestNG test suite (headless)
  6. Generate HTML/JSON/XML test reports
  7. Upload test results as artifacts
  8. Check pass rate (minimum 80%)

**Quality Gates:**
- Build fails if any critical test fails.
- Minimum 80% test pass rate required for merge.

**Optional:**
- Nightly regression runs scheduled at 00:00 UTC.
- CI badge integrated in README.

---

### 8. Entry and Exit Criteria

**Entry Criteria:**
- Requirements finalized
- Test environment ready
- Test cases reviewed

**Exit Criteria:**
- 95% test cases executed
- No open critical defects
- Test summary report approved

---

### 9. Risk Analysis

**Technical Risks:**
- Browser driver incompatibility
- Unstable test environment
- N11.com UI changes breaking selectors

**Project Risks:**
- Delayed development delivery
- Insufficient test data

**Mitigation:**
- WebDriverManager for automatic driver version management
- Multiple CSS selector fallbacks in Page Objects
- Cookie popup dismissal handled in Hooks

---

### 10. Roles and Responsibilities

| Role | Responsibility |
|------|---------------|
| Test Lead | Approve test plan, monitor progress |
| Automation Engineer | Develop and maintain test scripts, execute regression suites |
| Developers | Fix reported defects |

---

### 11. Test Deliverables

- Software Test Plan (this document)
- Automated Test Scripts (Java + Selenium)
- Cucumber Feature Files (7 feature files)
- TestNG Execution Reports (HTML, JSON, XML)
- Defect Report (`docs/DefectReport.md`)
- Test Summary Report (`docs/TestSummaryReport.md`)
- GitHub Repository Link
- CI Pipeline Configuration File (`.github/workflows/maven.yml`)
