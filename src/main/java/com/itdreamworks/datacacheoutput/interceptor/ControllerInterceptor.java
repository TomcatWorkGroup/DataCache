package com.itdreamworks.datacacheoutput.interceptor;

import com.itdreamworks.datacacheoutput.annotation.Permission;
import com.itdreamworks.datacacheoutput.service.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

//@Aspect
//@Component
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
    @Value("${token.user}")
    String userTokenName;
    @Autowired
    TokenService tokenService;

    @Pointcut("execution(* com.itdreamworks.datacacheoutput.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    //@Around("controllerMethodPointcut()")
    @Around("@annotation(com.itdreamworks.datacacheoutput.annotation.Permission)")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("用户登录拦截......");
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(isLogin(request)){
            result = pjp.proceed();
        }else{
           // result = "{\"code\":0,\"msg\":\"token无效！\"}";
        }
        return result;
    }

    /**
     * 判断一个方法是否需要登录
     * @param method
     * @return
     */
    private boolean isLoginRequired(Method method){
//        if(!env.equals("prod")){ //只有生产环境才需要登录
//            return false;
//        }

        boolean result = true;
        if(method.isAnnotationPresent(Permission.class)){
            result = method.getAnnotation(Permission.class).loginReqired();
        }
        return result;
    }

    //判断是否已经登录
    private boolean isLogin(HttpServletRequest request) {
        Cookie tokenCookie = WebUtils.getCookie(request, userTokenName);
        return tokenService.verifyUserToken(tokenCookie);
    }
}
