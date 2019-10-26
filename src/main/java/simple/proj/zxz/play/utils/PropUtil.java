package simple.proj.zxz.play.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 属性读取工具类
 * @author zhangxz
 * 2019/10/25
 */

@Component
@Getter
public class PropUtil {

    @Value("${user.permanent.authorization}")
    private String userPermanentAuthorization;

    @Value("${server.servlet.context-path}")
    private String serverServletContextPath;

    @Value("${play.api.version.latest}")
    private String playApiVersionLatest;

}
