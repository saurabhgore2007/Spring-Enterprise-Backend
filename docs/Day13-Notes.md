# 📘 Day 13 README (Spring Data JPA Queries)

# 📅 Day 13 - Spring Data JPA Queries & Performance Optimization

## 📚 Topics Covered

- Spring Data JPA Repository
- Derived Query Methods
- Repository Internals
- Query Method Naming Rules
- JPQL (Java Persistence Query Language)
- @Query Annotation
- Named Parameters (@Param)
- Native SQL Queries
- N+1 Query Problem
- JOIN FETCH
- Query Optimization
- JPQL vs SQL
- Hibernate Metadata
- Entity Mapping Internals

---

## 🎯 Learning Objectives

- Understand how Spring Data JPA generates repository implementations.
- Create derived query methods without writing SQL.
- Write custom JPQL queries using @Query.
- Pass parameters using @Param.
- Execute native SQL when required.
- Understand the N+1 Query Problem.
- Solve performance issues using JOIN FETCH.
- Learn Hibernate Metadata and JPQL translation.
- Follow query optimization best practices.

---

## 🧠 Key Concepts

### Derived Query Methods

```java
Product findByName(String name);

List<Product> findByPriceGreaterThan(Double price);

List<Product> findByCategoryName(String category);
````

Spring generates the implementation automatically.

---

### JPQL

```java
@Query("""
SELECT p
FROM Product p
WHERE p.price > :price
""")
List<Product> findPremiumProducts(@Param("price") Double price);
```

JPQL works with:

* Entity Classes
* Java Fields

Not Database Tables.

---

### Native SQL

```java
@Query(
value = """
SELECT *
FROM products
WHERE product_price > :price
""",
nativeQuery = true
)
```

Use only when database-specific SQL is required.

---

### N+1 Query Problem

Without JOIN FETCH

1 Query

*

N Queries

=

N+1 Queries

---

### JOIN FETCH

```java
@Query("""
SELECT p
FROM Product p
JOIN FETCH p.category
""")
List<Product> findAllProductsWithCategory();
```

Loads related entities in a single query.

---

### JPQL Internals

Hibernate creates Metadata during application startup.

Example Mapping

Product

↓

products table

price

↓

product_price column

JPQL

↓

Hibernate Metadata

↓

SQL

↓

Database

---

## 📌 Best Practices

* Use Derived Queries for simple searches.
* Use JPQL for medium complexity.
* Use Native SQL only when necessary.
* Avoid the N+1 Query Problem.
* Use JOIN FETCH only when related data is required.
* Fetch only the required data.
* Prefer readable repository methods.

---

## 🔥 Day 13 Summary

* Learned Spring Data JPA query generation.
* Understood repository implementation.
* Mastered JPQL and @Query.
* Used Named Parameters.
* Learned Native SQL.
* Solved the N+1 Query Problem.
* Used JOIN FETCH.
* Understood Hibernate Metadata.
* Learned query optimization techniques.

---

## 🚀 Technologies

* Java
* Spring Boot
* Spring Data JPA
* Hibernate ORM
* MySQL

---
## 🖼️ Visual Overview

![Day13-SpringDataJpa](../diagrams/Day13-SpringDataJpa.png)