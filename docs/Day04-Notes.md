# Day 04 - Bean Scope

## Objective
Understand how Spring manages the number of Bean instances and their lifecycle.

---

# What is Bean Scope?

Bean Scope defines how many objects Spring creates for a Bean and how long those objects live.

---

# Types of Bean Scope

1. Singleton
2. Prototype
3. Request
4. Session

---

# Singleton Scope

- Default scope in Spring.
- Only one Bean instance is created per Spring Container.
- Every getBean() call returns the same object.
- Beans are eagerly created by default during container startup.

Example:

@Component
public class UserService {

}

Output:

user1 == user2

true

---

# Prototype Scope

- A new Bean is created every time getBean() is called.
- Spring creates the object only when requested.

Example:

@Component
@Scope("prototype")
public class UserService {

}

Output:

user1 == user2

false

---

# Singleton vs Prototype

Singleton

- One object
- Default scope
- Eager initialization
- Shared by entire application

Prototype

- New object every getBean()
- Must use @Scope("prototype")
- Created on demand
- Not shared

---

# Request Scope

- Available only in Web Applications.
- One Bean per HTTP Request.
- Bean is destroyed after the request completes.

---

# Session Scope

- Available only in Web Applications.
- One Bean per User Session.
- Bean lives until session expires or user logs out.

---

# Important Rules

Rule 1

Singleton Beans are shared.

Rule 2

Prototype Beans create a new object every getBean() call.

Rule 3

Bean scope affects only that Bean, not its dependencies.

Example

Prototype Car

↓

Singleton Engine

Two Car objects share one Engine Bean.

---

# Scope Combinations

Singleton → Singleton

One Service
One Repository

Singleton → Prototype

Singleton receives one Prototype instance during creation.

Prototype → Singleton

Every Prototype object shares the same Singleton Bean.

Prototype → Prototype

Every Prototype object gets a new Prototype dependency.

---

# Interview Questions

Q. Default Bean Scope?

Singleton.

Q. Difference between Singleton and Prototype?

Singleton creates one Bean for the container.
Prototype creates a new Bean every request to the container.

Q. Can Request Scope work in a Console Application?

No.

Reason:
There is no HTTP Request.

Q. What happens if Prototype Bean is injected into Singleton Bean?

Only one Prototype object is injected when the Singleton Bean is created.

Q. Which scope is used for Shopping Cart?

Session Scope.

---

# Key Takeaways

✔ Bean Scope controls object creation.

✔ Singleton is the default scope.

✔ Prototype creates new objects on demand.

✔ Request and Session scopes are only for Web Applications.

✔ Bean scope does not automatically change the scope of dependent Beans.