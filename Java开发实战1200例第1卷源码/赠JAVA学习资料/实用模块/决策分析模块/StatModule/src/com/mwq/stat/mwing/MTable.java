/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MTable extends JTable {//implements ListSelectionListener {

    public MTable() {
        super();
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//���Զ���������п�

        selectionModel.addListSelectionListener(new MListSelectionListener());//���ѡ�����¼�������

        MListSelectionListener.addTable(this);//��ӵ�����������

    }

    @Override
    public JTableHeader getTableHeader() {
        //��ñ��ͷ�ĵ�Ԫ�����
        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        //���õ�Ԫ�����ݣ���������������ʾ
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return tableHeader;
    }
    //���ֵ������ʾ

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        //��ñ��ĵ�Ԫ�����
        DefaultTableCellRenderer defaultRenderer =
                (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
        //���õ�Ԫ�����ݣ�����ֵ��������ʾ
        defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return defaultRenderer;
    }

    @Override
    public ListSelectionModel getSelectionModel() {
        //���ñ����ֻ�ɵ�ѡ
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return selectionModel;
    }

    // �������ñ��ѡ���еķ���
    public void setRowSelectionInterval(int row) {
        setRowSelectionInterval(row, row);
    }
}
