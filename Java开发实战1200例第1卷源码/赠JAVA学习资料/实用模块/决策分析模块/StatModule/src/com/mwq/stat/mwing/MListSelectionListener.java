/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.mwing;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MListSelectionListener implements ListSelectionListener {

	private static MTable tableA;// ������֮һ
	private static MTable tableB;// ������֮��
	int selectedIndex = -1;// ��ǰѡ����������Ĭ��Ϊδѡ���κ���

	// ��ӱ�����
	public static void addTable(MTable table) {
		if (tableA == null) {// tableA��δ��ʼ��
			System.out.println("aaaaaaaaaaaaaaaa");
			System.out.println(tableA);

			tableA = table;// ��ʼ��tableA

		} else {// tableA�Ѿ���ʼ��
			System.out.println("bbbbbbbbbbbbb");
			System.out.println(tableB);

			tableB = table;// ��ʼ��tableB

		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {// ���ѡ���з����ı�ʱ��������

		if (tableA.getSelectedRow() == selectedIndex) {// ��ѡ��tableB�е��д���

			selectedIndex = tableB.getSelectedRow();// ���tableB�е�ѡ����

			tableA.setRowSelectionInterval(selectedIndex);// ͬ��ѡ��tableA�е���Ӧ��

		} else {// ��ѡ��tableA�е��д���

			selectedIndex = tableA.getSelectedRow();// ���tableA�е�ѡ����

			tableB.setRowSelectionInterval(selectedIndex);// ͬ��ѡ��tableB�е���Ӧ��

		}
	}
}
