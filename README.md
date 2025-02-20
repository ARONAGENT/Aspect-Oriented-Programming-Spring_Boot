# Aspect Oriented Programming (AOP) in Spring Boot

## Setting Up the Project
To start using Aspect-Oriented Programming (AOP) in a Spring Boot application, follow these steps:

1. **Create a Spring Boot Project**
   - Use Spring Initializr (https://start.spring.io/) to generate a Spring Boot project.
   - Select dependencies:
     - Spring Web
     - Spring AOP or Spring Data JPA
   - Download and extract the generated project.

2. **Import the Project into an IDE**
   - Open IntelliJ IDEA, Eclipse, or VS Code.
   - Import the project as a Maven project.
   - Ensure the dependencies are correctly installed.

---

## 1. Introduction to Aspect-Oriented Programming (AOP)

### 1.1 Cross-Cutting Concerns
Cross-cutting concerns are aspects of a program that affect multiple modules but cannot be modularized using traditional Object-Oriented Programming (OOP). AOP helps in modularizing such concerns without entangling the core business logic.

### 1.2 AOP in Spring
AOP is used to separate concerns in an application. It provides a way to modularize cross-cutting concerns like logging, security, and transaction management.

### 1.3 Benefits of AOP
- Cleaner and more focused code.
- Reduced code duplication.
- Enhanced modularity.
- Loose coupling and increased flexibility.

### 1.4 Key Concepts in AOP
- **Aspect**: A module encapsulating a cross-cutting concern.
- **Join Point**: A specific point in the program execution where additional behavior can be injected.
- **Advice**: The actual code that runs when a Join Point is reached.
- **Pointcut**: Expressions that define which Join Points should be intercepted.
- **Weaving**: The process of integrating aspects into the target code.

---

## 2. Declaring Pointcuts in AOP

### 2.1 Pointcuts in Spring AOP
Pointcuts define where advice should be applied. They use AspectJ expressions and can be declared using annotations or XML configurations.

[Official Documentation](https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/pointcuts.html)

### 2.2 Types of Pointcuts
- **Execution Pointcut**: Targets specific method executions.
 <br> ![00 Pointcut (Execution)](https://github.com/user-attachments/assets/539e567a-c272-4f8a-ac57-f4670e53916b)
- **Within Pointcut**: Limits advice to a specific class or package.
 <br> ![01 Pointcut (Within)](https://github.com/user-attachments/assets/49317017-3da8-4e11-80c0-19e5988662a0)
- **Annotation Pointcut**: Applies advice to methods annotated with a specific annotation.
 <br> ![02 Pointcut (Annotations)](https://github.com/user-attachments/assets/b87f9e40-55b4-41eb-99b1-3ed8c0683c40)
- **Named Pointcuts**: Reusable pointcut expressions defined using `@Pointcut`.
 <br> ![03 Pointcut Declaration](https://github.com/user-attachments/assets/534eb8df-c278-42bb-a35e-53e9c9ab781f)
  
---

## 3. Declaring Advices in AOP

### 3.1 Advice Types in Spring AOP
Spring AOP provides various types of advice:

[Official Documentation](https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/advice.html)

1. **@Before Advice**
   - Runs before the method execution.
   
   ```java
   @Before("execution(* com.example.service.*.*(..))")
   public void beforeAdvice(JoinPoint joinPoint) {
       System.out.println("Executing Before Advice on " + joinPoint.getSignature());
   }
   ```
   <br> **Execution ->**
   
   ![04 Advices (@Before)](https://github.com/user-attachments/assets/bc1a0cc9-964d-4484-bd85-b63f773ab799)

2. **@After Advice**
   - Runs after the method execution (regardless of success or failure).

   ```java
   @After("execution(* com.example.service.*.*(..))")
   public void afterAdvice(JoinPoint joinPoint) {
       System.out.println("Executing After Advice on " + joinPoint.getSignature());
   }
   ```
   <br> **Execution ->**
   
   ![05 Advices (@After)](https://github.com/user-attachments/assets/72a68c7c-ddd9-4360-b271-52e0baccc4e9)

3. **@AfterReturning Advice**
   - Runs only after successful execution of the method.

   ```java
   @AfterReturning(value = "adviceImplPointCut()", returning = "returnValue")
    public void afterReturningLogStatement(JoinPoint joinPoint, Object returnValue){
        log.info("After Returning Method called signature  : {}", joinPoint.getSignature());
        log.info("After Returning Method return value  : {}", returnValue);
    }
   ```
   <br> **Execution ->**
   
   ![06 Advices (@AfterReturning)](https://github.com/user-attachments/assets/a1a4f076-6d44-4c85-ac5c-5b1201738b07)

4. **@AfterThrowing Advice**
   - Runs only when an exception is thrown.

   ```java
   @AfterThrowing(value = "adviceImplPointCut()")
    public void afterThrowingLogStatement(JoinPoint joinPoint){
        log.info("After Throwing  Method called signature  : {}", joinPoint.getSignature());
    }
   ```
   <br> **Execution ->**
   
   ![07 Advices (@AfterThrowing)](https://github.com/user-attachments/assets/751e4d57-858e-4be7-9748-373ac6568bfe)

5. **@Around Advice**
   - Provides complete control over method execution.
   ```java
     @Around("adviceImplPointCut()")
    public Object validateId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object args[]= proceedingJoinPoint.getArgs();
        Long Id=(Long)args[0];
        if(Id>0) return proceedingJoinPoint.proceed();
        return "Cannot call method with negative Id ";
    }
   ```
   <br> **Execution ->**
   
   ![08 Advices (@Around)](https://github.com/user-attachments/assets/6f63a098-5503-4951-9982-2abb85c0353b)

---

## 4. Spring Proxy and Internal Working of AOP

### 4.1 Spring Proxy
Spring uses proxies to apply AOP features dynamically.
- Proxies are created only if a method matches a pointcut.
- Spring uses proxies for @Transactional, @Cacheable, and @Async functionalities.

### 4.2 How Proxies are Managed
- When Spring detects a need for a proxy, it creates one and stores it in the `ApplicationContext`.
- Clients receive the proxy object instead of the original bean.
- The proxy intercepts method calls and applies advice when needed.

### 4.3 How Proxies are Created
- **JDK Dynamic Proxies**: Used when the target object implements interfaces.
- **CGLIB Proxies**: Used when the target object does not implement any interface.

### 4.4 How Proxies are Used
1. Spring scans for aspects and applies advice based on pointcuts.
2. If a method matches a pointcut, a proxy is created.
3. When a method is called on a proxied bean, the proxy intercepts the call.
4. If no advice is applicable, the proxy calls the original method directly.
5. If advice exists, it is executed before, after, or around the original method.

### 4.5 Weaving in AOP
- **Compile-time Weaving**: Aspects are woven during compilation.
- **Load-time Weaving**: Aspects are woven when classes are loaded into the JVM.
- **Post-compilation Weaving**: Aspects are woven after compilation.
- Spring primarily uses dynamic proxies but can use AspectJ for advanced weaving.

---

## 5. Real-World Use Cases of AOP

### 5.1 Common AOP Use Cases
1. **Security Aspect**
   - Applying security rules using aspects.
   
2. **Caching Aspect**
   - Automatically caching method results.
   
3. **Auditing Aspect**
   - Logging method calls for auditing.
   
4. **Exception Handling Aspect**
   - Handling exceptions in a centralized manner.

### 5.2 Do We Really Need AOP for These?
- **Transactional**: @Transactional is sufficient for database transactions.
- **Security**: @Secured and @PreAuthorize simplify security concerns.
- **Validation**: @Valid provides built-in input validation.
- **Caching**: @Cacheable integrates seamlessly with Spring's caching abstraction.

### 5.3 Where Should AOP Be Used?
AOP is best used for:
- Advanced logging.
- Monitoring and profiling.
- Cross-cutting concerns that affect multiple layers of the application.
- Exception handling, auditing, and caching where built-in methods are insufficient.


This document provides an in-depth understanding of AOP in Spring Boot and guides you in setting up and using AOP efficiently.

