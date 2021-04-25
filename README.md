# Notes from the videos


# Chapter 2: Spring Data
## Benefits
- Allows you to make queries using Java with conventions.
- Allows you easily swap backing DBs without changing the code.

## Key code components
- Provides a repository interface: where you interact with data access
- Entity objects: data class that represents the table or document in
  the database. The repo relies on the entity for its template
  definition,

# Chapter 3: Service Tier and Dependency Injection
- The core of Spring is a dependency injection framework powered by an
  Inversion of Control (IoC) container.
- Dependencies that go into the IoC container are configured by the devs
  through:
  - Annotations and Component Scanning: for example annotating with
    @Service or @Repository tells Spring that this class is a bean that
    can be injected
  - Java Config (used when you need to do some work to initialize a
    dependency)
  - Auto configuration
  - XML (deprecated now)
- Spring instantiates all dependencies at startup
- The Bean factory holds the handles to all injectable dependencies and
  manages the dependencies' lifecycle.
- By default, Spring serves singletons of injectable dependencies.
