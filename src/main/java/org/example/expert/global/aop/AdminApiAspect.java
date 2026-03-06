package org.example.expert.global.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AdminApiAspect {

    // 경로 지정
    @Around("execution(* org.example.expert.domain.comment.controller.CommentAdminController.deleteComment(..)) ||" +
            "execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public Object logAdminApi(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalDateTime requestTime = LocalDateTime.now();

        log.info("[ API 요청 시간 ] : {}", requestTime);

        Object[] args = joinPoint.getArgs();
        log.info("RequestBody : {}", Arrays.toString(args));

        Object result = joinPoint.proceed();

        log.info("RequestBody : {}", result);

        return result;
    }
}