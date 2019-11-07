package simple.proj.zxz.play.exception;

import lombok.extern.slf4j.Slf4j;
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
    public Object defaultExceptionHandler(Exception e) {

        log.error("全局异常处理：" + e.getMessage(), e);

        if (e instanceof BaseException) {
            if (e instanceof BusinessException) {
                //已知的业务异常
                return CommOutVO.getBusinessException(e.getMessage());
            } else {

                //未知的业务异常
                return CommOutVO.getBusinessException("未知类型的业务异常！");
            }
        } else {
            //系统错误
            return CommOutVO.getSystemError();
        }

    }

}
