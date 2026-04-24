# Professional Test Case Document
## Project: N11.com E-Commerce Platform
**Version:** 2.0 (Optimized)  
**Department:** Quality Assurance  
**Date:** April 23, 2026  

---

## Test Design Technique
*   **Black Box Testing:** Used for all functional validations to verify system behavior against specifications without internal code knowledge.
*   **Boundary Value Analysis (BVA):** Applied to input fields (e.g., search keywords, quantity) and filter ranges.
*   **Positive and Negative Testing:** Scenarios designed to validate both intended use cases and graceful error handling for invalid inputs.

---

## Optimization Summary

| Metric | Previous | Optimized |
|--------|----------|-----------|
| Total Scenarios | 20 | **16** |
| Total Executions (incl. Data-Driven) | 23 | **19** |
| Modules Covered | 7 | **7** |

**Consolidation Strategy:**
*   Category + Brand filter → Single combined filter test (TC07)
*   Increase + Decrease + Remove cart → Single cart maintenance test (TC11)
*   Checkout navigation + Address validation → Single checkout test (TC12)

---

## 1. Authentication (Login) Module

**Test Scenario ID:** TS_01  
**Test Case ID:** TC01  
**Test Case Description:** Login with valid credentials  
**Test Priority:** High  
**Tags:** `@smoke` `@login` `@regression`  
**Pre-Requisite:** User must have a registered account on n11.com.  
**Post-Requisite:** User is redirected to the homepage with an active session.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Navigate to n11.com login page | URL: n11.com/giris-yap | Login page with email and password fields is displayed | TBD | Not Executed | |
| 2 | Enter valid registered email | valid_email@example.com | Email is accepted in the input field | TBD | Not Executed | |
| 3 | Enter valid account password | ValidPassword123! | Password characters are masked | TBD | Not Executed | |
| 4 | Click the "Giriş Yap" button | Click Action | System authenticates the user | TBD | Not Executed | |
| 5 | Verify navigation and profile | - | User is redirected to homepage; "Hesabım" link is visible | TBD | Not Executed | |

---

**Test Scenario ID:** TS_02  
**Test Case ID:** TC02  
**Test Case Description:** Login with invalid credentials (Data-Driven)  
**Test Priority:** Medium  
**Tags:** `@data-driven` `@login` `@regression`  
**Pre-Requisite:** Browser is open on the login page.  
**Post-Requisite:** User remains on the login page with an error message.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Enter email from data set | See Examples table | Email is entered in the field | TBD | Not Executed | |
| 2 | Enter password from data set | See Examples table | Password characters are masked | TBD | Not Executed | |
| 3 | Click the "Giriş Yap" button | Click Action | Error message is displayed or user remains on login page | TBD | Not Executed | |

**Data Set:**

| email | password | Expected Behavior |
|-------|----------|------------------|
| valid_email | WrongPassword123! | Wrong password error |
| invalid_user_test@invalid.com | AnyPassword123! | Invalid email error |
| invalid_user_test@invalid.com | WrongPassword123! | Invalid credentials error |
| test@ | 12345 | Email format validation error |

---

**Test Scenario ID:** TS_03  
**Test Case ID:** TC03  
**Test Case Description:** Login with empty fields  
**Test Priority:** Medium  
**Tags:** `@login` `@regression`  
**Pre-Requisite:** Browser is open on the login page.  
**Post-Requisite:** User remains on the login page with required field validation.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Leave email field empty | (empty) | Field remains blank | TBD | Not Executed | |
| 2 | Leave password field empty | (empty) | Field remains blank | TBD | Not Executed | |
| 3 | Click the "Giriş Yap" button | Click Action | Required field error or validation message is displayed | TBD | Not Executed | |

---

## 2. Product Search Module

**Test Scenario ID:** TS_04  
**Test Case ID:** TC04  
**Test Case Description:** Search with valid keyword  
**Test Priority:** High  
**Tags:** `@smoke` `@search` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Search results page is displayed with relevant products.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Type a valid product name in the search bar | "laptop" | Keyword is visible in the search box | TBD | Not Executed | |
| 2 | Click the search icon or press Enter | Click/Enter | Search query is submitted | TBD | Not Executed | |
| 3 | Verify search results page loads | - | Results page displays products related to "laptop" | TBD | Not Executed | |
| 4 | Verify at least 1 product is listed | - | Product count >= 1 | TBD | Not Executed | |

---

