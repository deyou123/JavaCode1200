package com.mwq.frame.mwing;

import java.awt.Insets;

import javax.swing.JButton;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MButton extends JButton {
	public MButton() {
		super();
		setBorderPainted(false);
		setContentAreaFilled(false);
		setMargin(new Insets(0, 0, 0, 0));
	}
}
