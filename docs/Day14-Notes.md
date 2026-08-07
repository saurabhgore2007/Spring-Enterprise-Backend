# 📘 Day 14 — Transaction Management

## 🎯 Objective

Understand how Spring Boot manages database transactions using `@Transactional`, Spring's transaction proxy, Hibernate's Persistence Context, flushing, committing, rollback, propagation, and isolation levels.

---

## 1. What is a Transaction?

A **transaction** is a group of database operations treated as **one logical unit of work**.

```text
BEGIN
   ↓
Operation 1
   ↓
Operation 2
   ↓
Operation 3
   ↓
COMMIT
```

If something fails:

```text
BEGIN
   ↓
Operation 1
   ↓
Operation 2
   ↓
ERROR
   ↓
ROLLBACK
```

---

# 2. ACID Properties

Transactions follow four important properties:

### Atomicity

All operations succeed or all are rolled back.

```text
Order + Payment + Stock
        ↓
   All succeed ✅
        OR
   All rollback ❌
```

### Consistency

The database moves from one valid state to another valid state.

### Isolation

Concurrent transactions should not incorrectly interfere with each other.

### Durability

Once committed, the changes survive failures.

---

# 3. `@Transactional`

Spring provides:

```java
@Transactional
```

to define a transaction boundary.

Example:

```java
@Service
public class OrderService {

    @Transactional
    public void placeOrder() {

        createOrder();
        updateStock();
        savePayment();

    }
}
```

Conceptually:

```text
Spring Proxy
     ↓
BEGIN
     ↓
placeOrder()
     ↓
Flush
     ↓
COMMIT
```

If an unchecked exception occurs:

```text
Exception
   ↓
ROLLBACK
```

---

# 4. Spring Transaction Proxy

`@Transactional` is normally handled through a **Spring proxy**.

```text
Controller
    ↓
Proxy
    ↓
BEGIN TRANSACTION
    ↓
Service Method
    ↓
COMMIT / ROLLBACK
```

The proxy executes transaction-related logic before and after the actual method.

---

# 5. Transaction Propagation

Propagation defines what happens when one transactional method calls another transactional method.

### REQUIRED

Default propagation.

```text
T1
 ↓
Service A
 ↓
Service B
```

Service B joins the existing transaction.

### REQUIRES_NEW

Suspends the current transaction and creates a new one.

```text
T1
 ↓
Service A
 ↓
T2 → Service B
```

The two transactions can commit/rollback independently.

### SUPPORTS

Uses an existing transaction if one exists; otherwise runs without a transaction.

### MANDATORY

Requires an existing transaction.

### NESTED

Creates a nested transaction/savepoint where supported.

---

# 6. Isolation Levels

Isolation controls how concurrent transactions see database changes.

| Isolation        | Dirty Read | Non-Repeatable Read | Phantom Read* |
| ---------------- | ---------: | ------------------: | ------------: |
| READ_UNCOMMITTED |          ❌ |                   ❌ |             ❌ |
| READ_COMMITTED   |          ✅ |                   ❌ |             ❌ |
| REPEATABLE_READ  |          ✅ |                   ✅ |            ❌* |
| SERIALIZABLE     |          ✅ |                   ✅ |             ✅ |

### READ_UNCOMMITTED

A transaction may see uncommitted changes from another transaction.

### READ_COMMITTED

Only committed data is visible.

### REPEATABLE_READ

Repeated reads of the same row remain consistent within the transaction.

### SERIALIZABLE

Provides the strictest isolation and can significantly reduce concurrency because transactions may block each other.

> `*` The SQL standard allows phantom reads at `REPEATABLE_READ`, while MySQL InnoDB prevents many phantom scenarios through MVCC and locking mechanisms.

---

# 7. Dirty Read

T1 changes data but hasn't committed.

```text
T1 → Price = 45000
       ↓
   NOT COMMITTED

T2 → reads 45000
```

If T1 rolls back, T2 read data that never became committed.

---

# 8. Non-Repeatable Read

T1 reads:

```text
Price = 50000
```

T2 changes and commits:

```text
50000 → 45000
```

T1 reads again and gets:

```text
45000
```

The same read produced different results.

---

# 9. Phantom Read

T1 executes:

```sql
SELECT * FROM product WHERE price > 1000;
```

Result:

```text
Laptop
Phone
```

T2 inserts another matching row.

T1 repeats the query and may see:

```text
Laptop
Phone
Tablet 👻
```

The newly appearing row is a **phantom row**.

---

# 10. Persistence Context + Transactions

Hibernate maintains a Persistence Context containing managed entities.

```text
Database
    ↓
Hibernate
    ↓
Persistence Context
    ↓
Managed Entity
```

If an existing entity is loaded:

```java
Product product = repository.findById(1L).get();

product.setPrice(50000);
```

`save()` is generally unnecessary because Hibernate's **dirty checking** detects the modification.

---

# 11. Dirty Checking

