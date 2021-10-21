/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.dataform.DataForm;
import javax.swing.table.AbstractTableModel;

/**
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class FixedColumnTableModel extends AbstractTableModel implements DataFormInterface {

    public int getRowCount() {//返回表格拥有行数

        return tableData.size();
    }

    public int getColumnCount() {//返回表格拥有列数，即固定列的数量

        return DataForm.getFixedColumnAmount();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {//返回指定单元格的值

        return tableData.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {//返回指定列的名称

        return columnName.get(column);
    }
}
