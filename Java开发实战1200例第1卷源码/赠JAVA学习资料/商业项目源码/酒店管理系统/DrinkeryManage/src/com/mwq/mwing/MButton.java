package com.mwq.mwing;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MButton extends JButton {
	public MButton() {
		setContentAreaFilled(false);
		setMargin(new Insets(0, 0, 0, 0));
		setBorderPainted(false);
		setFocusPainted(false);
	}
}
