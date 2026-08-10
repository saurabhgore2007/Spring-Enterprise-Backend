# Day 16 — Pagination & Sorting

## 📚 Overview

Day 16 focused on Pagination and Sorting with Spring Data JPA.

## 🎯 Learning Objectives

- Understand why pagination is required
- Understand `Pageable`
- Understand `PageRequest`
- Understand `Page<T>`
- Understand sorting
- Understand pagination with Spring Data JPA

## 1. Why Pagination?

Loading every record from a large database table is inefficient.

```java
productRepository.findAll();
````

Pagination allows the application to request only a specific portion of the data.

## 2. Zero-Based Page Index

Spring Data JPA uses zero-based page indexes.

```java
PageRequest.of(0, 20);
```

```text
Page 0 → First page
Page 1 → Second page
Page 2 → Third page
```

## 3. Pageable

`Pageable` represents the pagination request.

```java
Pageable pageable = PageRequest.of(0, 20);
```

## 4. PageRequest

`PageRequest` creates a `Pageable`.

```java
PageRequest.of(2, 20);
```

means page index `2` with a page size of `20`.

## 5. Page<T>

Spring Data JPA can return:

```java
Page<Product>
```

A `Page<Product>` contains the current page's products and pagination metadata.

## 6. Getting Current Page Products

```java
List<Product> products = page.getContent();
```

`getContent()` returns the products on the current page.

## 7. Page Metadata

```java
page.getNumber();
page.getSize();
page.getTotalElements();
page.getTotalPages();
page.hasNext();
page.hasPrevious();
```

## 8. Pagination with JpaRepository

`JpaRepository` already provides:

```java
Page<Product> findAll(Pageable pageable);
```

## 9. Pagination in Service

```java
public Page<Product> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
}
```

## 10. Pagination in Controller

```java
@GetMapping
public Page<Product> getProducts(Pageable pageable) {
    return productService.getProducts(pageable);
}
```

A request such as:

```text
GET /api/products?page=0&size=20
```

can be converted by Spring into a `Pageable`.

## 11. Pagination Request Parameters

```text
?page=2&size=20
```

means:

```text
Page index = 2
Page size  = 20
```

Sorting can also be included:

```text
?page=0&size=20&sort=price,asc
```

## 12. Pagination and SQL

Conceptually:

```sql
LIMIT 20 OFFSET 40;
```

For:

```text
page = 2
size = 20
```

the offset is:

```text
OFFSET = page × size
       = 2 × 20
       = 40
```

## 13. Sorting

```java
Pageable pageable = PageRequest.of(
        0,
        20,
        Sort.by("price").ascending()
);
```

`ASC` means lowest to highest.

`DESC` means highest to lowest.

## 14. Multiple Sort Fields

```java
Sort sort = Sort.by(
        Sort.Order.asc("price"),
        Sort.Order.desc("id")
);
```

This means:

```text
1. Lowest price first
2. If prices are equal → highest ID first
```

## 15. Pagination + Sorting

```java
Pageable pageable = PageRequest.of(
        2,
        20,
        Sort.by("price").descending()
);
```

This means:

```text
Page index = 2
Page size  = 20
Sort       = price DESC
```

## 16. Pageable with Derived Queries

Pagination can also be used with derived queries:

```java
Page<Product> findByActiveTrue(Pageable pageable);
```

Spring Data combines the query condition with pagination and sorting.

## 17. Nexora Application

Pagination and sorting will be important for Nexora's product catalog.

Example:

```text
GET /api/products?page=0&size=20&sort=price,asc
```

Flow:

```text
Frontend
   ↓
HTTP Request
   ↓
Spring MVC
   ↓
Pageable
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Hibernate
   ↓
MySQL
   ↓
Page<Product>
   ↓
Frontend
```

## 🧠 Key Takeaways

```text
Pageable
    ↓
"What page, size and sort do I want?"

PageRequest
    ↓
Creates Pageable

Page<Product>
    ↓
"What did I receive?"

getContent()
    ↓
Current page's List<Product>

getTotalElements()
    ↓
Total matching records

getTotalPages()
    ↓
Total number of pages

Sort
    ↓
Controls result ordering
```