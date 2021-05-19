# Spring Boot 2 Essential Training

These notes are taken from the LinkedIn Learning course
[Spring Boot 2 Essential Training](https://www.linkedin.com/learning/spring-boot-2-0-essential-training-2/spring-into-spring?u=2094516).

The sample project in this repository is also filled with detailed
comments about the different aspects of Spring Boot.

# Chapter 1 - Basics

## Understanding Auto-Configuration

- Auto config is the heart of spring boot
- @EnableAutoConfiguration is the key annotation that allows for classes
  to be scanned dynamically.
- @SpringBootApplication is a stereotype of @EnableAutoConfiguration
  which means it includes it.
- We can use @AutoConfigureBefore and @AutoConfigureAfter to hook into
  the configuration (more later).

### Conditional Loading

The concept of conditional loading is also key in understanding how
spring boot works and how we can hook into it.
- @ConditionalOnClass - load configuration conditional on a class being
  available on the classpath.
- @ConditionalOnBean - load configuration conditional on a bean being
  loaded.
- @ConditionalOnProperty - load configuration conditional on a property.
- @ConditionalOnMissing(Bean, Class, Property)
- More on this later

### Properties

Properties also make a big part of spring boot.
- Spring Boot comes with default properties for auto-configuration
  classes.
- EnableConfigurationProperties specifies a property set.
- Properties can ALWAYS be overridden.

### Other conditionals

These conditionals exist, but we are not going to study them in the
course.
- Application type based conditionals are available: e.g. web
  application vs not web application.
- Resource or file bases conditionals
- Expression based conditional.

## Configuring Spring

### Property-Based Configuration

- `application.properties` or `application.yaml`
- Only good for basic configuration
- Only good for development or demo purposes. NOT for production code.

### Environment variable Configuration

- You can set up your application so that it reads configuration from
  environment variables.
- Very common to modify the runtime.

### Command-line parameters

- You can set up your application so that it reads configuration
  variables from command line parameters.
- Not so common but still possible.

### Configuring via Cloud configurations (e.g Config Server, Consul)

- Most common way to configure a production application.
- Make your app reach out to a configuration server to read
  configuration parameters.
- Very powerful as it allows to easily change the configuration on a
  cluster running a service.
- More on this topic: See the course
  [Spring: Spring Cloud](https://www.linkedin.com/learning/spring-spring-cloud-2/spring-to-the-cloud?u=2094516)
  for more information about this.

## Configuring beans

There are several ways to configure your beans:
- Option 1: Configure beans on your default application class
  - This class can get too big too fast.
- Option 2: Use separate configuration classes to configure beans.
- Option 3: Use XML files (deprecated)
  - Try to favour using java based configurations or component scan
    configurations with annotations.

## Spring profiles

- For example: dev, staging, pre-prod, prod. Each of this will be
  considered a "profile" and configuration can be set to change
  according to the profile.
- Ideal for multi-environment deployments.

### Implementation options of configurations based on profiles

- Option 1) Using application.yaml
  - Use `spring.config.activate.on-profile` as the top level key of all
    configurations for a particular profile.
  - See `application.yaml` in this project for an example
- Option 2) Use profiles + configuration servers
  - This is nothing more than a YAML file that has been externalized.

### How to activate a profile

You have multiple options:
- Set `spring.profiles.active=prod` in the properties
- Inject the profile via a command line arg
- Specify the profile in an ENV variable (most common method)
  - The env variable name must be `spring.profiles.active`
    - See IntelliJ run configuration for this project
- If you activate a profile, and a particular property does not have a
  value for that profile, the default value will be used.

## Building Spring Boot Apps

- Spring can use Maven or Gradle
- Spring comes with multiple built-in build scripts that hook into the
  Maven or Gradle build.
- The output is an executable JAR or WAR (except for docker builds).
  - JAR or Docker are the outputs
- Out of the box Spring also comes with the tooling to run the JARs via
  `init.d` or `systemd`.

## Spring Boot and Docker

- Out of the box the Spring Boot provides build script to build docker
  images
  - Maven: `spring-boot:build-image`
  - Gradle: `bootBuildImage`
- You can also create your raw Dockerfile
  - You do this to get more control
  - This also allows you to build smaller images
  - This allows you to set the base pre-approved image you want to use


# Chapter 2 - Spring Boot Web

## Understanding Spring Boot Dependencies

- The `spring-boot-starter-web` dependency contains all the dependencies
  to build HTML web applications and also Web APIs
- In a way, it is a meta-dependency that packages other dependencies
  form Spring and from other vendors (like Apache). The meta-dependency
  has already taken care of all the version resolution.
- These are some examples of the tooling the meta-dependency brings:
  - HTTP Server: Tomcat comes inside `spring-boot-starter-web`
    - It can be replaced for others if you need to.
  - JSON Marshallers
    - `spring-boot-starter-web` brings Jackson
    - Spring boot web also sprinkles in automatic
      marshalling/unmarshalling on endpoints.
      - You can override the automatic marshalling if you want to.
  - Testing Harness for Web Applications
    - e.g. stuff like jsonpath matchers, mockmvc
  - Logging Frameworks: By default it uses SLF4J
    - Spring builds its own loggers to log framework output (powered by
      SLF4J)
    - You can either reuse those loggers or create your own.
    - If you use the one that Spring provides you get:
      - Property-based modification for logs
      - Logback logging
  - YAML parsing: powered by SnakeYAML
  - Spring Boot auto-configuration library
  - Core spring libraries: beans, core, context, etc.

