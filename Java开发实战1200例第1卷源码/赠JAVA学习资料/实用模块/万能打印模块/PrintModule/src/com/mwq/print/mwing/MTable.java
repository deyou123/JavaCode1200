/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MTable extends JTable {

    public MTable(Vector columnName, Vector tableData) {
        super(tableData, columnName);
        setEnabled(false);
    }

    // �������������ʾ
    @Override
    public JTableHeader getTableHeader() {
        tableHeader.setResizingAllowed(false);
        tableHeader.setFocusable(false);

        DefaultTableCellRenderer headerRenderer = // ��ñ��ͷ�ĵ�Ԫ�����
                (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        // ���õ�Ԫ�����ݣ���������������ʾ
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return tableHeader;
    }

    // �����ֵ������ʾ
    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        DefaultTableCellRenderer defaultRenderer = // ��ñ���еĵ�Ԫ�����
                (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
        defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return defaultRenderer;
    }
}
