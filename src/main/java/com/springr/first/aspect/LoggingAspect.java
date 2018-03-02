package com.springr.first.aspect;


import com.springr.first.service.processXls.base.ExcelDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect //@AspectJ annotációk, de nem annak a framework-je, a spring-aop-ot használja
//@Component //ha ezt kikommentezed, akkor kikapcsol az aspect
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
        target method run
        @Around: proceed után
        @After
        @AfterReturning/@AfterThrowing



    wildcard: * és a ..

    ("execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))") // .. -al az összes overloadoltra is
    ("execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(String))") // csak a stringes-re, bármilyen visszatérési érték
    ("execution(Integer com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(Double))") // csak arra, ami Integert ad vissza, de Double-t vár
    ("execution(* com.springr.first.service.storage.*.convertFileToDTO(..))") // wildcard az osztályokra

    ("execution(* *.*(..))") // minden metódus hívásnál

     */


    @Before("execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))") // csak ennél
    public void logBefore(final JoinPoint joinPoint) {
        log.info("Before");
    }


    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice() {
        log.info("Before executing service method");
    }

    // pointcut-tal befogható, és itt bent már elég a metódus nevet meghivatkozni, és használni
   // @Pointcut("within(com.springr.first.service..*))") // sub package-re is
    @Pointcut("within(com.springr.first.service.storage.*))") // minden metódusra ami a storage service-ben van
    public void allMethodsPointcut() {
    }


    @After("execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))")
    public void logAfter() {
        log.info("After");
    }


    @AfterThrowing(pointcut = "execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex) {
        log.info("ex dobva:" + ex.toString());
        log.info("Exception thrown in  Method=" + joinPoint.toString());
    }


    @AfterReturning(value = "execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))", returning = "excelReturned")
    public void getNameReturningAdvice(ExcelDTO<?> excelReturned) {
        log.info("Processed Xls=" + excelReturned.getTitle());
    }


    @Around("execution(* com.springr.first.service.processXls.base.ProcessXls.convertFileToDTO(..))")
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



    // target: class
    // this: type of the bean


}