Hibernate compares the managed entity's current state with its original state.

```text
Original

price = 45000
stock = 10

       ↓

Modified

price = 50000
stock = 20
```

At flush, Hibernate can generate:

```sql
UPDATE product
SET price = 50000,
    stock = 20
WHERE id = 1;
```

It does not execute an UPDATE after every setter.

---

# 12. Flush vs Commit

### Flush

Synchronizes pending changes from the Persistence Context to the database by executing SQL.

```text
Persistence Context
        ↓
      FLUSH
        ↓
     SQL
```

### Commit

Successfully finishes the transaction and makes its changes permanent.

```text
SQL
 ↓
COMMIT
 ↓
Permanent
```

Therefore:

```text
FLUSH ≠ COMMIT
```

A flushed change can still be rolled back.

---

# 13. Flush Timing

With the normal `AUTO` flush behavior, Hibernate may flush:

* Before transaction commit
* Before certain queries when pending changes could affect their results
* When explicitly requested with:

```java
entityManager.flush();
```

Hibernate does **not** simply flush after every Java statement.

---

# 14. New Entity vs Managed Entity

### New Entity

```java
Product product = new Product();
product.setName("Laptop");
```

This is just a normal Java object.

It isn't automatically persisted merely because `@Transactional` exists.

You need:

```java
productRepository.save(product);
```

or:

```java
entityManager.persist(product);
```

---

### Existing Managed Entity

```java
Product product =
    productRepository.findById(1L).orElseThrow();

product.setPrice(50000);
```

No `save()` is normally required.

```text
findById()
    ↓
Managed Entity
    ↓
Modify
    ↓
Dirty Checking
    ↓
Flush
    ↓
UPDATE
```

---

# 15. Flush Before Dependent Operations

Sometimes Hibernate needs SQL to execute before another operation can proceed.

For example:

```text
Create Order
    ↓
Generate Order ID
    ↓
Create Payment using order_id
```

Conceptually:

```text
INSERT Order
     ↓
Order ID = 101
     ↓
INSERT Payment(order_id = 101)
     ↓
COMMIT
```

The important point:

**The Order INSERT does not have to be committed before Payment can use its ID.**

Both can remain inside the **same transaction**.

---

# 16. Rollback

By default, Spring rolls back for unchecked exceptions such as:

```java
RuntimeException
```

Example:

```java
@Transactional
public void placeOrder() {

    saveOrder();

    throw new RuntimeException();

}
```

Result:

```text
saveOrder()
    ↓
Flush
    ↓
Exception
    ↓
ROLLBACK
```

---

# 17. Checked Exceptions

Checked exceptions don't normally cause rollback by default.

```java
throw new Exception();
```

To explicitly request rollback:

```java
@Transactional(
    rollbackFor = Exception.class
)
```

---

# 18. `noRollbackFor`

You can specify exceptions that should not trigger rollback:

```java
@Transactional(
    noRollbackFor = SomeException.class
)
```

Use this only when it matches the intended business behavior.

---

# 19. Transaction Best Practices

### ✅ Put transaction boundaries mainly in the Service layer

```java
@Service
public class OrderService {

    @Transactional
    public void placeOrder() {
        // business operation
    }
}
```

### ✅ Keep transactions reasonably short

Avoid holding a database transaction open while performing slow external operations.

### ✅ Use `readOnly = true` for read-oriented transactions where appropriate

```java
@Transactional(readOnly = true)
public Product getProduct(Long id) {
    return repository.findById(id).orElseThrow();
}
```

### ✅ Group related business operations into one transaction

```text
Place Order
 ├── Create Order
 ├── Create Order Items
 ├── Reduce Stock
 └── Update Payment Status
```

### ❌ Don't assume `@Transactional` automatically solves concurrency

For concurrent updates, additional mechanisms may be required:

```text
@Transactional
      +
Optimistic Locking
```

or:

```text
@Transactional
      +
Pessimistic Locking
```

---

# 🛒 Nexora Application

For Nexora, an order operation can eventually follow:

```text
Customer
   ↓
OrderController
   ↓
OrderService
   ↓
@Transactional
   ↓
Create Order
   ↓
Create Order Items
   ↓
Check / Update Stock
   ↓
Payment Status
   ↓
Hibernate Flush
   ↓
MySQL
   ↓
COMMIT
```

If an important operation fails:

```text
Exception
   ↓
ROLLBACK
```

Later, **Optimistic/Pessimistic Locking** will protect inventory from concurrent customers trying to purchase the same stock.

---

# 🧠 Day 14 Key Mental Model

```text
             Spring
                │
        @Transactional
                │
                ▼
         Transaction Proxy
                │
             BEGIN
                │
                ▼
            Service
                │
                ▼
            Hibernate
                │
                ▼
       Persistence Context
                │
          Dirty Checking
                │
              Flush
                │
                ▼
             MySQL
                │
          ┌─────┴─────┐
          ▼           ▼
       COMMIT      ROLLBACK
```