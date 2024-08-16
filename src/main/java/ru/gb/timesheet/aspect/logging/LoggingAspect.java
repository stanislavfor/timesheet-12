package ru.gb.timesheet.aspect.logging;
//
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class LoggingAspect {
//
//    private final LoggingProperties properties;
//
//    public LoggingAspect(LoggingProperties properties) {
//        this.properties = properties;
//    }
//
//    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
//    public void timesheetServiceMethodsPointcut() {}
//
//    @After("timesheetServiceMethodsPointcut()")
//    public void logMethodCall(JoinPoint joinPoint) {
//        StringBuilder message = new StringBuilder(joinPoint.getSignature().getName() + "(");
//        Object[] args = joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            if (i > 0) message.append(",");
//            message.append(args[i].getClass().getSimpleName()).append("=").append(args[i]);
//        }
//        message.append(")");
//        log.info("TimesheetService.{}", message.toString());
//    }
//
//    @Pointcut("@annotation(ru.gb.timesheet.aspect.logging.Logging)")
//    public void loggingMethodsPointcut() {}
//
//    @Pointcut("@within(ru.gb.timesheet.aspect.logging.Logging)")
//    public void loggingTypePointcut() {}
//
//    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
//    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
//        String methodName = pjp.getSignature().getName();
//        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
//        try {
//            return pjp.proceed();
//        } finally {
//            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
//        }
//    }
//}
//package ru.gb.timesheet.aspect.logging;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private LoggingProperties properties;

    public LoggingAspect(@Qualifier("loggingProperties") LoggingProperties properties) {
        this.properties = properties;
    }

    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut() {}

    @After("timesheetServiceMethodsPointcut()")
    public void logMethodCall(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder(joinPoint.getSignature().getName() + "(");
        Object[] args = joinPoint.getArgs();
        if (properties.isPrintArgs()) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) message.append(", ");
                message.append(args[i].getClass().getSimpleName()).append("=").append(args[i]);
            }
        }
        message.append(")");
        log.info("TimesheetService.{}", message.toString());
    }

    @Pointcut("@annotation(ru.gb.timesheet.aspect.logging.Logging)")
    public void loggingMethodsPointcut() {}

    @Pointcut("@within(ru.gb.timesheet.aspect.logging.Logging)")
    public void loggingTypePointcut() {}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        if (properties.isPrintArgs()) {
            log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        }
        try {
            return pjp.proceed();
        } finally {
            if (properties.isPrintArgs()) {
                log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
            }
        }
    }
}
