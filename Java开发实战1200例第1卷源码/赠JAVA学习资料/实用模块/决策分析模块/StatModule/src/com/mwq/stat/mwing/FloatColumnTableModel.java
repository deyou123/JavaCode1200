/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.dataform.DataForm;
import javax.swing.table.AbstractTableModel;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class FloatColumnTableModel extends AbstractTableModel implements DataFormInterface {

    public int getRowCount() {//���ر��ӵ������

        return tableData.size();
    }

    public int getColumnCount() {//���ر��ӵ�����������ɹ����е�����

        //��Ҫ���������м�ȥ�̶�������
        return columnName.size() - DataForm.getFixedColumnAmount();

    }

    public Object getValueAt(int rowIndex, int columnIndex) {//����ָ����Ԫ���ֵ

        //��Ҫ��������ֵ�м�ȥ�̶�������
        return tableData.get(rowIndex).get(columnIndex + DataForm.getFixedColumnAmount());

    }

    @Override
    public String getColumnName(int column) {//����ָ���е�����

        //��Ҫ��������ֵ�м�ȥ�̶�������
        return columnName.get(column + DataForm.getFixedColumnAmount());
    }
}
