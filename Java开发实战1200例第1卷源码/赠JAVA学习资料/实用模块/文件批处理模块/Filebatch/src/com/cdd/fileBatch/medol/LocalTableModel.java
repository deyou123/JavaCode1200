package com.cdd.fileBatch.medol;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class LocalTableModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class };
	boolean[] canEdit = new boolean[] { false, false, false };
	public LocalTableModel() {
		super(new Object[][] {}, new String[] { "文件名", "大小", "日期" });
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}