package com.broken.line.component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: wanjia1
 * @date: 2023/6/12
 * aspect pointCut & advice
 * https://blog.csdn.net/mu_wind/article/details/102758005
 */
@Aspect
@Slf4j
@Component
public class MetricAspect {

    /**
     * 定义pointCut
     */
//    @Pointcut("@annotation(com.broken.line.component.CustomizeMetric)")
//    private void customizeMetricPointCut() {
//    }

    /**
     * BeforeAdvice
     * AfterAdvice
     * AroundAdvice
     * AfterThrowingAdvice
     */
//    @Before("@annotation(customizeMetric)")
//    public void beforeAdvice(JoinPoint joinPoint, CustomizeMetric customizeMetric) {
//        log.info("before advice {}", Thread.currentThread().getName());
//    }
//
//    @After("@annotation(customizeMetric)")
//    public void afterAdvice(JoinPoint joinPoint, CustomizeMetric customizeMetric) {
//        log.info("after advice {}", Thread.currentThread().getName());
//    }
    @Around("@annotation(customizeMetric)")
    @SneakyThrows
    public Object roundAdvice(ProceedingJoinPoint joinPoint, CustomizeMetric customizeMetric) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        log.info("before around advice {}", Thread.currentThread().getName());

        Timer timer = Metrics.timer("method.cost.time", "method.name", method.getName());
        ThrowableHolder holder = new ThrowableHolder();
        Object obj = timer.recordCallable(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                holder.throwable = e;
            }
            return null;
        });
        log.info("after around advice {}", Thread.currentThread().getName());
        if (Objects.nonNull(holder.throwable)) {
            throw holder.throwable;
        }
        return obj;
    }

    @AfterReturning(value = "@annotation(customizeMetric)", returning = "result")
    public void afterReturn(JoinPoint joinPoint, CustomizeMetric customizeMetric, Object result) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        log.info("after return advice {}", Thread.currentThread().getName());
        final Counter counter = Metrics.counter("method.success.counter", "method.name", method.getName());
        counter.increment();
    }

    @AfterThrowing(pointcut = "@annotation(customizeMetric)", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, CustomizeMetric customizeMetric, Throwable throwable) {
        log.info("after throw advice {}", Thread.currentThread().getName());
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        final Counter counter = Metrics.counter("method.error.counter", "method.name", method.getName());
        counter.increment();
    }

    private static class ThrowableHolder {
        Throwable throwable;
    }
}
