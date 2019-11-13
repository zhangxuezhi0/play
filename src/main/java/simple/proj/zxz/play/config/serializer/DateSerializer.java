package simple.proj.zxz.play.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 日期序列器
 *
 * @author zhangxz
 * @date 2019-11-12 23:01
 */
public class DateSerializer extends JsonSerializer {

    /**
     * 日期序列化方法，返回时间戳格式
     *
     * @param o                  数值
     * @param jsonGenerator      json生成器
     * @param serializerProvider 序列器
     * @author zhangxz
     * @date 2019/11/13 10:41
     */
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(((Date) o).getTime());
    }
}
