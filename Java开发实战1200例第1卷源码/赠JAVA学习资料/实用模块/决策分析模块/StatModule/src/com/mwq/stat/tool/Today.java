/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.tool;

import java.util.Calendar;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class Today {

    private static final int YEAR;
    private static final int MONTH;
    private static final int DAY;
    

    static {
        Calendar today = Calendar.getInstance();
        YEAR = today.get(Calendar.YEAR);
        MONTH = today.get(Calendar.MONTH) + 1;
        DAY = today.get(Calendar.DAY_OF_MONTH);
    }

    public static String getDate() {
        return YEAR + " 年 " + MONTH + " 月 " + DAY + " 日";
    }
}
