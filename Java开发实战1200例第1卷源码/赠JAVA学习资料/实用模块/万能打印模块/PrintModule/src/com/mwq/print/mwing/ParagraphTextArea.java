/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import javax.swing.JTextArea;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class ParagraphTextArea extends JTextArea {

    public ParagraphTextArea(String text, int width) {
        super(text);
        setLineWrap(true);
        setBounds(0, 0, width, 100);
    }
}
