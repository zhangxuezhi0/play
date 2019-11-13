package simple.proj.zxz.play.config.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 总序列器
 *
 * @author zhangxz
 * @date 2019-11-12 22:07
 */

public class GeneralSerializer extends BeanSerializerModifier {

    //空数组
    private JsonSerializer _nullArraySerializer = new NullArraySerializer();
    //空字符串
    private JsonSerializer _nullStringSerializer = new NullStringSerializer();
    //日期类型
    private JsonSerializer dateSerializer = new DateSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter beanProperty : beanProperties) {
            if (isArrayType(beanProperty)) {
                beanProperty.assignNullSerializer(this._nullArraySerializer);
            } else if (isStringType(beanProperty)) {
                beanProperty.assignNullSerializer(this._nullStringSerializer);
            } else if (isDateType(beanProperty)) {
                beanProperty.assignSerializer(this.dateSerializer);
            }
        }
        return beanProperties;
    }

    private boolean isStringType(BeanPropertyWriter writer) {
        Class clazz = writer.getType().getRawClass();
        return clazz == String.class;
    }

    private boolean isDateType(BeanPropertyWriter writer) {
        return Date.class == writer.getType().getRawClass();
    }

    private boolean isArrayType(BeanPropertyWriter writer) {
        Class clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }


}
