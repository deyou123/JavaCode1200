/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.dataform;

import java.util.Vector;

/**
 * ��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class DataForm implements DataFormInterface {

    private static int fixedColumnAmount = 1;//��������̶�������
    

    static {//��ʼ��ͳ������

        //��ʼ���������
        columnName.add("Ʒ��");
        for (int column = 1; column <= 12; column++) {
            columnName.add(column + " ��");
        }

        //��ʼ���������
        String[] rowName = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
        for (int row = 0; row < rowName.length; row++) {
            Vector rowData = new Vector();
            rowData.add("��Ʒ " + rowName[row] + " ");
            for (int column = 1; column <= 12; column++) {
                rowData.add((int) (Math.random() * 1000));
            }
            tableData.add(rowData);
        }
    }

    public static void setFixedColumnAmount(int aFixedColumnAmount) {//���ù̶�������

        fixedColumnAmount = aFixedColumnAmount;
    }

    public static void setTableData(Vector<String> aColumnName, Vector<Vector> aTableData) {//����ͳ������

        fixedColumnAmount = 1;//��ʼ���̶�������

        columnName.addAll(aColumnName);//��ʼ���������

        int column = columnName.size();//��ñ������

        row:
        for (int row = 0; row < aTableData.size(); row++) {//�����������

            Object rowO = aTableData.get(row);//��ô������еĶ���

            if (rowO instanceof Vector) {//Ҫ��������еĶ������ΪVector��

                Vector rowV = (Vector) rowO;
                if (rowV.size() != column) {
                    System.out.println("�������ж����а����Ķ���������������������");
                } else {
                    for (int col = 0; col < rowV.size(); col++) {
                        Object colO = rowV.get(col);
                        if (!(colO instanceof Integer || colO instanceof Float || colO instanceof Double)) {
                            System.out.println("�������ж����а����Ķ������ΪVector��");
                            break row;
                        }
                    }
                }
            } else {
                System.out.println("�������еĶ������ΪVector��");
            }
        }
        tableData.addAll(tableData);
    }

    public static int getFixedColumnAmount() {//���ع̶�������

        return fixedColumnAmount;
    }

    public static Vector<String> getColumnName() {//���ر����������

        return columnName;
    }

    public static Vector<Vector> getTableData() {//���ر�����ݶ���

        return tableData;
    }
}
