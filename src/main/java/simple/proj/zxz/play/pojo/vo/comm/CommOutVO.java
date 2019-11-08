package simple.proj.zxz.play.pojo.vo.comm;

import first.zxz.tools.StringUtil;
import lombok.Data;
import simple.proj.zxz.play.comm.StatusCode;
import simple.proj.zxz.play.exception.StringEmptyException;

/**
 * 统一出参配置
 *
 * @author zhangxz
 * 2019/10/25
 */

@Data
public class CommOutVO<T> {

    private int code;

    private String message;

    private T data;

    public CommOutVO(T data) {
        this.data = data;
        this.code = StatusCode.SUCCESS;
    }

    //构造方法私有化，单例模式
    private CommOutVO() {
    }

    //返回成功
    private static final CommOutVO SUCCESS_INSTANCE = new CommOutVO();
    //返回未授权
    private static final CommOutVO NOT_AUTH_INSTANCE = new CommOutVO();
    //系统错误
    private static final CommOutVO SYS_ERROR_INSTANCE = new CommOutVO();

    static {
        SUCCESS_INSTANCE.code = StatusCode.SUCCESS;
        NOT_AUTH_INSTANCE.code = StatusCode.NOT_AUTH;
        SYS_ERROR_INSTANCE.code = StatusCode.SYSTEM_ERROR;
    }

    /**
     * 返回成功
     *
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/10/25 14:51
     **/
    public static CommOutVO getSuccess() {
        return SUCCESS_INSTANCE;
    }

    /**
     * 返回未授权
     *
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/10/25 17:41
     **/
    public static CommOutVO getNotAuth() {
        return NOT_AUTH_INSTANCE;
    }

    /**
     * 返回业务异常
     *
     * @param msg 业务异常描述
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/11/7 12:21
     **/
    public static CommOutVO getBusinessException(String msg) {
        return generateCommOutVO(StatusCode.BUSINESS_EXCEPTION, msg);
    }

    /**
     * 返回系统错误
     *
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/11/7 14:19
     **/
    public static CommOutVO getSystemError() {
        return SYS_ERROR_INSTANCE;
    }

    /**
     * 返回请求参数格式错误
     *
     * @param msg 提示语
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/11/7 18:27
     **/
    public static CommOutVO getReqDataFormatError(String msg) {
        return generateCommOutVO(StatusCode.REQ_DATA_FORMAT_ERROR, msg);
    }

    /**
     * 根据状态码和提示语，构造返回数据
     *
     * @param code 状态码
     * @param msg  提示语
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/11/7 18:28
     **/
    private static CommOutVO generateCommOutVO(int code, String msg) {
        if (StringUtil.isEmptyAndBlank(msg)) {
            throw new StringEmptyException();
        }
        CommOutVO outVO = new CommOutVO();
        outVO.setCode(code);
        outVO.setMessage(msg);
        return outVO;
    }

}
