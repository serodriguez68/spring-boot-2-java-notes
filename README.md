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

# Chapter 4: Web Pages with Spring: Spring Web and template rendering
- The web functionality in Spring is given by the Spring Web dependency.
- The controller is the heart of Spring Web, both for web pages and web
  APIs.
- In spring, the controller is nothing more than a POJO that is
  decorated with annotations.
  - Annotations make the controller a bean that is managed by the IoC
    container.
  - Annotations allow some functionalities like routing to be Aspected in
    (aka servlet mapping).
- When controllers finish the job, they either output raw data or a
  view.
- HTML template engines:
  - Multiple are supported
  - Thymeleaf is the most popular one.

## MVC in Spring Web
In Spring web, MVC refers to something slightly different than in for
example Rails MVC.
- The "Controller" is equivalent to the controller in Rails.
- The "View" refers to the STATIC part of the HTML template.
- The "Model" refers to the DYNAMIC part of the view. This is the main
  difference with Rails models. In Spring a Model (view model) is only
  there to represent the dynamic data of the View and is SEPARATE from
  the entities. In Rails, models are Entities.

# Chapter 5: Exposing RESTful Endpoints with @RestController
- For JSON endpoints Spring MVC still makes sense. We just need a to use
  a JSON "view" instead of an HTML one.
- Spring automatically does the JSON marshaling and unmarshalling for
  you.
- XML endpoints are also available via a simple configuration.
- @RestController vs @Controller: The @ResponseBody annotation allows
  for JSON or XML Mapping of the Response Object.
