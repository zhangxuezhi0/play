package simple.proj.zxz.play.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import simple.proj.zxz.play.interceptors.ApiAccessInterceptor;
import simple.proj.zxz.play.utils.PropUtil;

/**
 * web配置
 *
 * @author zhangxz
 * 2019/10/25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiAccessInterceptor apiAccessInterceptor;
    @Autowired
    private PropUtil propUtil;

    /**
     * 拦截器配置
     *
     * @param registry 拦截器注册类
     * @return void
     * @author Zxz
     * @date 2019/10/25 17:53
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiAccessInterceptor).addPathPatterns("/" + propUtil.getPlayApiVersionLatest() + "/**");

        //注意，拦截器配置不能使用配置文件的统一api路径配置：server.servlet.context-path，这样配置是无效的。
        //只能使用controller里面的具体路径配置，才能有效拦截
//        registry.addInterceptor(apiAccessInterceptor).addPathPatterns("/play/api/**");

    }
}
