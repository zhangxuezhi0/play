/*
 *
 * FileName: BaseException
 * Author:   zhangxz
 * Date:     2019/11/7
 */
package simple.proj.zxz.play.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 基类异常
 *
 * @author zhangxz
 * 2019/11/7
 */
public class BaseException extends NestedRuntimeException {

    public BaseException(String msg) {
        super(msg);
    }
}
