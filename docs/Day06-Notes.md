# Day 06 - Spring Dependency Injection & Bean Resolution

## 📚 Topics Covered

- Constructor Injection
- Setter Injection
- Field Injection
- @Autowired
- @Primary
- @Qualifier
- Dependency Resolution
- NoUniqueBeanDefinitionException
- Spring Bean Creation Flow

---

## 📖 What I Learned

### Constructor Injection
- Recommended dependency injection technique.
- Spring automatically injects dependencies when only one constructor exists.
- Supports immutable (`final`) dependencies.
- Best for required dependencies.

### Setter Injection
- Dependencies are injected through setter methods.
- Suitable for optional dependencies.
- Object is created first, then dependencies are injected.

### Field Injection
- Spring injects dependencies directly into fields using reflection.
- Convenient but not recommended for production code.
- Harder to test and hides dependencies.

### @Autowired
- Marks an injection point.
- Can be used on constructors, setters and fields.
- Not required for a single constructor (Spring 4.3+).

### @Primary
- Marks the default bean when multiple beans of the same type exist.

### @Qualifier
- Specifies exactly which bean should be injected.
- Overrides @Primary.

### Bean Resolution Order

Need Bean
↓
Search by Type
↓
One Bean?
├── Yes → Inject
└── No
    ↓
@Qualifier?
├── Yes → Inject Qualified Bean
└── No
    ↓
@Primary?
├── Yes → Inject Primary Bean
└── No
    ↓
NoUniqueBeanDefinitionException

---

## 🧪 Experiments Performed

- Constructor Injection
- Multiple Constructors with @Autowired
- Multiple Engine Implementations
- @Primary Example
- @Qualifier Example
- Dependency Resolution
- Bean Creation Order Observation

---

## 🎯 Key Takeaways

- Spring injects beans by **type**.
- Constructor Injection is the industry standard.
- @Qualifier has higher priority than @Primary.
- Spring creates singleton beans during container startup.
- Bean creation order depends on dependency relationships.
- Never rely on console output order for independent beans.

---

## 💻 Technologies

- Java
- Spring Framework
- Spring Core
- Eclipse / STS