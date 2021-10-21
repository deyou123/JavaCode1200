/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.dataform;

import java.util.Vector;

/**
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class DataForm implements DataFormInterface {

    private static int fixedColumnAmount = 1;//用来保存固定列数量
    

    static {//初始化统计数据

        //初始化表格列名
        columnName.add("品名");
        for (int column = 1; column <= 12; column++) {
            columnName.add(column + " 月");
        }

        //初始化表格数据
        String[] rowName = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
        for (int row = 0; row < rowName.length; row++) {
            Vector rowData = new Vector();
            rowData.add("商品 " + rowName[row] + " ");
            for (int column = 1; column <= 12; column++) {
                rowData.add((int) (Math.random() * 1000));
            }
            tableData.add(rowData);
        }
    }

    public static void setFixedColumnAmount(int aFixedColumnAmount) {//设置固定列数量

        fixedColumnAmount = aFixedColumnAmount;
    }

    public static void setTableData(Vector<String> aColumnName, Vector<Vector> aTableData) {//设置统计数据

        fixedColumnAmount = 1;//初始化固定列数量

        columnName.addAll(aColumnName);//初始化表格列名

        int column = columnName.size();//获得表格列数

        row:
        for (int row = 0; row < aTableData.size(); row++) {//遍历表格数据

            Object rowO = aTableData.get(row);//获得代表表格行的对象

            if (rowO instanceof Vector) {//要求代表表格行的对象必须为Vector型

                Vector rowV = (Vector) rowO;
                if (rowV.size() != column) {
                    System.out.println("代表表格行对象中包含的对象个数必须与表格列数相等");
                } else {
                    for (int col = 0; col < rowV.size(); col++) {
                        Object colO = rowV.get(col);
                        if (!(colO instanceof Integer || colO instanceof Float || colO instanceof Double)) {
                            System.out.println("代表表格行对象中包含的对象必须为Vector型");
                            break row;
                        }
                    }
                }
            } else {
                System.out.println("代表表格行的对象必须为Vector型");
            }
        }
        tableData.addAll(tableData);
    }

    public static int getFixedColumnAmount() {//返回固定列数量

        return fixedColumnAmount;
    }

    public static Vector<String> getColumnName() {//返回表格列名对象

        return columnName;
    }

    public static Vector<Vector> getTableData() {//返回表格数据对象

        return tableData;
    }
}