**Test Scenario ID:** TS_05  
**Test Case ID:** TC05  
**Test Case Description:** Search with invalid / nonsense keyword  
**Test Priority:** Low  
**Tags:** `@search` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** No results or empty state is displayed.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Type a nonsense keyword in the search bar | "xyzxyzxyzabc123" | Keyword is visible in the search box | TBD | Not Executed | |
| 2 | Submit the search | Click/Enter | Search query is submitted | TBD | Not Executed | |
| 3 | Verify no results or empty state | - | "Sonuç bulunamadı" message or empty product list | TBD | Not Executed | |

---

**Test Scenario ID:** TS_06  
**Test Case ID:** TC06  
**Test Case Description:** Search with empty query  
**Test Priority:** Low  
**Tags:** `@search` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** User stays on homepage or sees appropriate empty state.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Submit search with empty query | (empty) | Search is submitted without a keyword | TBD | Not Executed | |
| 2 | Verify behavior | - | User remains on homepage or sees empty search results | TBD | Not Executed | |

---

## 3. Product Filtering & Sorting Module

**Test Scenario ID:** TS_07  
**Test Case ID:** TC07  
**Test Case Description:** Filter search results by category and brand (Combined)  
**Test Priority:** High  
**Tags:** `@smoke` `@filter` `@regression`  
**Pre-Requisite:** User has searched for "ayakkabı" and results are displayed.  
**Post-Requisite:** Results are filtered by both category and brand.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Select category from sidebar filter | Category: "Ayakkabı" | Results are filtered to show only "Ayakkabı" items | TBD | Not Executed | |
| 2 | Verify results are updated | - | Page reflects the applied category filter | TBD | Not Executed | |
| 3 | Select brand from brand filter | Brand: "Adidas" | Results are further refined to the selected brand | TBD | Not Executed | |
| 4 | Verify results are updated | - | Page reflects both category and brand filters | TBD | Not Executed | |
| 5 | Verify at least 1 product is visible | - | At least one matching product is displayed | TBD | Not Executed | |

---

**Test Scenario ID:** TS_08  
**Test Case ID:** TC08  
**Test Case Description:** Sort search results by descending price  
**Test Priority:** Medium  
**Tags:** `@filter` `@regression`  
**Pre-Requisite:** User has searched for "ayakkabı" and results are displayed.  
**Post-Requisite:** Products are listed in descending order of price.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Click the sorting dropdown menu | Click Action | Sorting options (Newest, Price, etc.) are displayed | TBD | Not Executed | |
| 2 | Select "Azalan Fiyat" (High to Low) | Select Action | System re-sorts the current product list | TBD | Not Executed | |
| 3 | Verify results are updated | - | Results page is refreshed with new sort order | TBD | Not Executed | |

---

## 4. Shopping Cart Module

**Test Scenario ID:** TS_09  
**Test Case ID:** TC09  
**Test Case Description:** Add product to cart from product detail page  
**Test Priority:** High  
**Tags:** `@smoke` `@cart` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Product is added to the user's cart.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Search for "mouse" and open first product | "mouse" | Product detail page is displayed | TBD | Not Executed | |
| 2 | Click "Sepete Ekle" button | Click Action | Success notification or cart badge update is observed | TBD | Not Executed | |
| 3 | Verify add-to-cart confirmation | - | Confirmation message or cart count increment | TBD | Not Executed | |

---

**Test Scenario ID:** TS_10  
**Test Case ID:** TC10  
**Test Case Description:** Validate cart contents after adding product  
**Test Priority:** High  
**Tags:** `@smoke` `@cart` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Cart contains the added product with correct details.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Search for "klavye" and open first product | "klavye" | Product detail page is displayed | TBD | Not Executed | |
| 2 | Add product to cart | Click Action | Product is added to cart | TBD | Not Executed | |
| 3 | Navigate to cart page | Click "Sepetim" | Cart page is displayed | TBD | Not Executed | |
| 4 | Verify cart contains at least 1 item | - | Product is visible in the cart list | TBD | Not Executed | |

---