## Configuring the embedded Tomcat

The default configuration of the Tomcat server is not the best. In this
section we show how to customise the Tomcat server.

### Property based configuration

The easiest way to configure your Tomcat.
- `server.address`, `server.port`, `server.contextPath`
- Session based configs (cookies, timeouts, etc)
- The SERVER error page path can be configured.
- Compression configuration
  - `server.compression.enabled=true`
- TLS configuration
  - This can be done via properties
  - Requires a Java keystone that contains the cert to be available to
    your app
  - This will also require a password for the certificate to be
    available to your app.
  - This means you need a secret manager.
- The full list of properties can be found in the code
  `org.springframework.boot.autoconfigure.web.ServerProperties`

## Rest of the chapter...

The content of the rest of the chapter was already covered in the
previous course "learning spring with spring boot". See that courses'
code and notes for all info.


# Chapter 3 - Spring Boot Command Line

Spring boot also allows you to create applications that are designed to
run only via the command line. This allows us to use all the power of
Spring's dependency injection and ecosystem for creating command line
applications to do things like admin tasks or batch operations.

Spring boot command line applications can can also be used to create
runners that are run by other spring applications.

- The core of the command line applications is the `CommandLineRunner`
  interface.
  - The `ApplicationRunner` interface is a similar interface.
