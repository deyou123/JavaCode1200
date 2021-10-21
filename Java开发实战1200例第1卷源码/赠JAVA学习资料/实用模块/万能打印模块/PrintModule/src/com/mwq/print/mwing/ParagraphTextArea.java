/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import javax.swing.JTextArea;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ParagraphTextArea extends JTextArea {

    public ParagraphTextArea(String text, int width) {
        super(text);
        setLineWrap(true);
        setBounds(0, 0, width, 100);
    }
}
