# Day 03 - Dependency Injection & Component Scanning

## Objective
Understand how Spring automatically creates and injects objects using Component Scanning and Constructor Dependency Injection.

---

# What is a Dependency?

A dependency is an object that another object requires to perform its work.

### Example

```java
public class Car {

    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Here, **Car depends on Engine**, so Engine is the dependency.

---

# What is Dependency Injection (DI)?

Dependency Injection is a design pattern in which the required dependencies are provided by the Spring Container instead of being created inside the class.

### Without Dependency Injection

```java
Engine engine = new Engine();
Car car = new Car(engine);
```

The developer creates and injects the dependency manually.

### With Spring Dependency Injection

```java
Car car = context.getBean(Car.class);
```

Spring creates the Engine Bean, injects it into Car, and returns the fully initialized Car Bean.

---

# Constructor Injection

Constructor Injection is the recommended way to inject dependencies.

### Example

```java
@Component
public class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

### Advantages

- Dependencies are mandatory.
- Object becomes immutable using `final`.
- Easy to unit test.
- Recommended by the Spring Framework.

---

# @Component

`@Component` marks a class as a Spring-managed Bean.

### Example

```java
@Component
public class Engine {

}
```

During Component Scanning, Spring automatically creates an Engine Bean.

---

# Component Scanning

Component Scanning is the process in which Spring searches packages for stereotype annotations and automatically registers them as Beans.

Example:

```java
@Configuration
@ComponentScan("com.saurabh.spring.day03.dependencyinjection")
public class AppConfig {

}
```

Spring scans the specified package and its sub-packages for components.

---

# Component Scanning Flow

```
Application Starts
        │
        ▼
Spring Container Starts
        │
        ▼
Component Scan
        │
        ▼
Find @Component Classes
        │
        ▼
Create Bean Definitions
        │
        ▼
Create Bean Objects
        │
        ▼
Store Beans in Spring Container
```

---

# How Dependency Injection Works

Suppose Car requires an Engine.

```
Car Constructor
        │
        ▼
Engine Required
        │
        ▼
Search Spring Container
        │
        ▼
Engine Bean Found
        │
        ▼
Inject Engine
        │
        ▼
Create Car Bean
```

---

# Bean Resolution

Spring resolves dependencies **by Java Type**, not by variable name.

Example:

```java
Engine engine = new PetrolEngine();
```

Since PetrolEngine **is-a** Engine, it is considered a valid candidate.

---

# Common Exceptions

## NoSuchBeanDefinitionException

Occurs when no Bean of the required type exists.

Example:

```
No qualifying bean of type 'Engine' available
```

Reason:
- Bean not registered.
- Missing `@Component`.
- Missing `@Bean`.
- Package not scanned.

---

## NoUniqueBeanDefinitionException

Occurs when multiple Beans match the required type.

Example:

```
PetrolEngine
DieselEngine
ElectricEngine
```

Spring cannot decide which Engine to inject.

This problem is solved using:

- `@Primary`
- `@Qualifier`

---

# Manual Bean Registration

```java
@Bean
public Engine engine() {
    return new Engine();
}
```

The developer explicitly registers the Bean.

---

# Automatic Bean Registration

```java
@Component
public class Engine {

}
```

Spring automatically discovers and registers the Bean during Component Scanning.

---

# Interview Questions

### What is Dependency?

An object required by another object to perform its work.

---

### What is Dependency Injection?

Providing dependencies from the Spring Container instead of creating them manually.

---

### What is Constructor Injection?

Injecting dependencies through the constructor of a class.

---

### Why is Constructor Injection preferred?

- Mandatory dependencies
- Immutability
- Better testing
- Recommended by Spring

---

### What is @Component?

Marks a class as a Spring-managed Bean.

---

### What is Component Scanning?

The process of searching packages for Spring Components and registering them as Beans.

---

### How does Spring resolve dependencies?

Spring resolves dependencies by Java Type.

---

# Summary

- A dependency is an object required by another object.
- Spring performs Dependency Injection using the IoC Container.
- Constructor Injection is the recommended injection technique.
- `@Component` registers application classes as Beans.
- Component Scanning automatically discovers Components.
- Spring resolves dependencies by Java Type.
- Missing Beans cause `NoSuchBeanDefinitionException`.
- Multiple matching Beans cause `NoUniqueBeanDefinitionException`.