package simple.proj.zxz.play.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.proj.zxz.play.comm.GeneralConsts;
import simple.proj.zxz.play.prop.CommProp;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * swagger配置类
 *
 * @author zhangxz
 * 2019/10/25
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private CommProp commProp;

    /**
     * 请求头字段
     */
    private static final String REQ_HEADER = "header";
    /**
     * 认证范围
     */
    private static final String AUTHORIZATION_SCOPE = "global";
    /**
     * 认证描述
     */
    private static final String AUTHORIZATIONS_DESC = "accessEverything";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("simple.proj.zxz.play.controller"))
                .paths(PathSelectors.any())
                .build()
                //加入认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(GeneralConsts.PROJECT_API_TITLE)
                .description(GeneralConsts.PROJECT_DESC)
                .version(commProp.getPlayApiVersionLatest())
                .build();
    }

    /**
     * 配置请求头
     *
     * @return java.util.List<springfox.documentation.service.ApiKey>
     * @author Zxz
     * @date 2019/10/25 17:13
     **/
    private List<ApiKey> securitySchemes() {
        return Collections.singletonList(new ApiKey(GeneralConsts.REQ_HEADER_AUTH, GeneralConsts.REQ_HEADER_AUTH, REQ_HEADER));
    }

    /**
     * 设置登录认证路径，可配置多个
     *
     * @return java.util.List<springfox.documentation.spi.service.contexts.SecurityContext>
     * @author Zxz
     * @date 2019/10/25 17:14
     **/
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/" + commProp.getPlayApiVersionLatest() + "/.*"));
        return result;
    }

    /**
     * 配置单个认证路径
     *
     * @param regex 正则表达式
     * @return springfox.documentation.spi.service.contexts.SecurityContext
     * @author Zxz
     * @date 2019/10/25 17:17
     **/
    private SecurityContext getContextByPath(String regex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(regex))
                .build();
    }

    /**
     * 不知道是啥
     *
     * @return java.util.List<springfox.documentation.service.SecurityReference>
     * @author Zxz
     * @date 2019/10/25 17:22
     **/
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{new AuthorizationScope(AUTHORIZATION_SCOPE, AUTHORIZATIONS_DESC)};
        return Collections.singletonList(new SecurityReference(GeneralConsts.REQ_HEADER_AUTH, authorizationScopes));
    }

}
