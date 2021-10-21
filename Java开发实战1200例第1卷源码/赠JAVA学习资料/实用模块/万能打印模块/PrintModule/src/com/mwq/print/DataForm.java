/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class DataForm {

	private static final Map contentMap = new TreeMap();

	static {
		// �ı�
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < 16; i += 2) {
			for (int j = 0; j < i; j++) {
				sb.append("��");
			}
			sb.append(i + "��");
			addParagraph(i, i + "��" + sb.toString());
		}
		// ͼƬ
		addImg(4, "F:/min.png", "СͼƬ");
		addImg(8, "F:/bar.png", "�������ͼ");
		addImg(12, "F:/pie.png", "��ı���ͼ");
		// ���
		Vector<String> columnName = new Vector<String>();
		Vector<Vector> tableData = new Vector<Vector>();
		for (int col = 1; col < 6; col++) {
			columnName.add("�� " + col);
		}
		for (int row = 1; row < 30; row++) {
			Vector rowV = new Vector();
			for (int col = 1; col < 6; col++) {
				rowV.add(row + "." + col);
			}
			tableData.add(rowV);
		}
		addTable(6, "���ݱ�", columnName, tableData);
	}

	public static void addParagraph(int index, String text) {
		validateIndex(index);// ��֤����

		contentMap.put(index, text);// ��Ӵ�ӡ�ֶ�

	}

	public static void addImg(int index, String path, String title) {
		validateIndex(index);// ��֤����

		contentMap.put(index, new String[] { path, title });// ��Ӵ�ӡͼ��

	}

	public static void addTable(int index, String title,
			Vector<String> columnName, Vector<Vector> tableData) {
		validateIndex(index);// ��֤����

		// ��֤���
		int column = columnName.size();
		for (Vector rowV : tableData) {
			if (rowV.size() != column) {
				System.out.println("������ݵ�������ͳһ������");
				break;
			}
		}
		contentMap.put(index, new Object[] { title, columnName, tableData });// ��Ӵ�ӡ���

	}

	public static Map getContentMap() {
		return contentMap;
	}

	private static void validateIndex(int index) {
		Set keySet = contentMap.keySet();// ��ü���

		if (keySet.contains(index)) {// �������ָ����

			System.out.println("�������Ѿ����ڣ�����");
		}
	}
}
