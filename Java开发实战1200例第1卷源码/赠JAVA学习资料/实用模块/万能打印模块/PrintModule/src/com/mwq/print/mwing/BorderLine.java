/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import javax.swing.JSeparator;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class BorderLine extends JSeparator {

    public static final String PAPER_UP = "UP";
    public static final String PAPER_DOWN = "DOWN";
    public static final String PAPER_LEFT = "LEFT";
    public static final String PAPER_RIGHT = "RIGHT";

    public BorderLine(String location, int width, int height, int upSpace, int downSpace, int leftSpace, int rightSpace) {
        super();
        if (location.equals(PAPER_UP)) {
            setBounds(leftSpace + 1, upSpace + 1, width - (leftSpace + rightSpace), 1);
        } else if (location.equals(PAPER_DOWN)) {
            setBounds(leftSpace + 1, height - downSpace, width - (leftSpace + rightSpace), 1);
        } else if (location.equals(PAPER_LEFT)) {
            setOrientation(VERTICAL);
            setBounds(leftSpace + 1, upSpace + 1, 1, height - (upSpace + downSpace));
        } else {//PAPER_RIGHT

            setOrientation(VERTICAL);
            setBounds(width - rightSpace, upSpace + 1, 1, height - (upSpace + downSpace));
        }
    }
}
