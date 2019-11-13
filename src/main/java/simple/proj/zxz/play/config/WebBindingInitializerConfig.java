package simple.proj.zxz.play.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import simple.proj.zxz.play.config.editor.DateEditor;

import java.util.Date;

/**
 * 配置请求 bean数据接收格式，例如date格式
 *
 * @author zhangxz
 * @date 2019-11-12 19:56
 */

@Configuration
public class WebBindingInitializerConfig {

    /**
     * 配置请求入参数据格式，参考{@link simple.proj.zxz.play.config.editor.DateEditor}
     *
     * @param mvcConversionService mvc版本业务
     * @param mvcValidator         mvc校验器
     * @return org.springframework.web.bind.support.ConfigurableWebBindingInitializer
     * @author zhangxz
     * @date 2019/11/13 10:40
     */
    @Bean
    public ConfigurableWebBindingInitializer configurableWebBindingInitializer(FormattingConversionService mvcConversionService, Validator mvcValidator) {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        initializer.setConversionService(mvcConversionService);
        initializer.setValidator(mvcValidator);

        initializer.setPropertyEditorRegistrar(propertyEditorRegistry -> {
            //PropertyEditor 非线程安全
            propertyEditorRegistry.registerCustomEditor(Date.class, new DateEditor());
        });
        return initializer;
    }

}
