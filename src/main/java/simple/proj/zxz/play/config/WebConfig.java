package simple.proj.zxz.play.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import simple.proj.zxz.play.interceptors.ApiAccessInterceptor;
import simple.proj.zxz.play.prop.CommProp;

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
    private CommProp commProp;

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
        registry.addInterceptor(apiAccessInterceptor).addPathPatterns("/" + commProp.getPlayApiVersionLatest() + "/**");

        //注意，拦截器配置不能使用配置文件的统一api路径配置：server.servlet.context-path，这样配置是无效的。
        //只能使用controller里面的具体路径配置，才能有效拦截
//        registry.addInterceptor(apiAccessInterceptor).addPathPatterns("/play/api/**");

    }


    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                //输出null值
                SerializerFeature.WriteMapNullValue,
                //数组的null输出为空数组：[]
                SerializerFeature.WriteNullListAsEmpty,
                //禁止json的循环引用
                SerializerFeature.DisableCircularReferenceDetect,

//                SerializerFeature.WriteDateUseDateFormat,
                //字符串的null输出为空字符串：""
                SerializerFeature.WriteNullStringAsEmpty);

        return fastJsonConfig;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(
            @Qualifier("fastJsonConfig") FastJsonConfig fastJsonConfig) {
        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastConverter);
    }

}
