# Day 05 - Spring Bean Lifecycle

## 📌 Overview
I learned how Spring manages the complete lifecycle of a bean, from object creation to destruction. I explored initialization and cleanup callbacks, different lifecycle management approaches, and the differences between Singleton and Prototype beans.

---

## 📚 Topics Covered

- Spring Bean Lifecycle
- Constructor Execution
- Dependency Injection
- `@PostConstruct`
- `@PreDestroy`
- `@Bean(initMethod, destroyMethod)`
- Singleton vs Prototype Lifecycle
- Bean Definition vs Bean Instance
- Stateless Singleton Beans

---

## 🏗️ Project Structure

```
Day05_BeanLifecycle
│
├── AppConfig.java
├── DatabaseConnection.java
├── Main.java
└── Day05-Notes.md
```

---

## 🔄 Spring Bean Lifecycle

```
Spring Container Starts
        │
        ▼
Constructor Executes
        │
        ▼
Dependency Injection
        │
        ▼
@PostConstruct
        │
        ▼
Bean Ready
        │
        ▼
Application Running
        │
        ▼
Context Closed
        │
        ▼
@PreDestroy
```

---

## 💡 Key Concepts

### Constructor
- Creates the bean object.
- Executes before dependency injection.
- Should not contain heavy initialization logic.

### @PostConstruct
- Executes once after dependency injection.
- Used for initialization tasks.
- Examples:
  - Opening database connections
  - Loading cache
  - Validating configuration
  - Starting background services

### @PreDestroy
- Executes before a Singleton bean is destroyed.
- Used for cleanup operations.
- Examples:
  - Closing database connections
  - Releasing resources
  - Stopping background services

---

## ⚙️ Bean Lifecycle Using @Bean

```java
@Bean(initMethod = "init", destroyMethod = "destroy")
public DatabaseConnection databaseConnection() {
    return new DatabaseConnection();
}
```

This approach is useful when working with third-party classes that cannot be modified.

---

## 🔄 Singleton vs Prototype

| Singleton | Prototype |
|-----------|-----------|
| One bean instance | New bean instance for every `getBean()` |
| Created once | Created on demand |
| Managed completely by Spring | Spring only creates and initializes |
| `@PreDestroy` executes | `@PreDestroy` is not called automatically |

---

## 🧠 Important Learnings

- Constructor is responsible for creating the object.
- Initialization should be performed after dependency injection.
- `@PostConstruct` executes automatically after all dependencies are injected.
- `@PreDestroy` executes only for Singleton beans managed by Spring.
- `@Bean(initMethod, destroyMethod)` provides lifecycle callbacks without using annotations inside the class.
- Singleton beans should remain stateless to avoid concurrency issues.

---

## ▶️ Output

```
1. Constructor : DatabaseConnection Bean Created
2. Database Connection Opened

Application Running...

3. Database Connection Closed
```

---

## 🎯 Interview Questions

- What is the Spring Bean Lifecycle?
- What is the difference between Constructor and `@PostConstruct`?
- Why should heavy initialization not be placed inside the constructor?
- Why doesn't `@PreDestroy` execute for Prototype beans?
- What is the difference between `@Component` and `@Bean`?
- When should `@Bean(initMethod, destroyMethod)` be preferred?

---

## 🚀 Outcome

By completing Day 05, I gained a solid understanding of how Spring creates, initializes, manages, and destroys beans. I also learned different lifecycle callback mechanisms and understood why Singleton beans should be stateless in enterprise applications.