/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.dataform;

import java.util.Vector;

/**
 * 版权所有：明日科技有限公司
 *
 * @author Administrator
 */
public interface DataFormInterface {

    static Vector<String> columnName = new Vector<String>();//用来保存表格列名
    static Vector<Vector> tableData = new Vector<Vector>();//用来保存表格数据
}
