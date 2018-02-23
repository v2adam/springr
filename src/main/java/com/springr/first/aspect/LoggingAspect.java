package com.springr.first.aspect;


import com.springr.first.misc.ExcelDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/aop.html

    /*

        @Before: előtte
        @After: utána, mindig lefut, olyan mint a finally
        @AfterReturning: csak akkor ha ok volt
        @AfterThrowing: ha exception-t dobott
        @Around: az egészet elérjük, de nekünk kell visszaadni értéket ha nem void


        // lefutási sorrend
        @Around: proceed előtt
        @Before
        @Around: proceed után
        @After
        @AfterReturning/@AfterThrowing

     */


    @Before("execution(* com.springr.first.service.storage.ProcessXls.convertFileToDTO(..))") // csak ennél
    public void logBefore(final JoinPoint joinPoint) {
        log.info("Before");
    }


    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice() {
        log.info("Before executing service method");
    }

    // minden service hívásnál
    // pointcut-tal befogható, és itt bent már elég a metódus nevet meghivatkozni, és használni
    @Pointcut("within(com.springr.first.service.*))")
    public void allMethodsPointcut() {
    }


    @After("execution(* com.springr.first.service.storage.ProcessXls.convertFileToDTO(..))")
    public void logAfter() {
        log.info("After");
    }


    @AfterThrowing("execution(* com.springr.first.service.storage.ProcessXls.convertFileToDTO(..))")
    public void logExceptions(JoinPoint joinPoint) {
        log.info("Exception thrown in  Method=" + joinPoint.toString());
    }


    @AfterReturning(value = "execution(* com.springr.first.service.storage.ProcessXls.convertFileToDTO(..))", returning = "excelReturned")
    public void getNameReturningAdvice(ExcelDTO<?> excelReturned) {
        log.info("Processed Xls=" + excelReturned.getTitle());
    }


    @Around("execution(* com.springr.first.service.storage.ProcessXls.convertFileToDTO(..))")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("Before method invoke");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
            if (value instanceof ExcelDTO<?>) {
                ExcelDTO<?> modified = (ExcelDTO<?>) value;
                modified.setTitle("más lesz a neved");
                value = modified;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("After invoking method. Return value=" + value);
        return value;
    }


    // saját annotációk ide irányítanak
    @After("@annotation(com.springr.first.aspect.MyLoggable)")
    public void myAdvice() {
        log.info("Executing myAdvice annotation!!");
    }

}
