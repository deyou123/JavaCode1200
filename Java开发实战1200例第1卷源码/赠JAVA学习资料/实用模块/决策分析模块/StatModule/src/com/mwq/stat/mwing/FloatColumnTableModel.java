/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.dataform.DataForm;
import javax.swing.table.AbstractTableModel;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class FloatColumnTableModel extends AbstractTableModel implements DataFormInterface {

    public int getRowCount() {//返回表格拥有行数

        return tableData.size();
    }

    public int getColumnCount() {//返回表格拥有列数，即可滚动列的数量

        //需要从总列数中减去固定列数量
        return columnName.size() - DataForm.getFixedColumnAmount();

    }

    public Object getValueAt(int rowIndex, int columnIndex) {//返回指定单元格的值

        //需要从列索引值中减去固定列数量
        return tableData.get(rowIndex).get(columnIndex + DataForm.getFixedColumnAmount());

    }

    @Override
    public String getColumnName(int column) {//返回指定列的名称

        //需要从列索引值中减去固定列数量
        return columnName.get(column + DataForm.getFixedColumnAmount());
    }
}
