package com.springJourney.Aspect_Oriented_Programming.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // execution PointCut
    /*
        This is the most widely used pointcut expression. It allows you to target
        method executions in your classes.
    */
    @Before("execution(* com.springJourney.Aspect_Oriented_Programming.Services.impl.AronServiceImpl.*(..))")
    public void beforeLogStatement(JoinPoint joinPoint){
        log.info("Before Method called signature  : {}", joinPoint.getSignature());
        log.info("Before Method called Kind  : {}", joinPoint.getKind());
    }

    // within PointCut
    /*
          Use within() when you want to limit the advice to a particular class or
        package, without focusing on specific methods. This pointcut applies to any
        join point within the com.springJourney.Aspect_Oriented_Programming.Services.impl
         package, including methods, fields, and constructors.
     */
    @Before("within(com.springJourney.Aspect_Oriented_Programming.Services.impl.*)")
    public void withinLogStatement(JoinPoint joinPoint){
        log.info("Within Method called signature  : {}", joinPoint.getSignature());
        log.info("Within Method called Kind  : {}", joinPoint.getKind());
    }


    // @annotation pointCut
    /*
        Use @annotation() when you want to apply advice to methods annotated with a
        particular annotation. You can also create your custom annotations for this
        pointcut.
        In this ex. I apply point cut on @Transactional Annotation
    */

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeAnnotationLogStatement(JoinPoint joinPoint){
        log.info("Before Annotation Method called signature  : {}", joinPoint.getSignature());
        log.info("Before Annotation Method called Kind  : {}", joinPoint.getKind());
    }

    //PointCut Declaration efficient way
    /*
    * The pointcut is defined separately using @Pointcut and then referenced by
    the advice annotations.*/

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactAnnotation(){}

    @Before("transactAnnotation()")
    public void beforeTransactLogStatement(JoinPoint joinPoint){
        log.info("Before Transaction Method called signature  : {}", joinPoint.getSignature());
        log.info("Before Transaction Method called Kind  : {}", joinPoint.getKind());
    }
    @After("transactAnnotation()")
    public void afterTransactLogStatement(JoinPoint joinPoint){
        log.info("After Transaction Method called signature  : {}", joinPoint.getSignature());
        log.info("After Transaction Method called Kind  : {}", joinPoint.getKind());
    }




}
