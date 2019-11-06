package simple.proj.zxz.play.pojo.vo.comm;

import lombok.Data;
import simple.proj.zxz.play.comm.StatusCode;

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
    public static CommOutVO SUCCESS_INSTANCE = new CommOutVO();
    //返回未授权
    public static CommOutVO NOT_AUTH_INSTANCE = new CommOutVO();

    static {
        SUCCESS_INSTANCE.code = StatusCode.SUCCESS;
        NOT_AUTH_INSTANCE.code = StatusCode.NOT_AUTH;
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
    public static CommOutVO getNotAuthInstance() {
        return NOT_AUTH_INSTANCE;
    }

}
