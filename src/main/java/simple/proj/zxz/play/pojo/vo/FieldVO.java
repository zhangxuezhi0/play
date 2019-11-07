/*
 *
 * FileName: FieldVO
 * Author:   zhangxz
 * Date:     2019/11/6
 */
package simple.proj.zxz.play.pojo.vo;

import first.zxz.tools.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * 字段拓展vo
 *
 * @author zhangxz
 * 2019/11/6
 */

@Data
public class FieldVO<T> {

    //字段key，用于运算的实际值
    private T key;

    //字段value，用于展示
    private String value;

    public FieldVO() {
    }

    public FieldVO(T key) {
        if (key instanceof Date) {
            this.key = key;
            this.value = DateUtil.format(((Date) key));
        } else {
            this.key = key;
            this.value = String.valueOf(key);
        }
    }

}