**Test Scenario ID:** TS_11  
**Test Case ID:** TC11  
**Test Case Description:** Update and remove cart item (Combined)  
**Test Priority:** High  
**Tags:** `@cart` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Cart operations (increase, decrease, remove) are validated.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Search for "mouse" and open first product | "mouse" | Product detail page is displayed | TBD | Not Executed | |
| 2 | Add product to cart and navigate to cart | Click Actions | Cart page displays the product | TBD | Not Executed | |
| 3 | Click (+) to increase quantity | Click "+" | Quantity increases by 1 | TBD | Not Executed | |
| 4 | Verify quantity increased | - | New quantity > previous quantity | TBD | Not Executed | |
| 5 | Click (-) to decrease quantity | Click "-" | Quantity decreases by 1 | TBD | Not Executed | |
| 6 | Verify quantity decreased | - | New quantity < previous quantity | TBD | Not Executed | |
| 7 | Remove item from cart | Click "Sil" | Product is removed from the cart | TBD | Not Executed | |
| 8 | Verify cart is empty or has fewer items | - | "Sepetiniz Boş" message or updated item count | TBD | Not Executed | |

---

## 5. Checkout Module

**Test Scenario ID:** TS_12  
**Test Case ID:** TC12  
**Test Case Description:** Proceed to checkout and validate login/address step (Combined)  
**Test Priority:** High  
**Tags:** `@smoke` `@checkout` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Checkout page displays address or login section.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Search for "defter" and open first product | "defter" | Product detail page is displayed | TBD | Not Executed | |
| 2 | Add product to cart and navigate to cart | Click Actions | Cart page displays the product | TBD | Not Executed | |
| 3 | Click "Ödemeye Geç" button | Click Action | User is redirected to checkout workflow | TBD | Not Executed | |
| 4 | Verify checkout or login page | - | Checkout/login page URL is displayed | TBD | Not Executed | |
| 5 | Verify address or login section | - | Address form or login prompt is visible | TBD | Not Executed | |

---

## 6. Order History Module

**Test Scenario ID:** TS_13  
**Test Case ID:** TC13  
**Test Case Description:** Access and validate order history page  
**Test Priority:** Medium  
**Tags:** `@smoke` `@orderHistory` `@regression`  
**Pre-Requisite:** User has valid login credentials.  
**Post-Requisite:** Past orders or "No orders found" message is displayed.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Navigate to login page and log in | Valid credentials | User is logged in and redirected to homepage | TBD | Not Executed | |
| 2 | Click on "Hesabım" menu | Click Action | Account menu options are displayed | TBD | Not Executed | |
| 3 | Select "Siparişlerim & İadelerim" | Click Action | Redirected to order history URL | TBD | Not Executed | |
| 4 | Verify page content | - | Past orders list or empty state placeholder is visible | TBD | Not Executed | |

---

## 7. Non-Functional Testing Module

**Test Scenario ID:** TS_14  
**Test Case ID:** TC14  
**Test Case Description:** Validate homepage load time  
**Test Priority:** Low  
**Tags:** `@performance` `@nonfunctional` `@regression`  
**Pre-Requisite:** Browser cache is cleared.  
**Post-Requisite:** Homepage is fully rendered within time limit.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Access n11.com URL | URL: n11.com | Homepage loads within acceptable time | TBD | Not Executed | |
| 2 | Measure page load time | Navigation Timing API | Load time < 10,000 ms | TBD | Not Executed | |

---

**Test Scenario ID:** TS_15  
**Test Case ID:** TC15  
**Test Case Description:** Validate search results page load time  
**Test Priority:** Low  
**Tags:** `@performance` `@nonfunctional` `@regression`  
**Pre-Requisite:** User is on the n11.com homepage.  
**Post-Requisite:** Search results page is loaded within time limit.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Navigate to homepage | URL: n11.com | Homepage is loaded | TBD | Not Executed | |
| 2 | Search for "laptop" | "laptop" | Search results page is displayed | TBD | Not Executed | |
| 3 | Measure page load time | Navigation Timing API | Load time < 10,000 ms | TBD | Not Executed | |

---

**Test Scenario ID:** TS_16  
**Test Case ID:** TC16  
**Test Case Description:** Validate session expiration behavior  
**Test Priority:** Medium  
**Tags:** `@security` `@nonfunctional` `@regression`  
**Pre-Requisite:** User is logged in with an active session.  
**Post-Requisite:** User is logged out and redirected.  

| Step No | Action | Input | Expected Output | Actual Output | Test Result | Test Comments |
|---------|--------|-------|-----------------|---------------|-------------|---------------|
| 1 | Log in with valid credentials | Valid email/password | User is authenticated | TBD | Not Executed | |
| 2 | Clear all browser session cookies | System Action | Session tokens are invalidated | TBD | Not Executed | |
| 3 | Refresh the current page | Refresh | User session is no longer recognized | TBD | Not Executed | |
| 4 | Verify redirection | - | System redirects to login or guest homepage | TBD | Not Executed | |

---
**End of Document**
