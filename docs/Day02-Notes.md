# Day 02 - Spring Container & ApplicationContext

## Objective
Understand how the Spring Container creates, manages, and provides Beans using Java Configuration.

---

## What is Spring Container?

The Spring Container is the core component of the Spring Framework responsible for:

- Creating Beans
- Managing Bean Lifecycle
- Injecting Dependencies
- Storing Beans
- Providing Beans when requested

---

## What is ApplicationContext?

ApplicationContext is the advanced implementation of the Spring IoC Container.

Responsibilities:

- Reads Configuration Classes
- Creates and Stores Beans
- Injects Dependencies
- Returns Beans using getBean()

Example:

```java
ApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
```

---

## What is @Configuration?

Marks a Java class as a Spring Configuration Class.

Example:

```java
@Configuration
public class AppConfig {

}
```

---

## What is @Bean?

Marks a method whose returned object should be managed by Spring.

Example:

```java
@Bean
public Engine engine() {
    return new Engine();
}
```

---

## Bean Creation Flow

Application Starts

↓

ApplicationContext Created

↓

Reads AppConfig

↓

Finds @Bean methods

↓

Creates Bean Objects

↓

Stores Beans inside Container

↓

Application Ready

---

## getBean()

Example:

```java
Engine engine = context.getBean(Engine.class);
```

getBean() does NOT create a new object.

It returns the existing Bean managed by the Spring Container.

---

## Experiment

```java
Car car1 = context.getBean(Car.class);
Car car2 = context.getBean(Car.class);

System.out.println(car1 == car2);
```

Output:

```
true
```

Reason:

Both references point to the same Singleton Bean.

---

## Key Learnings

- Spring does not create every Java class.
- Only classes registered as Beans are managed.
- ApplicationContext starts the Spring Container.
- @Configuration defines Bean configuration.
- @Bean registers objects inside the Spring Container.
- getBean() returns the managed Bean.
- Singleton is the default Bean scope.

---

## Interview Questions

1. What is Spring Container?
2. What is ApplicationContext?
3. Difference between BeanFactory and ApplicationContext?
4. What is @Configuration?
5. What is @Bean?
6. What happens when ApplicationContext starts?
7. What does getBean() do?
8. Why does getBean() return the same object by default?

---

## Summary

Today I learned how Spring starts its IoC Container, reads Configuration classes, creates Beans, stores them internally, and returns managed objects using ApplicationContext. I also understood that Spring creates only registered Beans and, by default, returns the same Singleton instance whenever getBean() is called.