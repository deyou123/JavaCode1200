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
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class DataForm {

	private static final Map contentMap = new TreeMap();

	static {
		// 文本
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < 16; i += 2) {
			for (int j = 0; j < i; j++) {
				sb.append("成");
			}
			sb.append(i + "。");
			addParagraph(i, i + "、" + sb.toString());
		}
		// 图片
		addImg(4, "F:/min.png", "小图片");
		addImg(8, "F:/bar.png", "大的柱形图");
		addImg(12, "F:/pie.png", "大的饼形图");
		// 表格
		Vector<String> columnName = new Vector<String>();
		Vector<Vector> tableData = new Vector<Vector>();
		for (int col = 1; col < 6; col++) {
			columnName.add("列 " + col);
		}
		for (int row = 1; row < 30; row++) {
			Vector rowV = new Vector();
			for (int col = 1; col < 6; col++) {
				rowV.add(row + "." + col);
			}
			tableData.add(rowV);
		}
		addTable(6, "数据表", columnName, tableData);
	}

	public static void addParagraph(int index, String text) {
		validateIndex(index);// 验证索引

		contentMap.put(index, text);// 添加打印字段

	}

	public static void addImg(int index, String path, String title) {
		validateIndex(index);// 验证索引

		contentMap.put(index, new String[] { path, title });// 添加打印图象

	}

	public static void addTable(int index, String title,
			Vector<String> columnName, Vector<Vector> tableData) {
		validateIndex(index);// 验证索引

		// 验证表格
		int column = columnName.size();
		for (Vector rowV : tableData) {
			if (rowV.size() != column) {
				System.out.println("表格数据的列数不统一！！！");
				break;
			}
		}
		contentMap.put(index, new Object[] { title, columnName, tableData });// 添加打印表格

	}

	public static Map getContentMap() {
		return contentMap;
	}

	private static void validateIndex(int index) {
		Set keySet = contentMap.keySet();// 获得键集

		if (keySet.contains(index)) {// 如果包含指定键

			System.out.println("该索引已经存在！！！");
		}
	}
}
