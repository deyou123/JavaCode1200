package com.mwq.frame.mwing;

import java.awt.Insets;

import javax.swing.JButton;

/**
 *版权所有：明日科技有限公司
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
