package simple.proj.zxz.play.interceptors;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import simple.proj.zxz.play.comm.GeneralConsts;
import simple.proj.zxz.play.pojo.vo.comm.CommOutVO;
import simple.proj.zxz.play.utils.JsonUtil;
import simple.proj.zxz.play.utils.PropUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * api访问拦截器
 *
 * @author Zxz
 * @version 1.0
 * @date Created at 2018/11/24
 **/

@Slf4j
@Component
public class ApiAccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PropUtil propUtil;

    /**http响应类型字段*/
    private static final String RESPONSE_CONTENT_TYPE = "Content-Type";
    /**http响应类型：json*/
    private static final String RESPONSE_HEADER_JSON = "application/json";

    /**
     * 访问认证拦截
     *
     * @param request  请求
     * @param response 响应
     * @param handler  数据
     * @return boolean
     * @author Zxz
     * @date 2019/10/25 17:34
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法类型过滤
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        //token验证
        String token = request.getHeader(GeneralConsts.REQ_HEADER_AUTH);
        if (StringUtils.isEmpty(token)) {
            //没有token信息，未登录
            response.setHeader(RESPONSE_CONTENT_TYPE, RESPONSE_HEADER_JSON);
            response.getWriter().write(JsonUtil.toFormattedJsonString(CommOutVO.getNotAuthInstance()));
            return false;
        } else if (!auth(token)) {
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 验证认证信息是否可以
     *
     * @param token token串
     * @return boolean
     * @author Zxz
     * @date 2019/10/25 17:48
     **/
    private boolean auth(String token) {
        return token.equals(propUtil.getUserPermanentAuthorization());
    }


}
