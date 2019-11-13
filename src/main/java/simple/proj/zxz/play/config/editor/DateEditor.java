package simple.proj.zxz.play.config.editor;

import first.zxz.tools.DateUtil;

import java.beans.PropertyEditorSupport;

/**
 * 日期编辑器
 *
 * @author zhangxz
 * @date 2019-11-12 20:01
 */

public class DateEditor extends PropertyEditorSupport {
    /**
     * 是否将null转换为空字符串
     */
    private final boolean nullAsEmpty;

    public DateEditor() {
        this(true);
    }

    public DateEditor(boolean nullAsEmpty) {
        this.nullAsEmpty = nullAsEmpty;
    }

    /*@Override
    public String getAsText() {
        Object value = getValue();
        if (value == null) {
            if (nullAsEmpty) {
                return "";
            } else {
                return null;
            }
        }

        Date date = (Date) value;
        return String.valueOf(date.getTime());
    }*/

    @Override
    public void setAsText(String text) {
        if (text == null) {
            if (nullAsEmpty) {
                setValue("");
            } else {
                setValue(null);
            }
        } else {
            setValue(DateUtil.parse(text));
        }
    }

}
