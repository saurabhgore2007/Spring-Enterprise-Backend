# Day 07 - Spring Boot Internals & Auto Configuration

## 📚 Topics Covered

- Why Spring Boot?
- Spring Framework vs Spring Boot
- Spring Boot Starters
- Auto Configuration
- @SpringBootApplication
- SpringApplication.run()
- application.properties

---

## 📖 What I Learned

### Why Spring Boot?

Spring Boot is built on top of Spring Framework and removes repetitive configuration by providing sensible defaults.

---

### Spring Framework vs Spring Boot

Spring Framework provides:

- IoC Container
- Dependency Injection
- Bean Lifecycle
- Spring MVC
- AOP
- Transaction Management

Spring Boot adds:

- Auto Configuration
- Starter Dependencies
- Embedded Tomcat
- Production Ready Features
- External Configuration

---

### Starter Dependencies

Starter dependencies are pre-configured dependency bundles.

Example:

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-test

Instead of adding many libraries manually, Spring Boot downloads all compatible dependencies automatically.

---

### Auto Configuration

Spring Boot checks the dependencies available on the classpath and automatically configures the application.

Examples:

- Embedded Tomcat
- Spring MVC
- Jackson
- Hibernate
- JPA
- DispatcherServlet

If a custom bean is provided, Spring Boot usually backs off and uses the developer's configuration.

---

### @SpringBootApplication

This annotation combines:

- @Configuration
- @ComponentScan
- @EnableAutoConfiguration

It serves as the main entry point of every Spring Boot application.

---

### SpringApplication.run()

Application startup sequence:

JVM

↓

main()

↓

SpringApplication.run()

↓

Create ApplicationContext

↓

Component Scan

↓

Auto Configuration

↓

Create Beans

↓

Dependency Injection

↓

@PostConstruct

↓

Start Embedded Tomcat

↓

Application Ready

---

### application.properties

Stores application configuration.

Examples:

server.port=9090

spring.datasource.url=...

spring.jpa.show-sql=true

logging.level.org.springframework=DEBUG

Spring Boot reads these values during startup and configures the application automatically.

---

## 🧪 Experiments Performed

- Generated Spring Boot Project
- Added Spring Web Starter
- Observed Embedded Tomcat Startup
- Changed Server Port using application.properties
- Explored Spring Boot Startup Process

---

## 🎯 Key Takeaways

- Spring Boot is built on Spring Framework.
- Starter dependencies simplify dependency management.
- Auto Configuration creates sensible default configurations.
- @SpringBootApplication combines three important annotations.
- SpringApplication.run() initializes the complete Spring Boot application.
- application.properties customizes application behavior without changing Java code.

---
