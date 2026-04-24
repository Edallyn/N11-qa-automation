# N11.com E2E Test Automation Project

![CI](https://github.com/YOUR_USERNAME/YOUR_REPO_NAME/actions/workflows/maven.yml/badge.svg)

This repository contains a comprehensive end-to-end (E2E) test automation framework for the N11.com e-commerce platform. The project is designed to ensure the quality and stability of core user journeys through automated functional and non-functional testing.

---

## Project Scope

The project focuses on validating critical e-commerce functionalities to ensure a seamless user experience. The primary modules under test include:
*   **User Lifecycle:** Secure authentication and account-related navigations.
*   **Product Journey:** Discovery through search, refinement via filtering, and sorting.
*   **Commerce Flow:** Shopping cart management and checkout navigation.

**Out of Scope:** Actual payment processing and financial transactions are excluded from the current testing scope to maintain data security and focus on platform stability.

---

## QA Deliverables

The following artifacts have been produced as part of this project's quality assurance process:
*   **[Test Plan](docs/TestPlan.md):** Defines the overall strategy, objectives, and test environment.
*   **[Test Case Document](docs/ProfessionalTestCaseDocument.md):** Detailed manual test cases covering positive and negative scenarios (Optimized Suite).
*   **[Defect Report](docs/DefectReport.md):** Tracking of identified issues and their resolution status.
*   **[Test Summary Report](docs/FinalTestSummaryReport.md):** Final execution results and release readiness assessment.
*   **Automated Test Framework:** A modular, BDD-based Selenium framework for continuous regression testing.

---

## Test Coverage (Optimized)

The automation suite provides high coverage across 16 optimized scenarios:
*   **Authentication:** Valid/Invalid logins and empty field validation.
*   **Product Search:** Keyword-based discovery and empty/invalid state handling.
*   **Filtering & Sorting:** Combined category-brand filtering and price-based sorting.
*   **Cart Operations:** Product addition, quantity updates (+/-), and item removal.
*   **Checkout Navigation:** Transitioning from cart to address/payment sections.
*   **Order History:** Post-login access to historical order data.
*   **Non-Functional:** Page load time performance (Homepage & Search) and session security.

---

## Technologies Used

| Tool | Version | Purpose |
|------|----------|-------|
| **Java** | 17 | Core programming language |
| **Maven** | 3.x | Project build and dependency management |
| **Selenium WebDriver** | 4.18.1 | Web UI automation |
| **Cucumber** | 7.15.0 | Behavior-Driven Development (BDD) |
| **TestNG** | 7.9.0 | Test execution and reporting structure |
| **ExtentReports** | 5.1.1 | Advanced HTML reporting |
| **GitHub Actions** | - | CI/CD pipeline integration |

---

## Configuration & Execution

### Prerequisites
*   Java 17+
*   Maven 3.6+
*   Chrome, Firefox, or Edge browser installed

### Local Execution
1.  Configure credentials in `src/test/resources/config.properties`.
2.  Run all tests:
    ```bash
    mvn clean test
    ```
3.  Run specific tags:
    ```bash
    mvn clean test -Dcucumber.filter.tags="@smoke"
    ```

### Reporting
After execution, detailed reports are available at:
`target/cucumber-reports/cucumber-html-report.html`

---

## CI/CD Integration
The project utilizes **GitHub Actions** for continuous quality validation. Every push and pull request triggers a headless execution of the regression suite, ensuring that new changes do not introduce regressions (Quality Gate: 80% Pass Rate).

---

## License
This project is developed for educational and academic purposes as a demonstration of professional software testing standards.
