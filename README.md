
# Spring Documentation


<div style="text-align:center;">
  <img src="https://github.com/douaeelh2/Spring-Documentation/assets/127549220/355c9b44-800a-4e34-8442-8f65ec6316d1"  style="width:100%">
</div> <br /> <br />

Comprehensive documentation for integrating and utilizing Spring Framework within your projects, featuring essential information, examples, and references to help you leverage Spring's features effectively. This documentation also includes prerequisites for Spring Boot.

## Table of Contents

1. [Introduction to Spring Framework](#introduction-to-spring-framework)
    - [Key Features of Spring Framework](#key-features-of-spring-framework)
    - [Why Choose Spring Framework?](#why-choose-spring-framework)
2. [Spring Components](#spring-components)
    - [Spring Container IoC](#spring-container-ioc)
    - [BeanFactory](#beanfactory)
    - [ApplicationContext](#applicationcontext)
3. [Spring Core Concepts](#spring-core-concepts)
    - [Dependency Injection](#dependency-injection)
    - [Aspect-Oriented Programming (AOP)](#aspect-oriented-programming-aop)
    - [Inversion of Control (IoC)](#inversion-of-control-ioc)
    - [Bean Lifecycle](#bean-lifecycle)
    - [Spring Architecture](#spring-architecture)
4. [References](#references)


# 1. Introduction to Spring Framework

- Prior to Enterprise Java Beans (EJB), Java developers relied on JavaBeans for web applications. However, JavaBeans lacked essential services like transaction management and security. EJB solved this but introduced `complexity`.

- Spring framework(which is commonly known as Spring) has emerged as a solution to all these complications . This framework uses various new techniques such as `Aspect-Oriented Programming (AOP)`, Plain `Old Java Object (POJO)`, and `dependency injection (DI)`, to develop enterprise applications, thereby removing the complexities involved while developing enterprise applications using EJB, Spring is an open source lightweight framework that allows Java EE 7 developers to build simple, reliable, and scalable enterprise applications.

- This framework mainly focuses on providing various ways to help you manage your business objects. It made the development of Web applications much easier as compared to classic Java frameworks and `Application Programming Interfaces (APIs)`, such as `Java database connectivity(JDBC)`, `JavaServer Pages(JSP)`, and `Java Servlet`.

- By using Spring, developers can focus on building business functionality without having to worry about low-level technical details.

In summary, Spring is a powerful toolkit that helps developers create high-quality Java applications faster and easier by supporting various important aspects like `object assembly`, `transaction management`, `security` and the `configuration`.

# Why Choose Spring Framework ?

- `Simplicity:` Spring provides a set of tools and features that make it easier to develop applications in Java. This allows you to focus on the business logic of their application, rather than spending time managing repetitive tasks.

- `Modularity:` Spring is designed modularly, meaning one can use only the modules they need. This helps reduce complexity and makes the application easier to maintain.

- `Testability:` Spring's design promotes testability by enabling easy unit testing and mocking of dependencies, leading to more reliable and maintainable codebases.

- `Easy integration:` Spring integrates easily with other frameworks and technologies, such as Hibernate, Struts, and JPA. This allows us to choose the tools that best suit their project.

- `Security:` Spring provides robust security features, such as authentication and authorization, that help protect the application from malicious attacks.

- `Community and Support:` Spring is supported by an active community of developers, which means there are always a regular updates and large amount of resources available to help developers solve problems and learn new features.

In summary, Spring Framework provides a comprehensive and flexible platform for building robust, scalable, and maintainable Java applications, making it a preferred choice for enterprise development.

# 2. Spring Framework Components

## Spring IOC Container

- Spring `IoC (Inversion of Control)` Container is the core of Spring Framework. It creates the objects, configures and assembles their dependencies, manages their entire life cycle by applying the principle of:

    - Inversion of control
    - Reverse dependency

- The Container uses Dependency Injection(DI) to manage the components that make up the application. It gets the information about the objects from a configuration file `(XML)` or Java `Code` or Java `Annotations` and Java `POJO class`. These objects are called `Beans`. Since the Controlling of Java objects and their lifecycle is not done by the developers, hence the name Inversion Of Control.

-  Each bean managed by Spring has one or more unique `identifiers`. `The instance(s)` of a bean are created by Spring depending on the configuration either in the form of a `singleton` (single instance) or `prototype` (an instance is created on each request to the container)

There are 2 types of IoC containers:
- BeanFactory
- ApplicationContext

![IoC-Containers](https://github.com/douaeelh2/Spring-Documentation/assets/127549220/116b8800-0b9e-4ae2-ae02-c8c1a0580d37)

## BeanFactory

- `BeanFactory` is the base interface for accessing Spring containers. It provides functionalities for bean `instantiation`, `configuration`, and `lifecycle` management.

- `BeanFactory` provides methods for retrieving beans by their name and type, as well as for checking the existence of a bean.

## ApplicationContext

- `ApplicationContext` extends BeanFactory and provides additional functionalities.
  In addition to BeanFactory's features , offering advanced features such as internationalization, event handling, AOP integration, and annotation-based configuration.

![BeanFactory](https://github.com/douaeelh2/Spring-Documentation/assets/127549220/86fa2db2-e6f3-4559-9bf0-31712fc96801)


### XmlBeanFactory :
- is a deprecated implementation of the `BeanFactory` interface that reads bean definitions from an `XML` file. It is lightweight and is mainly used for backward compatibility.

  ```java
    import org.springframework.beans.factory.BeanFactory;
    import org.springframework.beans.factory.xml.XmlBeanFactory;
    import org.springframework.core.io.ClassPathResource;
    
    public class Main {
        public static void main(String[] args) {
            // Load the XML configuration file
            ClassPathResource resource = new ClassPathResource("beans.xml");
        
            // Create the XmlBeanFactory instance
            BeanFactory factory = new XmlBeanFactory(resource);
            
            // Retrieve the bean by its name
            Person person = (Person) factory.getBean("person");
            
            // Use the bean
            System.out.println(person);
        }
    }

  ```
  ```xml
      <!-- beans.xml -->
        <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd">
            
            <!-- Person Bean -->
            <bean id="person" class="com.example.Person">
                <property name="name" value="John Doe" />
                <property name="age" value="30" />
            </bean>
        </beans>
    ```
### AnnotationConfigApplicationContext:
- is used to load Spring bean configuration classes that are annotated with `@Configuration`.
- It scans the packages for annotations like `@Component`, `@Service`, `@Repository`, etc., and registers them as Spring `beans`.

     ```java
        @Configuration
            public class AppConfig {
                @Bean
                public MyService myService() {
                    return new MyServiceImpl();
             }
           }

     ```  
     ```java
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;

        public class Main {
            public static void main(String[] args) {
                // Creating the AnnotationConfigApplicationContext instance
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
                
                // Using the context to retrieve a bean
                MyService myService = context.getBean(MyService.class);
                
                // Using the service
                myService.doSomething();
            }
        }

    ```

### ClassPathXmlApplicationContext:
- It loads bean definitions from an XML file located in the classpath. It's commonly used when the bean configuration is defined in an XML file within the project's resources.

  ```java
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;
    
    public class Main {
        public static void main(String[] args) {
            // Create the ClassPathXmlApplicationContext instance in src/main/resources
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            
            // Retrieve the bean by its name
            Person person = (Person) context.getBean("person");
            
            // Use the bean
            System.out.println(person);
        }
    }

  ```
  ```xml
    <!-- beans.xml -->
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
        
        <!-- Definition of the Person bean -->
        <bean id="person" class="com.example.Person">
            <property name="name" value="John Doe" />
            <property name="age" value="30" />
        </bean>
    </beans>
  ```

  ### FileSystemXmlApplicationContext:

    - It loads bean definitions from an XML file located in the file system. It's useful when the bean configuration file is outside the classpath, such as in a specific directory in the file system.

  ```java
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.FileSystemXmlApplicationContext;
    
    public class Main {
        public static void main(String[] args) {
            // Create the FileSystemXmlApplicationContext instance
            ApplicationContext context = new FileSystemXmlApplicationContext("C:/path/to/beans.xml");
            
            // Retrieve the bean by its name
            Person person = (Person) context.getBean("person");
            
            // Use the bean
            System.out.println(person);
        }
    }

  ```
# Spring Core Concepts
## Dependency Injection
- Dependency Injection is the main functionality provided by Spring `IOC(Inversion of Control)`. The Spring-Core module is responsible for injecting dependencies through either Constructor or Setter methods.

### XML Dependency Injection
- `Constructor Dependency Injection (CDI):` In this, the DI will be injected with the help of contructors. Now to set the DI as CDI in bean, it is done through the bean-configuration file For this, the property to be set with the CDI is declared under the `<constructor-arg>` tag in the bean-config file.

  ```java
      // UserRepository.java
     public class UserRepository {
         // implementation details
     }
  ```
  ```java
      // UserService.java
     public class UserService {
         private UserRepository userRepository;
     
         // java code constructor 
         public UserService(UserRepository userRepository) {
             this.userRepository = userRepository;
         }
     
         // Other methods
     }
  ```
  ```xml
     <!-- applicationContext.xml into src/main/resources -->
     <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd">
     
     <!-- Define the UserRepository bean -->
     <bean id="userRepository" class="com.example.UserRepository" />
 
     <!-- xml constructor injection -->
     <bean id="userService" class="com.example.UserService">
         <constructor-arg ref="userRepository" />
     </bean>
 
     </beans>
  ```
    - Advantages:
        - `Code Clarity:` Dependency is explicitly defined in the constructor, making the code more readable and aiding in understanding a class's dependencies.
        - `Immutable:` Once initialized via the constructor, dependencies cannot be changed, making the object immutable and less prone to errors.
        - `Direct Injection:` Dependencies are injected directly upon object creation, making it straightforward to understand the object's initialization.
        - `Compile-Time Safety:` All required dependencies must be provided upon object creation, ensuring that the object is initialized appropriately from the start.

    - Disadvantages:
        - `Long Constructors:` If a class has a large number of dependencies, constructors can become lengthy and difficult to manage, reducing code readability.
        - `Rigidity:` The number and type of dependencies are fixed when designing the constructor, potentially making the class less flexible for future modifications.
        - `Initialization Complexity:` If dependencies themselves have dependencies, object creation can become complex, requiring the passing of multiple levels of                 dependencies through constructors.
        - `Difficult to Manage for Large Object Hierarchies:` When dealing with object hierarchies with many nested dependencies, managing constructors can become                     challenging, and code maintenance can become complex.

    - `Setter Dependency Injection (SDI):` This is the simpler of the two DI methods. In this, the DI will be injected with the help of setter and/or getter methods. Now to set the DI as SDI in the bean, it is done through the `bean-configuration` file For this, the property to be set with the SDI is declared under the `<property>` tag in the bean-config file.

- Example 1 :

  ```java
      // UserRepository.java
     public class UserRepository {
         // implementation details
     }
  ```
  ```java
      // UserService.java
         public class UserService {
             private UserRepository userRepository;
         
             // java code setter method
             public void setUserRepository(UserRepository userRepository) {
                 this.userRepository = userRepository;
             }
         
             // Other methods
         }

     }
  ```
  ```xml
     <!-- applicationContext.xml into src/main/resources -->
         <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://www.springframework.org/schema/beans
             http://www.springframework.org/schema/beans/spring-beans.xsd">
 
             <!-- Define the UserRepository bean -->
             <bean id="userRepository" class="com.example.UserRepository" />
         
             <!-- Define the UserService bean with setter injection -->
             <bean id="userService" class="com.example.UserService">
                 <property name="userRepository" ref="userRepository" />
             </bean>
 
         </beans>
  ```

    - When you define beans in Spring's XML configuration file, each bean is identified by a unique `ID`. When you use this `ID` in another bean to indicate which dependency should be injected, you use the `ref` attribute.

- Example 2 :

     ```xml
       <bean id="userService" class="com.example.UserService">
            <constructor-arg index="0" value="Kelmoua" />
            <constructor-arg index="1" value="Leila" />
       </bean>
     ```
     ```java
        public class UserService {
            private String param1;
            private String param2;
        
            // Constructor
            public UserService(String param1, String param2) {
                this.param1 = param1;
                this.param2 = param2;
            }
        
            // Other methods of the class
        }
     ```

- Here , two constructor arguments are provided using <constructor-arg> elements with value attributes. These arguments correspond to the strings "Kelmoua" and "Leila".

    - Advantages:
        - `Flexibility:` Setter injection offers runtime flexibility, enabling changes to dependencies dynamically.
        - `Ease of Testing:` Simplifies unit testing by allowing easy substitution of dependencies via setter methods.
        - `Reduced Constructor Length:` Constructors remain concise as they only initialize mandatory dependencies, leaving optional ones to be set via setters.
        - `Partial Initialization:` Objects can be partially initialized, with dependencies set incrementally based on specific conditions.

    -  Disadvantages:
        - `Order Dependency:` Correct order of setter calls is crucial for proper object initialization, leading to runtime errors if not managed properly.
        -  `Mutable State:` Object state can be mutable post-creation, posing challenges in maintaining consistency.
        -  `Increased Complexity:` Complexity may rise, especially in classes with many dependencies or circular dependency scenarios.
        - `Less Explicit:` Dependency initialization becomes less explicit compared to constructor injection, making it harder to grasp object initialization requirements.


        #### When to use constructor injection CDI ?
         - Mandatory dependencies
        #### When to use accessor injection SDI ?
         - Optional dependencies
         - The number of mandatory dependencies becomes very large

- `FactoryBean Dependency Injection` is a Spring interface that allows you to create custom objects to be used as beans in an application.
  - This interface defines a `getObject()` method which allows you to create an instance of the object to use as a bean, as well as methods to manage the scope of the             bean and its type.
  - Using FactoryBean is useful in cases where creating a bean is more complex than simply instantiating a class with a constructor.

    ```java
       public  class BeanSimple {
               public BeanSimple () {
                   return super();
                 }
              }
     ```
     ```java
        public class BeanSimpleFactory implements FactoryBean<BeanSimple> {  
                 public BeanSimpleFactory() { super(); }
                 public BeanSimple getObject() throws Exception {  return new BeanSimple();}
                 public Class<BeanSimple> getObjectType() {  return BeanSimple.class;}
                    public boolean isSingleton() {  return false;}}
     ```

  #### What is `FactoryBean` for ?
    - Useful for instantiating beans whose configuration is too complex to perform in XML.
    - Integration of existing code.
    - The injection is carried out as with any bean.

    - `Spring bean scopes:`

      `Singleton` – only one instance per context. This is the default scope.

      ```java
          public class SingletonBean {
             private static int instanceCount = 0;
             private int id;
         
             public SingletonBean() {
                 instanceCount++;
                 id = instanceCount;
             }
         
             public void display() {
                 System.out.println("Singleton Bean instance id: " + id);
             }
         }
      ```

      `Prototype` – new instance created each time the bean is injected.

      ```java
          public class PrototypeBean {
             private static int instanceCount = 0;
             private int id;
         
             public PrototypeBean() {
                 instanceCount++;
                 id = instanceCount;
             }
         
             public void display() {
                 System.out.println("Prototype Bean instance id: " + id);
             }
         }
      ```

      `Session` – new instance created per user session. Web environment.

      ```java
          import javax.servlet.http.HttpSession;
 
         public class SessionBean {
             private int userId;
         
             public SessionBean(HttpSession session) {
                 this.userId = (int) session.getAttribute("userId");
             }
         
             public void display() {
                 System.out.println("Session Bean: User ID = " + userId);
             }
         }
      ```
      `Request` – new instance created for each request. Web environment.

      ```java
          public class RequestBean {
             private static int instanceCount = 0;
             private int id;
         
             public RequestBean() {
                 instanceCount++;
                 id = instanceCount;
             }
         
             public void display() {
                 System.out.println("Request Bean instance id: " + id);
             }
         }
      ```
      `Custom` – definition of new management rules.

      ```java
         public class CustomScopedBean {
             private static int instanceCount = 0;
             private int id;
         
             public CustomScopedBean() {
                 instanceCount++;
                 id = instanceCount;
             }
         
             public void display() {
                 System.out.println("Custom Scoped Bean instance id: " + id);
             }
         }
      ```
      applicationContext.xml

      ```xml
          <?xml version="1.0" encoding="UTF-8"?>
          <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://www.springframework.org/schema/beans
                 http://www.springframework.org/schema/beans/spring-beans.xsd">
         
             <!-- Singleton Bean -->
             <bean id="singletonBean" class="com.example.SingletonBean" />
         
             <!-- Prototype Bean -->
             <bean id="prototypeBean" class="com.example.PrototypeBean" scope="prototype" />
         
             <!-- Session Bean (Web Environment) -->
             <bean id="sessionBean" class="com.example.SessionBean" scope="prototype" />
         
             <!-- Request Bean (Web Environment) -->
             <bean id="requestBean" class="com.example.RequestBean" scope="prototype" />
         
             <!-- Custom Scoped Bean -->
             <bean id="customScopedBean" class="com.example.CustomScopedBean" scope="prototype" />
         
          </beans>
      ```

      Main.java

       ```java
         import org.springframework.context.ApplicationContext;
         import org.springframework.context.support.ClassPathXmlApplicationContext;
         import javax.servlet.http.HttpSession;
         
         public class Main {
             public static void main(String[] args) {
                 // Assuming we have an HTTP session
                 HttpSession session = new MockHttpSession();
         
                 // Load the Spring context
                 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         
                 // Use beans with different scopes
         
                 // Singleton Bean
                 SingletonBean singleton1 = (SingletonBean) context.getBean("singletonBean");
                 singleton1.display(); // Always displays the same ID
         
                 // Prototype Bean
                 PrototypeBean prototype1 = (PrototypeBean) context.getBean("prototypeBean");
                 prototype1.display(); // Displays a new ID each time
         
                 // Session Bean
                 SessionBean sessionBean1 = (SessionBean) context.getBean("sessionBean", session);
                 sessionBean1.display(); // Displays the user ID in the session
         
                 // Request Bean (Assuming a new HTTP request comes in)
                 RequestBean request1 = (RequestBean) context.getBean("requestBean");
                 request1.display(); // Displays a new ID for each request
         
                 // Custom Scoped Bean (Assuming a custom action requires a new context)
                 CustomScopedBean custom1 = (CustomScopedBean) context.getBean("customScopedBean");
                 custom1.display(); // Displays a new ID based on custom scope logic
         
                 // Close the Spring context
                 ((ClassPathXmlApplicationContext) context).close();
             }
         }
       ```


### Annotation Dependency Injection

- Annotation-based dependency injection is a technique used in software development, particularly in `Java`, to manage the dependencies between different components of an application. This approach relies on annotations to specify how `dependencies` should be injected into objects rather than explicitly `configuring` them through XML or Java configuration files.

- By using injection via `annotations`, XML configuration is reduced to a bare minimum, simplifying dependency management in the application and allowing for better code `flexibility` and `scalability`. This approach also facilitates unit testing and facilitates application modularity by allowing dependencies to be easily replaced with mocks or stubs during testing.

`@Autowired:` This annotation is used to automatically inject beans by matching the type of the bean to be injected with one of the beans declared in the application context. It can be applied to fields, setter methods, and constructors.

   ```java
        public class ExampleService {
        @Autowired
        private SomeDependency dependency;
    }

   ```
- In this example, when Spring instantiates `MyClass`, it looks for an instance of `SomeDependency` in its context and automatically injects it into the dependency attribute.
- Dependency injection is done directly in this attribute when `creating` the class `instance`.

   ```java
        public class MyClass {
        
            private SomeDependency dependency;
        
            @Autowired
            public MyClass(SomeDependency dependency) {
                this.dependency = dependency;
            }
        }
   ```
    ```java
        public class MyClass {
        
            private SomeDependency dependency;
    
             @Autowired
            public void setDependency(SomeDependency dependency) {
                this.dependency = dependency;
            }
        }
   ```
- When using `@Autowired` with a constructor or setter, dependency injection is done at the time the constructor or setter is `called`. This can provide more `flexibility` in some cases, for example if you need dependencies that can be changed `after` the object is `created`.

- `@Component:` This annotation is used to mark a class as a component. When Spring scans the application source code, it detects classes annotated with @Component and automatically instantiates them as beans managed by the Spring container. These beans can then be injected into other classes where they are needed.

<div style="text-align:center;">
  <img src="https://github.com/douaeelh2/Spring-Documentation/assets/127549220/5758e221-86a6-4b13-aa45-7d636f72a975" alt="Component Annotation" style="width:100%">
</div> <br /> <br />


   ```java
        import org.springframework.stereotype.Component;
        @Component
        public class UserRepository {
        }
   ```

   ```java
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        
        @Component 
        public class UserService {
            private final UserRepository userRepository;
        
            @Autowired
            public UserService(UserRepository userRepository) {
                this.userRepository = userRepository;
            }
   
        }
   ```

- `@Repository:` This annotation is used to mark a class as a persistence component in a Spring application. It is generally used for `DAO (Data Access Object)` or repositories to interact with the database.

   ```java
        import org.springframework.stereotype.Repository;

        @Repository
        public class MyRepository {
            // Data access methods
       }

   ```

- `@Service:` By marking a class with @Service, you tell Spring that it should be managed as a service component and be supported for dependency injection.
  Services are generally used to encapsulate the business logic of the application. They perform complex operations and provide functionality to controllers or other services.

    ```java
            import org.springframework.stereotype.Service;

            @Service
            public class MyService {
                // Service methods
            }
    
    ```

- `@Controller:` This annotation is used to mark a class as a controller in a Spring `MVC (Model-View-Controller)` architecture.
  Classes marked with `@Controller` are automatically detected when Spring scans components and are typically used to handle HTTP requests and return responses, often views or JSON data.

    ```java
         import org.springframework.stereotype.Controller;

        @Controller
        public class MyController {
            // Request handling methods
        }

    ```

- `@Configuration:` This annotation is used to mark a class as a source of bean definitions for the Spring container.
  Classes annotated with `@Configuration` can contain methods annotated with `@Bean` to define Spring beans. These beans are then managed by the Spring container.

   ```java
          import org.springframework.context.annotation.Bean;
           import org.springframework.context.annotation.Configuration;
           
           @Configuration
           public class AppConfig {
           
               @Bean
               public UserService userService() {
                   return new UserService();
               }
           
               @Bean
               public UserRepository userRepository() {
                   return new UserRepository();
               }
           }

   ```
- `@Bean:` is used specifically to annotate methods that produce beans for Spring to handle. This annotation tells Spring that the annotated method must be called to create a `bean`, and that the object returned by this method must be managed by the `Spring container`.

- The `@Bean` annotation is used in the Java-based configuration context in Spring. It is mainly used with classes annotated with @Configuration or methods in classes annotated with `@Component`, `@Service`, `@Repository`, etc., to declare the beans that the Spring container should manage.

- `@Scope:` This annotation is used to define the scope of a bean, i.e., the lifespan and visibility of the instance. For example, a bean can be defined as having a singleton scope `(@Scope("singleton"))`, meaning only one instance will be created for the application, or as having a prototype scope `(@Scope("prototype"))`, meaning a new instance will be created every time it's requested.

     ```java
            @Component
            @Scope("prototype")
            public class MyPrototypeBean {
                // class code
            }    
     ```

- `@Qualifier:` This annotation is used to specify which bean should be injected when there are multiple beans of the same type. It is used in conjunction with the @Autowired annotation.

     ```java
           public interface MyInterface {
            void myMethod();
        }
        
        @Component("firstImplementation")
        public class FirstImplementation implements MyInterface {
            public void myMethod() {
                System.out.println("First Implementation");
            }
        }
        
        @Component("secondImplementation")
        public class SecondImplementation implements MyInterface {
            public void myMethod() {
                System.out.println("Second Implementation");
            }
        }

     ```

      ```java
           @Component
            public class MyClient {
            
                @Autowired
                @Qualifier("firstImplementation")
                private MyInterface myInterface;
            
                // Some method using myInterface
            }

     ```

- `@Value:` is used to inject values from external sources such as property files, environment variables, or other Spring beans into fields in your Spring-managed beans. It allows you to externalize configuration from your code, making it more flexible and easier to manage.

     ```java
         @Component
        public class MyBean {
            @Value("${my.property}")
            private String myProperty;
            
            // Other class members and methods
        }
     ```

- `@Required:` is used to indicate that a particular property of a bean must be set during bean initialization. If the property is not set, Spring will throw an exception indicating that the property is required but not configured.

     ```java       
         @Component
        public class MyBean {
            @Required
            private String requiredProperty;
            
            // Other class members and methods
        }
     ```

- `@Lazy:` This annotation is used to defer the initialization of a bean until it is explicitly requested, which can improve performance by delaying object creation until it is needed.

     ```java       
        @Component
        @Lazy
        public class MyLazyBean {
            // class code
        }

     ```

## Aspect-Oriented Programming (AOP)

- `AOP (Aspect-Oriented Programming):` is a programming paradigm that allows separating cross-cutting concerns such as security, logging, transaction management, etc., from the core concerns of the application. Spring Framework provides built-in support for AOP.

Here's a general explanation of how AOP works in Spring:

- `Advices:` Advices are the actions that need to be executed when a specific join point is reached in the program. Advices can be executed before, after, or around the join point.

- `Pointcuts:` Pointcuts define where in the code the advices should be applied. They can be defined using regular expressions, method name patterns, etc.

- `Advice Triggering:` Advices are triggered when the corresponding pointcut is matched during the program's execution.

- `Aspect:` An aspect is a combination of pointcuts and advices. Aspects capture a set of cross-cutting behaviors in a reusable module.

- `Join Points:` Join points are the actual execution points of the program, where the code can be woven by AOP. For example, method invocation, method execution, etc.

- `@Aspect:` This annotation is used to tell Spring that the class is an aspect. In the context of aspect-oriented programming (AOP) with Spring, an aspect is a module that encapsulates a set of cross-cutting concerns, such as logging, security, transaction management, etc.

   ```java       
      // Service to manage user accounts
       public interface UserService {
           void createUser(String username, String password);
           User getUser(String username);
           void deleteUser(String username);
       }
    ```

    ```java 
       // Implementation of the user service
       @Component
       public class UserServiceImpl implements UserService {
           @Override
           public void createUser(String username, String password) {
               // Logic to create a user
           }
       
           @Override
           public User getUser(String username) {
               // Logic to retrieve a user
           }
       
           @Override
           public void deleteUser(String username) {
               // Logic to delete a user
           }
       }
    ```

    ```java
       // Aspect for logging
       @Aspect
       @Component
       public class LoggingAspect {
           @Before("execution(* com.example.UserService.*(..))")
           public void logMethodCall(JoinPoint joinPoint) {
               String methodName = joinPoint.getSignature().getName();
               System.out.println("Method " + methodName + " is called.");
           }
       }
    ```

    - `UserServiceImpl` is a basic service with methods to create, retrieve, and delete users.
    - `LoggingAspect` is an aspect that logs each time a method is called in UserService.
    - The `@Aspect` annotation tells Spring that this class is an aspect.
    - The expression `execution(* com.example.UserService.*(..))` defines the `pointcut`, which matches all methods defined in UserService.
    - The `logMethodCall()` method is an advice defined to execute before the execution of methods matching the pointcut.

## Inversion of Control (IOC)

- `Inversion of Control (IoC)` is one of the core concepts of the Spring Framework. It is often associated with dependency management and the modular software development model. Understanding Inversion of Control is crucial to fully leverage the features provided by Spring.

- The main idea behind Inversion of Control is to reverse the control of dependency management in an application. Rather than having each component of the application responsible for searching and instantiating its dependencies, this responsibility is delegated to an IoC container, such as the Spring container. The container is then responsible for creating and injecting the necessary dependencies into the components.

- Here are some key points to remember about Inversion of Control in the context of Spring:

- `Declarative Dependencies:` Instead of explicitly creating objects within your code, you simply declare your dependencies and let Spring handle their injection.

- `Reduced Coupling:` By letting the IoC container manage dependencies, components become less dependent on each other. This reduces coupling, making your code more modular, easier to test, and maintain.

- `External Configuration:` Spring allows you to configure dependencies and component behaviors using external metadata, such as XML files, annotations, or Java configuration. This separates application configuration, making it more flexible and easier to modify without touching the source code.

- `Dependency Injection:` One of the key mechanisms of IoC is dependency injection, where dependencies required by a component are injected into it by the IoC container. Spring supports various types of dependency injection, including constructor injection, setter injection, and field injection.

- `Object Lifecycle Management:` Spring also manages the lifecycle of objects, allowing developers to define initialization and destruction methods for components, providing better control over object behavior in the application.

- In Spring, the predominant usage is that of loose coupling, and this is closely tied to the concept of Inversion of Control (IoC).

  `Loose Coupling:` refers to a design where components are relatively independent of each other and interact through well-defined interfaces.
    - Components in a loosely coupled system communicate through contracts, such as interfaces or abstract classes, rather than concrete implementations.
    - Changes to one component can be made without affecting other components, as long as the contract remains intact.
    - Loose coupling improves modularity, testability, and maintainability of the system.

- The Spring framework is considered `loosely coupled` because of its `dependency injection` feature.
- In Spring, components are not directly dependent on each other, but rather on abstractions. This means that classes are not directly instantiated within other classes, but rather injected into them, allowing for greater flexibility, easier testing, and reduced dependencies between components. This promotes a more modular and maintainable codebase, making it easier to swap out components or make changes without affecting the entire system.

  ### Example : Dependency Injection via Constructor

  ```java
        public interface MessageService {
            String getMessage();
        }
  ```
  ```java
        @Component
        public class EmailService implements MessageService {
            @Override
            public String getMessage() {
                return "Email message";
            }
        }
  ```
  ```java
        @Component
        public class NotificationService {
            private final MessageService messageService;
        
            @Autowired
            public NotificationService(MessageService messageService) {
                this.messageService = messageService;
            }
        
            public void sendNotification() {
                String message = messageService.getMessage();
                // Logic to send notification
                System.out.println("Sending notification: " + message);
            }
        }
     ```

-  In this example, we have two classes: `EmailService` and `NotificationService`. `NotificationService` requires functionality provided by `MessageService`. However, `NotificationService` doesn't directly create an instance of EmailService. Instead, it relies on Spring's dependency injection mechanism to inject an instance of `MessageService` , which in this case is EmailService.

- This achieves loose coupling because `NotificationService` is not tightly coupled to `EmailService`. It depends on the `abstraction` provided by the `MessageService` interface rather than a specific implementation. This allows for easier modification or substitution of `MessageService` implementations without impacting `NotificationService`.

## Bean Lifecycle

In Spring Framework, the bean lifecycle represents the process by which a bean is created, initialized, used, and eventually destroyed. Understanding this cycle is essential for developing robust and well-structured applications. Here's an overview of the bean lifecycle in Spring:

- `Instantiation:` The Spring container creates an instance of the bean. This can happen by a manufacturer or a factory.

- `Dependency Injection:` The dependencies of the bean are injected. This can be done via setters, constructors or annotated fields.

- `Initialization:` Once all the dependencies are injected, the initialization methods are called. There are two common initialization methods:

The `@PostConstruct` method, annotated with `@PostConstruct`, is called `after` all dependencies are injected, but before the bean is accessible to clients.
The `afterPropertiesSet()` method, implemented by the InitializingBean interface, is called after all of the bean's properties have been set.
Usage: The bean is now ready for use by other application components.

- `Destruction:` When the Spring container closes, it destroys the beans. During this phase, resources can be freed and connections can be closed. Two main methods are used for destruction:

The `@PreDestroy` method, annotated with `@PreDestroy`, is called just before the bean is destroyed.
The `destroy()` method, implemented by the `DisposableBean` interface, is called just before the bean is destroyed.

<img width="909" alt="Bean-Life-Cycle" src="https://github.com/douaeelh2/Spring-Documentation/assets/127549220/60b39ca4-02e9-4be7-8a70-5a1e0bbf8f7c">



  ```java
        import javax.annotation.PostConstruct;
        import javax.annotation.PreDestroy;
        
        public class MyBean {
            
            private String message;
        
            public MyBean() {
                System.out.println("Bean instantiated.");
            }
        
            public void setMessage(String message) {
                this.message = message;
            }
        
            @PostConstruct
            public void init() {
                System.out.println("Bean initialized with message: " + message);
            }
        
            public void doSomething() {
                System.out.println("Bean is doing something...");
            }
        
            @PreDestroy
            public void cleanup() {
                System.out.println("Bean is being destroyed.");
            }
        }

  ```

In this example:

- During `Instantiation`, when the Spring container is initialized, it creates an instance of MyBean, triggering the print statement `Bean instantiated`.

- Then if any dependencies were required, they would be injected via setters, constructors, or annotated fields. Here, we have a `setter` method `setMessage()` which sets the message property.

- Initialization: After the dependencies are injected, the `init()` method annotated with `@PostConstruct` is called, printing "Bean initialized with message: <message>".

- Usage: At this point, the bean is ready to be used. Calling the `doSomething()` method would result in printing "Bean is doing something...".

- Destruction: When the Spring container is shut down, the `cleanup()` method annotated with `@PreDestroy` is called, printing "Bean is being destroyed." This happens during the shutdown process of the Spring container.


## Spring Architecture

![Spring-Architecture](https://github.com/douaeelh2/Spring-Documentation/assets/127549220/e1111657-f343-4d4f-8a73-47ca20cf92bf)

### Core Container
The Core Container provides the fundamental functionality of the Spring framework, including the Inversion of Control (IoC) container and the ApplicationContext. It includes the following modules:

- `Spring Core:` This module provides the fundamental functionality of the Spring framework, including IoC and DI. The IoC container is the heart of the Spring Framework, responsible for creating and managing instances of JavaBeans. It uses dependency injection to wire the beans together.

- `Spring Beans:` This module provides the BeanFactory, which is the basic building block of the IoC container, and the BeanWrapper, which is responsible for managing the lifecycle of a bean. The Bean Factory is the core interface for accessing the IoC container. It provides methods for retrieving beans.

- `Spring Context:` This module provides the ApplicationContext, which is an advanced version of the BeanFactory and provides additional features, such as internationalization and resource loading, and the ability to publish and consume events.

- `Spring Expression Language (SpEL):` This module provides a powerful expression language for querying and manipulating objects during runtime. SpEL supports a wide range of features, including property access, method invocation, conditionals, loops, and type conversion. It also provides support for accessing variables and functions defined in the application context, as well as support for defining custom functions and variables.

### Data Access/Integration
The Data Access/Integration area provides support for integrating with databases and other data sources. It includes the following modules:

- `Spring JDBC:` This module provides a simple JDBC abstraction layer that reduces the amount of boilerplate code required to work with JDBC. Spring JDBC provides support for transaction management, allowing developers to manage database transactions declaratively using Spring’s transaction management.

- `Spring ORM:` This module provides integration with Object-Relational Mapping (ORM) frameworks, such as Hibernate and JPA. Spring ORM provides a higher-level abstraction layer on top of ORM frameworks, allowing developers to write less boilerplate code and more easily integrate ORM technologies with other Spring features, such as transaction management and caching.

- `Spring Data:` This module provides a consistent and easy-to-use programming model for working with data access technologies, including databases, NoSQL, and cloud-based data services. Spring Data provides a wide range of features, including automatic CRUD (Create, Read, Update, Delete) operations, query generation from method names, support for pagination and sorting, integration with Spring’s transaction management, and more. Additionally, Spring Data provides support for common data access patterns, such as repositories and data access objects (DAOs).

- `Spring Transaction:` This module provides support for declarative transaction management in Spring applications. Spring Transaction provides support for various transaction propagation and isolation levels, allowing developers to manage transactions at different levels of granularity. Additionally, Spring Transaction provides support for different transaction management strategies, such as using a JTA transaction manager or a simple JDBC transaction manager.

### Web
The Web area provides support for building web applications. It includes the following modules:
- `Spring MVC:` This module provides a Model-View-Controller (MVC) framework for building web applications. Spring MVC provides a range of features, including support for handling HTTP requests and responses, form handling, data binding, validation, and more. It also provides support for different view technologies, such as JSP (JavaServer Pages), Thymeleaf, and Velocity, allowing developers to choose the view technology that best suits their needs.

- `Spring WebFlux:` This module provides a reactive programming model for building web applications that require high concurrency and scalability. Spring WebFlux provides support for building reactive web applications using a range of technologies, such as Netty, Undertow, and Servlet 3.1+ containers. It also provides a range of features, including support for reactive data access, reactive stream processing, and reactive HTTP clients.

- `Spring Web Services:` This module provides support for building SOAP-based and RESTful web services. Spring Web Services provides support for generating WSDL (Web Services Description Language) from Java classes, and for generating Java classes from WSDL. This allows developers to define the contract (i.e., the interface) of their web service using WSDL, and to generate the Java classes that implement the web service from the WSDL.

### Miscellaneous
The Miscellaneous area includes other modules that provide additional functionality, such as:

- `Spring Security:` This module provides authentication and authorization features for Spring applications. Spring Security provides a range of authorization mechanisms, such as role-based access control and expression-based access control. It also provides support for securing different parts of the application using different security configurations, allowing developers to apply fine-grained security policies.

- `Spring Integration:` This module provides support for building message-driven and event-driven architectures. Spring Integration provides a range of integration patterns, such as messaging, routing, and transformation. It provides support for a range of messaging systems, such as JMS, AMQP, and Apache Kafka. It also provides support for integrating with different protocols, such as FTP, HTTP, and TCP.

- `Spring Batch:` This module provides support for batch processing and integration with enterprise systems. Spring Batch provides a range of tools and utilities for building and managing batch processing applications, such as support for testing and debugging batch jobs, logging and monitoring, and integration with other Spring modules, such as Spring Data and Spring Integration.

- `Spring Cloud:` This module provides support for building cloud-native applications using Spring technologies. Spring Cloud provides a range of features for building cloud-native applications, such as service discovery, configuration management, and load balancing. It provides support for integrating with different cloud platforms, such as AWS and GCP, and for using different cloud-native technologies, such as containers and serverless computing.
  Overall, the Spring framework modules provide developers with a powerful set of tools to build robust, scalable, and maintainable enterprise applications. The modular architecture of the Spring framework allows developers to select only the necessary modules for their specific needs, reducing unnecessary overhead and complexity in the application.

# References
- [Java Guides](https://www.javaguides.net/)
- [GeeksforGeeks](https://www.geeksforgeeks.org/)
