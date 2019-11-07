/*
 *
 * FileName: LogEntity
 * Author:   zhangxz
 * Date:     2019/11/6
 */
package simple.proj.zxz.play.pojo.entity;

import lombok.Data;
import simple.proj.zxz.play.pojo.vo.FieldVO;

import java.util.Date;

/**
 * 日志记录实体类
 *
 * @author zhangxz
 * 2019/11/6
 */

@Data
public class LogEntity {

    /**
     * 创建时间
     */
    private FieldVO<Date> createTime;
    /**
     * 请求者的ip
     */
    private String remoteIp;

    /**
     * 请求的数据
     */
    private String reqData;

    /**
     * 请求的api
     */
    private String reqApi;

    /**
     * 请求方法类型
     */
    private String reqType;

    /**
     * 请求的方法
     */
    private String reqMethod;

    /**
     * 方法处理毫秒数
     */
    private Long methodHandleMilliseconds;

    /**
     * 方法返回数据
     */
    private String result;

    /**
     * 异常信息
     */
    private String errorMsg;

}
