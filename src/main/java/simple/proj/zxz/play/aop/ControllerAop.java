/*
 *
 * FileName: ControllerAop
 * Author:   zhangxz
 * Date:     2019/11/6
 */
package simple.proj.zxz.play.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import first.zxz.tools.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import simple.proj.zxz.play.pojo.entity.LogEntity;
import simple.proj.zxz.play.pojo.vo.FieldVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;

/**
 * 控制器层的aop
 *
 * @author zhangxz
 * 2019/11/6
 */


@Aspect
@Component
@Slf4j
public class ControllerAop {

    //get方法类型
    private static final String METHOD_TYPE_GET = "GET";

    //controller层的aop配置

    //配置注解
    //@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")

    //配置指定包下的类，不包含子包
    //@Pointcut("execution(* simple.proj.zxz.play.controller.acg.*.*(..))")

    //配置指定包下的类，包含所有子包的类
    @Pointcut("execution(* simple.proj.zxz.play.controller..*.*(..))")
    public void controllerAspect() {
    }


    /**
     * 切面方法，在方法执行期间，记录方法的入参，返回结果，执行时长等信息
     *
     * @param joinPoint 切面数据
     * @return java.lang.Object
     * @author Zxz
     * @date 2019/11/7 11:46
     **/
    @Around(value = "controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        LogEntity logEntity = new LogEntity();

        logEntity.setCreateTime(new FieldVO<>(new Date()));
        logEntity.setReqData(getReqDataStr(joinPoint));
        logEntity.setRemoteIp(getIp(request));

        logEntity.setReqApi(request.getRequestURL().toString());
        logEntity.setReqType(request.getMethod());

        logEntity.setReqMethod(joinPoint.getSignature().toShortString());

        try {
            Object proceed = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            logEntity.setMethodHandleMilliseconds(endTime - startTime);

            if (!isIdempotentMethod(request.getMethod())) {
                logEntity.setResult(proceed.toString());
            }

            log.info("controller request data: " + JSON.toJSONString(logEntity, true));

            return proceed;
        } catch (Throwable throwable) {

            long endTime = System.currentTimeMillis();
            logEntity.setMethodHandleMilliseconds(endTime - startTime);

            logEntity.setErrorMsg(throwable.getMessage());
            log.info("controller request data: " + JSON.toJSONString(logEntity, true));

            throw throwable;
        }


    }

    /**
     * 判断方法类型是否为幂等性的方法
     * 只有类型为GET的方法，才是幂等性的。
     *
     * @param methodType 方法类型
     * @return boolean
     * @author Zxz
     * @date 2019/11/7 11:43
     **/
    private boolean isIdempotentMethod(String methodType) {
        if (StringUtil.isEmptyAndBlank(methodType)) {
            return false;
        }
        return methodType.trim().toUpperCase().equals(METHOD_TYPE_GET);
    }

    /**
     * 获取请求的ip地址
     *
     * @param request 请求
     * @return java.lang.String
     * @author Zxz
     * @date 2019/11/7 11:39
     **/
    private static String getIp(HttpServletRequest request) {
        if (request == null)
            return null;
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        return ip;
    }

    /**
     * 获取请求参数，构造成字符串
     *
     * @param joinPoint 数据
     * @return java.lang.String
     * @author Zxz
     * @date 2019/11/6 17:37
     **/
    private String getReqDataStr(JoinPoint joinPoint) {
        StringBuilder result = new StringBuilder();
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length <= 0) {
            return result.toString();
        }
        for (Object arg : joinPoint.getArgs()) {
            if (isInputString(arg)) {
                String jsonString = JSON.toJSONString(arg,
//                        SerializerFeature.NotWriteDefaultValue,
                        SerializerFeature.DisableCircularReferenceDetect,
                        SerializerFeature.WriteClassName);
                result.append(jsonString).append(",");
            }
        }

        //去除最后一个逗号
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * 判断对象是否是用户输入的数据
     *
     * @param arg 对象
     * @return boolean
     * @author Zxz
     * @date 2019/11/6 17:40
     **/
    private boolean isInputString(Object arg) {
        return !(arg instanceof HttpServletRequest)
                && !(arg instanceof HttpServletResponse)
                && !(containsMultipartFile(arg));
    }

    /**
     * 判断是否包含MultipartFile类型数据
     *
     * @param object 数据
     * @return boolean
     * @author Zxz
     * @date 2019/11/6 17:37
     **/
    private boolean containsMultipartFile(Object object) {
        if (object instanceof MultipartFile) {
            return true;
        }
        if (object instanceof Collection) {
            Collection list = (Collection) object;
            for (Object o : list) {
                if (o instanceof MultipartFile) {
                    return true;
                }
            }
        }
        return false;
    }

}
