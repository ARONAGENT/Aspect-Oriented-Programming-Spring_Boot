package com.springJourney.Aspect_Oriented_Programming.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectAdvices {

    //Before Advice
    /*Runs before the matched method executes.
    Provides access to the JoinPoint, allowing retrieval of method details such as name and arguments.*/

    @Before("adviceImplPointCut()")
    public void beforeLogStatement(JoinPoint joinPoint){
        log.info("Before Method called signature  : {}", joinPoint.getSignature());
        log.info("Before Method called Kind  : {}", joinPoint.getKind());
    }


    /*  @After Advice
     Runs after the method completes execution, whether it returns normally or throws an exception.*/

    @After("adviceImplPointCut()")
    public void AfterLogStatement(JoinPoint joinPoint){
        log.info("After Method called signature  : {}", joinPoint.getSignature());
        log.info("After Method called Kind  : {}", joinPoint.getKind());
    }


    /*  @AfterReturning Advice
        Runs only after a method successfully completes execution.
        Can be used to access the return value of the method.   */

    @AfterReturning(value = "adviceImplPointCut()", returning = "returnValue")
    public void afterReturningLogStatement(JoinPoint joinPoint, Object returnValue){
        log.info("After Returning Method called signature  : {}", joinPoint.getSignature());
        log.info("After Returning Method return value  : {}", returnValue);
    }

    /*     @AfterThrowing Advice
        Runs only if the method throws an exception.
        Useful for logging errors or handling exceptions globally.*/


    @AfterThrowing(value = "adviceImplPointCut()")
    public void afterThrowingLogStatement(JoinPoint joinPoint){
        log.info("After Throwing  Method called signature  : {}", joinPoint.getSignature());
    }


    /*@Around Advice
        Wraps around a method execution.
        Can be used to modify the method’s behavior by executing code before and after it runs.
        Allows returning custom values, throwing exceptions, or proceeding with the original
        method execution.
    * */

    @Around("adviceImplPointCut()")
    public Object validateId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object args[]= proceedingJoinPoint.getArgs();
        Long Id=(Long)args[0];

        if(Id>0) return proceedingJoinPoint.proceed();

        return "Cannot call method with negative Id ";
    }

    @Pointcut("execution(* com.springJourney.Aspect_Oriented_Programming.Services.impl.*.*(..))")
    public void adviceImplPointCut(){}

}
