package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Aspect
@Configuration
@Slf4j
public class RespositoryAccessLogAop {

    @Around("execution(* hello.repository.*.*(..))")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        Date startDate = new Date();
        Object result = joinPoint.proceed();
        Date endDate = new Date();
        log.debug("Respository took " + (endDate.getTime() - startDate.getTime() + " milliseconds"));
        return result;
    }
}
