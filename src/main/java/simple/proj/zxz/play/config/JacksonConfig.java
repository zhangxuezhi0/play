package simple.proj.zxz.play.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import simple.proj.zxz.play.config.serializer.GeneralSerializer;

/**
 * @description: 配置jackson 序列器
 * @author: zhangxz
 * @create: 2019-11-12 22:02
 */


@Configuration
public class JacksonConfig {

    /**
     * 出参数据格式配置，参考类 {@link simple.proj.zxz.play.config.serializer.GeneralSerializer}
     *
     * @return org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     * @author zhangxz
     * @date 2019/11/13 10:34
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper();
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new GeneralSerializer()));

        return converter;
    }

}
