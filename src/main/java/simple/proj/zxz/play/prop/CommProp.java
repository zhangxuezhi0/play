/*
 *
 * FileName: CommProp
 * Author:   zhangxz
 * Date:     2019/10/28
 */
package simple.proj.zxz.play.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 公共配置读取
 *
 * @author zhangxz
 * 2019/10/28
 */

@Component
@Getter
public class CommProp {

    /**
     * 用户认证信息
     */
    @Value("${user.permanent.authorization}")
    private String userPermanentAuthorization;

    /**
     * api版本号
     */
    @Value("${api.version.latest}")
    private String apiVersionLatest;

    /**
     * 时区设置
     */
    @Value("${time.zone}")
    private String timeZone;

    /**
     * 应用名称
     */
    @Value("${application.name}")
    private String applicationName;

}
