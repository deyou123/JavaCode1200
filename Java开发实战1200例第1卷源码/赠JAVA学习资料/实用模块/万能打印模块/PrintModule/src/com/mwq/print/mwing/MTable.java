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
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MTable extends JTable {

    public MTable(Vector columnName, Vector tableData) {
        super(tableData, columnName);
        setEnabled(false);
    }

    // 表格列名居中显示
    @Override
    public JTableHeader getTableHeader() {
        tableHeader.setResizingAllowed(false);
        tableHeader.setFocusable(false);

        DefaultTableCellRenderer headerRenderer = // 获得表格头的单元格对象
                (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        // 设置单元格内容（即列名）居中显示
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return tableHeader;
    }

    // 表格列值居中显示
    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        DefaultTableCellRenderer defaultRenderer = // 获得表格列的单元格对象
                (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
        defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return defaultRenderer;
    }
}
