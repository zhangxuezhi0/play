package simple.proj.zxz.play.exception;

import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import simple.proj.zxz.play.pojo.vo.comm.CommOutVO;

/**
 * 全局异常处理
 *
 * @author Zxz
 * @version 1.0
 * @date Created at 2019/04/23
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object defaultExceptionHandler(Exception globalException) {

        log.error("全局异常处理：" + globalException.getMessage(), globalException);

        if (globalException instanceof BaseException) {
            //自定义异常
            if (globalException instanceof BusinessException) {
                //已知的业务异常
                return CommOutVO.getBusinessException(globalException.getMessage());
            } else {
                //未知的业务异常
                return CommOutVO.getBusinessException("未知类型的业务异常！");
            }
        } else if (isReqDataFormatError(globalException)) {
            //请求数据格式有误，分以下三种情况
            String message = globalException.getMessage();
            try {
                if (globalException instanceof MethodArgumentNotValidException) {
                    //hibernate validator验证报错
                    message = ((MethodArgumentNotValidException) globalException).getBindingResult().getAllErrors().get(0).getDefaultMessage();
                } else if (globalException instanceof HttpMessageNotReadableException) {
                    //spring验证报错
                    message = ((HttpMessageNotReadableException) globalException).getCause().getMessage();
                } else if (globalException instanceof JSONException) {
                    //fastJson验证报错
                    message = ((JSONException) globalException).getCause().toString();
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return CommOutVO.getReqDataFormatError(message);
        } else {
            //系统错误
            return CommOutVO.getSystemError();
        }

    }

    /**
     * 判断异常是否为请求数据格式有问题的异常
     *
     * @param globalException 异常信息
     * @return boolean
     * @author Zxz
     * @date 2019/11/8 10:10
     **/
    private boolean isReqDataFormatError(Exception globalException) {
        return globalException instanceof MethodArgumentNotValidException
                || globalException instanceof HttpMessageNotReadableException
                || globalException instanceof JSONException;
    }

}
