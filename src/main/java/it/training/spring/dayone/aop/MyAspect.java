package it.training.spring.dayone.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class MyAspect {

    //@Pointcut("execution(int it.training.spring.dayone.MyBean.*(..))")
    @Pointcut("@annotation(it.training.spring.dayone.aop.MyAudit)")
    public void myPC() throws Throwable {}

    /*@Before("myPC()")
    public void before(JoinPoint jp) throws Throwable {
        System.out.println("[Before] Spring AOP -> "+jp.getSignature());
        Object[] args = jp.getArgs();
        for (int i=0; i<args.length; i++) {
            System.out.println("- arg"+(i+1)+": "+args[i]);
        }
    }*/

    @Around("myPC()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Around] Spring AOP -> "+pjp.getSignature());
        Object[] args = pjp.getArgs();
        Object[] newArgs = new Object[args.length];

        for (int i=0; i<args.length; i++) {
            System.out.println("- arg" + (i + 1) + ": " + args[i]);
            newArgs[i] = (int) args[i]*2;
        }

        Object returnValue = pjp.proceed();
        //Object returnValue = pjp.proceed(newArgs);
        //Object returnValue = 1000;

        return returnValue;
    }


}
