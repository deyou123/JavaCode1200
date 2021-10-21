/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import com.mwq.print.tool.InstancePool;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class BackgroundLabel extends JLabel {

    private float alpha = InstancePool.getBackPanel().getOpaqueValue() / 100F;// 计算背景图片的透明度

    public BackgroundLabel(Icon image) {
        super(image);// 创建用于显示背景图片的标签组件对象

    }

    @Override
    public void paint(Graphics g) {// 重构父类的该方法

        Graphics2D g2d = (Graphics2D) g;// 获得G2D对象

        g2d.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));// 设置透明度

        super.paint(g2d);// 调用父类的该方法

    }
}
