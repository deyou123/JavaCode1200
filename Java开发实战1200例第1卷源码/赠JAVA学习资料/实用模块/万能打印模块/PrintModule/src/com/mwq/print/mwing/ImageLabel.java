/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import com.mwq.print.tabbedPane.AlignPanel;
import com.mwq.print.tool.InstancePool;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ImageLabel extends JLabel {

    private static final AlignPanel alignPanel;
    

    static {
        alignPanel = InstancePool.getAlignPanel();
    }

    public ImageLabel(BufferedImage bufferedImage, String title) {
        int h = 0;
        Enumeration hEnumeration = alignPanel.getHEnumeration();
        while (hEnumeration.hasMoreElements()) {
            JRadioButton radioButton = (JRadioButton) hEnumeration.nextElement();
            if (radioButton.isSelected()) {
                h = Integer.valueOf(radioButton.getName());
            }
        }
        setHorizontalAlignment(h);

        int v = 0;
        Enumeration vEnumeration = alignPanel.getVEnumeration();
        while (vEnumeration.hasMoreElements()) {
            JRadioButton radioButton = (JRadioButton) vEnumeration.nextElement();
            if (radioButton.isSelected()) {
                v = Integer.valueOf(radioButton.getName());
            }
        }
        setVerticalAlignment(v);

        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);

        setIcon(new ImageIcon(bufferedImage));
        if (title != null) {
            setText(title);
        }
    }
}
