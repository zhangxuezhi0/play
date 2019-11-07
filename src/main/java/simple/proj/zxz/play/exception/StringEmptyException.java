/*
 *
 * FileName: StringEmptyException
 * Author:   zhangxz
 * Date:     2019/11/7
 */
package simple.proj.zxz.play.exception;

/**
 * 字符串空异常
 *
 * @author zhangxz
 * 2019/11/7
 */
public class StringEmptyException extends BusinessException {
    public StringEmptyException() {
        super("字符串不能为空！");
    }
}
