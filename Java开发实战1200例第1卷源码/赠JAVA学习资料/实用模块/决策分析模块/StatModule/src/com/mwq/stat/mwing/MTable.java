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
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MTable extends JTable {//implements ListSelectionListener {

    public MTable() {
        super();
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//不自动调整表格列宽

        selectionModel.addListSelectionListener(new MListSelectionListener());//添加选中行事件监听器

        MListSelectionListener.addTable(this);//添加到监听器类中

    }

    @Override
    public JTableHeader getTableHeader() {
        //获得表格头的单元格对象
        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        //设置单元格内容（即列名）居中显示
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return tableHeader;
    }
    //表格值居中显示

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        //获得表格的单元格对象
        DefaultTableCellRenderer defaultRenderer =
                (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
        //设置单元格内容（即列值）居中显示
        defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return defaultRenderer;
    }

    @Override
    public ListSelectionModel getSelectionModel() {
        //设置表格行只可单选
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return selectionModel;
    }

    // 重载设置表格选中行的方法
    public void setRowSelectionInterval(int row) {
        setRowSelectionInterval(row, row);
    }
}