- The only thing we need to do to get create a Spring Boot runner is
  implement a `run` method in the main application class. The rest of
  the code is up to us.
  - See this
    [linkedin learning video](https://www.linkedin.com/learning/spring-boot-2-0-essential-training-2/building-a-command-line-application?u=2094516)
    for more info.
- When creating command line runners with multiple tasks, we can use the
  `@Order` annotation spring the run order of those tasks.
  - The course didn't go into any more detail on how this works.

The basic template for a command line application looks like this:

```java
public class MyCommandLineApp {
    private static final Logger LOG = LoggerFactory.getLogger(MyCommandLineApp.class);
    public static void main(String[] args) {  SpringApplication.run(MyCommandLineApp.class, args); }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
          LOG.info("Starting dummy command line runner");
          /* We can do whatever we want here */
          LOG.info("Finishing dummy command line runner");
        };
    }

}
```

# Chapter 4 - Spring Boot Data

Note: We did not take code notes for this chapter in this repo because
this was already covered in the previous course with better depth. **SEE
the learning-spring code for an example of how to actually use this.**

## The Repository Pattern

- Most of Spring Data is based on the repository pattern.
- A repository:
  - Deals with one and only one type of business object (aka entity).
    - If you need to deal with multiple, then you need multiple
      repositories.
  - Makes it clear what type of operations can be performed on a
    business object (entity).
  - Prevents leakage of data access logic outside the repositories.
    - This helps avoid many performance problems that often come out of
      uncontrolled use of data access from anywhere in the code.

## Introducing Spring Boot Data

- Based on Spring Data.
- Has built in support for relational and non-relational DBs.
- Spring Boot Data leverages some common defaults from Spring Data.
- When using an embedded databases, spring boot data has a convention
  where you can put sql files that will be used to "seed" the database.
  - Put files in `resources/data.sql` and `resources/schema.sql` and
    they will be automatically picked up
- You can only have ONE database auto-configured. Any other database
  that your application needs to access will need to be manually
  configured.
- When configured (via auto config or manual config) you get a
  `DataSource` object that you can inject anywhere you need it.
  - This is most likely the Repositories only, but theoretically you
    could inject a `DataSource` object anywhere you want.
  - If you use JPA, Spring will automatically inject the data source
    bean into your repositories.

# Chapter 5 - Spring boot extra topics

## Spring Security

Note: We did not take code notes for this chapter in this repo. **SEE
the learning-spring code for an example of how to actually use this.**

### Basic Auth

- In its basic default form, it provides HTTP basic auth on all
  endpoints except common ones like /js and /css endpoints.
  - In the basic form, the user and password are generated at startup
    time and printed into the app logs.
  - Basic auth is NOT recommended for production code.

### Form based Auth

- Spring security provides support for form based auth, but you need to
  configure it yourself.
- Non-dummy use of form based auth requires the use of
  `WebSecurityConfigurerAdapter`.
  - This is needed to be able to back your Authorization with a data
    store.
  - Using `@EnableWebSecurity` in the `WebSecurityConfigurerAdapter`,
    disables basic auth.
  - Not using `WebSecurityConfigurerAdapter` only allows us to retrieve
    users and passwords from in-memory stores.
- Use BCrypt to hash the passwords. Do NOT use SHA-1 or MD5.

### OAuth2

- Spring boot has full support for OAuth2.
- There are starter applications both for OAut servers and clients.

## Asynchronous Messaging with Spring Boot

The course talks about a specific style of async messaging between
microservices: AMQP via RabbitMQ. There are other types of async message
technologies like Kafka.
- [See what is RabbitMQ](https://www.youtube.com/watch?v=7rkeORD4jSw)
- [RabbitMQ vs Kafka](https://www.youtube.com/watch?v=GMmRtSFQ5Z0)

Code notes were not taken for any of the RabbitMQ Videos. Rewatch the
videos if you need to implement something with Rabbit.

## Basic RabbitMQ main components

There are 4 main components in a rabbit MQ system: producers, exchanges,
queues and consumers.

Spring boot provides a dependency for us to implement producers and
consumers as spring applications. Exchanges and Queues are typically
just ran as "plug and play" docker containers whose implementation we
don't care about.

### Producers

- The microservice that publish messages.
- Spring provides a template to make a microservice a RabbitMQ producer.
- You can tweak the config of the default template via configuration
  properties.
- You can put JSON as messages or serialized java objects.
  - It is often recommended that you use JSON to avoid the serialization
    overhead and not couple to Java.

### Consumers / Listeners

- The microservices that read from the message queues and react to them.
- Spring provides a RabbitMQ consumer implementation template that is
  capable of getting retrieving and acknowledging messages out of a
  queue.
- Once the Spring Rabbit consumer logic retrieves the message, it is up
  to us to implement whatever logic we want.

#### Exchanges / Broker

A server that receives all the messages from the producers and decides
to which queues should the message go (it may duplicate the message to
send it to multiple queues).

#### Queues

Single threaded servers that hold all the messages of a specific queue
and delete. They delete the message once the consuming microservice
acknowledges that it has processed it correctly.

## Spring REST repositories

- Additional dependency `spring-boot-starter-data-rest`
- Automagically exposes endpoints to access our repositories via a REST
  api.
  - In other words, it looks for all of our `@Repositories` and
    generates endpoints for them under a configurable url.
  - For example, if we have a `RoomsRepository` and a configured base
    URL `/api/autorest/*`, we can access the rooms index via
    `/api/autorest/rooms`.
- The automatic endpoint also includes
  [HATEOEAS](https://spring.io/guides/gs/rest-hateoas/) data in the
  responses.
- This feature is kind of weird because there is a lot of convention and
  magic.

## Spring Actuator

Actuator is another optional spring boot dependency designed to allow us
to monitor how our application is running.

Just by virtue of including it in our application we get the following
endpoints:
- A health endpoint we can use to check if the app is UP or DOWN.
  - The health check functionality also checks that all related
    dependencies (like PostgreSQL or Rabbit) are also healthy.
  - An app is only considered UP when all the dependencies are UP.
- A Beans endpoint where we can check all the available beans for an
  app.
- An Env endpoint where we can check all the ENV variables the app is
  using.
- An endpoint to expose metrics common memory and machine metrics like
  JVM heap size, RAM, etc.

### Actuator Security

- Actuator is by default it is relatively locked down since it provides
  data that could be used by a bad actor.
- None of these endpoints should be publicly available so make sure you
  use Spring Security or other mechanism to keep that data secure.
- Actuator comes with many endpoints. Only turn on what you need.

### Actuator Extension

We add custom endpoints into actuator. The LinkedIn Learning video shows
a very basic example.
- It is common for enterprises that have microservices to have to add
  the same custom actuator extension endpoints to all their services.
  This can easily be done by adding the custom extensions to customized
  Spring Boot Starters.

## Custom Spring Boot Starters

A Spring boot starter is like a meta-library that collects and describes
all the dependencies needed (and their configuration) to provide a
specific well defines slice of functionality.

For example `spring-boot-starter-web` provides our application with
route handling, http servers, controllers, etc (note that it is common
for a starter to use other starters). This single pre-baked dependency
already encapsulates all the compatible dependencies needed and their
autoconfiguration so that we only need to deal with a single dependency.

We can build custom spring boot starters as a mechanism to provide auto
configurable pieces of functionality to other spring boot applications.

### High level steps for creating a custom Spring Boot starter

**1)Create a Library**

Start by extracting the common code that you want to share with multiple
Spring apps as a library.

- Make sure your library is not Spring-specific and it works in any
application.
- Consider your library dependencies wisely, because if you decide to
  bring a crazy dependency for your library, all apps that use your
  starter will end up depending on the crazy dependency.

**2) Build an Auto-Configuration Module for your library**

Separately from your library, code a Spring Boot Auto-Configuration
module for your library. This is the bit that will make your library
very easy to use within a Spring boot application.
- The Auto-Configuration module should NOT be included in your library
  because your library should be usable by any app with or without
  Spring Boot.
- Your Auto-Configuration module should include your library as a
  dependency. In that way, you only need to include the Auto-Config
  module in your starter.

**3) Build the Starter Module**[README]()

A starter module is usually an empty JAR file that holds:
- Dependencies to your auto-configuration module and library
- Dependencies to any other starter you want to include
- From the LinkedIn Learning video, it is not very clear what this is.
