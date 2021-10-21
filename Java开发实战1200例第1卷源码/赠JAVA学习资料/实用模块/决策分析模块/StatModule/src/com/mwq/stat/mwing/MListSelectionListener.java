/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class MListSelectionListener implements ListSelectionListener {

	private static MTable tableA;// 表格对象之一
	private static MTable tableB;// 表格对象之二
	int selectedIndex = -1;// 当前选中行索引，默认为未选中任何行

	// 添加表格对象
	public static void addTable(MTable table) {
		if (tableA == null) {// tableA尚未初始化
			System.out.println("aaaaaaaaaaaaaaaa");
			System.out.println(tableA);

			tableA = table;// 初始化tableA

		} else {// tableA已经初始化
			System.out.println("bbbbbbbbbbbbb");
			System.out.println(tableB);

			tableB = table;// 初始化tableB

		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {// 表格选中行发生改变时将被触发

		if (tableA.getSelectedRow() == selectedIndex) {// 由选中tableB中的行触发

			selectedIndex = tableB.getSelectedRow();// 获得tableB中的选中行

			tableA.setRowSelectionInterval(selectedIndex);// 同步选中tableA中的相应行

		} else {// 由选中tableA中的行触发

			selectedIndex = tableA.getSelectedRow();// 获得tableA中的选中行

			tableB.setRowSelectionInterval(selectedIndex);// 同步选中tableB中的相应行

		}
	}
}
