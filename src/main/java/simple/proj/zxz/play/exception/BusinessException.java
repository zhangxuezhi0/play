/*
 *
 * FileName: BusinessException
 * Author:   zhangxz
 * Date:     2019/11/7
 */
package simple.proj.zxz.play.exception;

/**
 * 业务异常
 *
 * @author zhangxz
 * 2019/11/7
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super("业务异常！");
    }

    public BusinessException(String msg) {
        super(msg);
    }
}
