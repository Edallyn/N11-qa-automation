# Defect Report
## Project: N11.com E-Commerce Test Automation

---

### Defect Report Template

| Field | Description |
|-------|-------------|
| **Defect ID** | Unique identifier (e.g., BUG-001) |
| **Title** | Short description of the defect |
| **Module** | Affected module (Login, Search, Cart, etc.) |
| **Severity** | Critical / High / Medium / Low |
| **Priority** | P1 / P2 / P3 / P4 |
| **Status** | Open / In Progress / Fixed / Closed / Reopened |
| **Reported By** | Tester name |
| **Reported Date** | Date of report |
| **Environment** | Browser, OS, Java version |
| **Steps to Reproduce** | Numbered steps |
| **Expected Result** | What should happen |
| **Actual Result** | What actually happened |
| **Screenshot/Log** | Attached evidence |

---

### Reported Defects

#### BUG-001: Cookie Consent Popup Blocks UI Interactions
| Field | Value |
|-------|-------|
| **Module** | All Modules |
| **Severity** | Medium |
| **Priority** | P2 |
| **Status** | Fixed |
| **Environment** | Chrome 124, Windows 11 |
| **Steps to Reproduce** | 1. Navigate to n11.com<br>2. Observe cookie consent popup |
| **Expected Result** | Tests should run without popup interference |
| **Actual Result** | Cookie popup blocks clickable elements |
| **Resolution** | Added JavaScript-based popup dismissal in `Hooks.java` and `HomePage.java` |

---

#### BUG-002: Search Button Click Fails Due to Overlay
| Field | Value |
|-------|-------|
| **Module** | Product Catalog (Search) |
| **Severity** | High |
| **Priority** | P1 |
| **Status** | Fixed |
| **Environment** | Chrome 124, Windows 11 |
| **Steps to Reproduce** | 1. Navigate to homepage<br>2. Type search keyword<br>3. Click search button |
| **Expected Result** | Search results page should load |
| **Actual Result** | Click intercepted by overlay element |
| **Resolution** | Bypassed by navigating directly to search URL (`n11.com/arama?q=keyword`) |

---

#### BUG-003: Product Opens in New Tab, Losing Driver Context
| Field | Value |
|-------|-------|
| **Module** | Product Catalog |
| **Severity** | Medium |
| **Priority** | P2 |
| **Status** | Fixed |
| **Environment** | Chrome 124, Windows 11 |
| **Steps to Reproduce** | 1. Search for a product<br>2. Click on first product link |
| **Expected Result** | Product detail page opens in same context |
| **Actual Result** | Product opens in new tab, Selenium loses context |
| **Resolution** | Added window handle switching logic in `CartSteps.java` |

---

#### BUG-004: Add-to-Cart Button Not Found with Standard Selector
| Field | Value |
|-------|-------|
| **Module** | Shopping Cart |
| **Severity** | High |
| **Priority** | P1 |
| **Status** | Fixed |
| **Environment** | Chrome 124, Windows 11 |
| **Steps to Reproduce** | 1. Navigate to product detail page<br>2. Click "Sepete Ekle" button |
| **Expected Result** | Product added to cart |
| **Actual Result** | Element not found exception |
| **Resolution** | Added fallback XPath selectors and `jsClick` in `ProductPage.java` |

---

#### BUG-005: Password Field Visibility Timeout with Specific Email Format
| Field | Value |
|-------|-------|
| **Module** | Authentication |
| **Severity** | Medium |
| **Priority** | P2 |
| **Status** | Fixed |
| **Environment** | Chrome 147, Windows 11 |
| **Steps to Reproduce** | 1. Navigate to login page<br>2. Enter email "test@" (invalid format)<br>3. Observe password field visibility |
| **Expected Result** | Password field should appear or inline validation should trigger |
| **Actual Result** | TimeoutException: Waiting for visibility of element located by By.id: password |
| **Resolution** | Investigate if n11.com hides password field for certain invalid email formats and adjust `LoginPage.java` logic to handle optional password field visibility. |

---

### Defect Summary

| Severity | Total | Open | Fixed | Closed |
|----------|-------|------|-------|--------|
| Critical | 0 | 0 | 0 | 0 |
| High | 2 | 0 | 2 | 0 |
| Medium | 3 | 0 | 3 | 0 |
| Low | 0 | 0 | 0 | 0 |
| **Total** | **5** | **0** | **5** | **0** |
