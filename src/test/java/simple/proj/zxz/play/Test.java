/*
 *
 * FileName: Test
 * Author:   zhangxz
 * Date:     2019/11/6
 */
package simple.proj.zxz.play;

import first.zxz.tools.PrintTool;

import java.util.TimeZone;

/**
 * 测试类
 *
 * @author zhangxz
 * 2019/11/6
 */


public class Test {

    public static void main(String[] args) {
        System.out.println("可选得时区总数量：" + TimeZone.getAvailableIDs().length);
        PrintTool.printStringArr(TimeZone.getAvailableIDs());
//        System.out.println(TimeZone.getTimeZone("Asia/Shanghai"));
//        System.out.println(TimeZone.getTimeZone("CTT"));
    }

}
