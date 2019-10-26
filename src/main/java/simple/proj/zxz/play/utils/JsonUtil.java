package simple.proj.zxz.play.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json 工具类
 *
 * @author zhangxz
 * 2019/10/26
 */
public class JsonUtil {

    /**
     * 输出对象为json字符串，
     * 1. 所有字段都输出
     * 2. string的null转为空字符串
     * 3. 数组的null转为空数组
     * 4. 禁用Fastjson的循环引用
     * 5. 格式化输出
     *
     * @param object 目标对象
     * @return java.lang.String
     * @author Zxz
     * @date 2019/10/26 14:26
     **/
    public static String toFormattedJsonString(Object object) {
        return JSON.toJSONString(object,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty);
    }

}
