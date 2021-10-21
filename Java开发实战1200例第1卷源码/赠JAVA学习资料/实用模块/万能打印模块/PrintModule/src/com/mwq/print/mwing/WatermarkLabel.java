/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.print.mwing;

import com.mwq.print.tabbedPane.WaterPanel;
import com.mwq.print.tool.InstancePool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class WatermarkLabel extends JLabel {

    private static final WaterPanel waterPanel = InstancePool.getWaterPanel();
    private String watermark = waterPanel.getWatermark();// 水印文本
    private Color color = waterPanel.getColor();// 水印颜色
    private float alpha = waterPanel.getOpaqueSliderValue() / 100F;// 透明度
    private double zoom = waterPanel.getSizeSliderValue() / 100D;// 缩放比例
    private boolean clockwise = waterPanel.isClockwiseRadioButtonSelected();// 旋转方向
    private int angle = waterPanel.getAngle();// 旋转角度
    private int xy;// 旋转中心

    public WatermarkLabel(int width) {
        super();
        setText(watermark);// 设置水印文本

        setForeground(color);// 设置水印颜色

        setFont(new Font("宋体", Font.BOLD, 16));// 设置水印字体

        int wh = (int) (width * zoom);// 缩放后的大小

        double preferredWidth = getPreferredSize().getWidth();// 获得首选宽度

        xy = (int) (preferredWidth / 2);// 旋转中心

        zoom = wh / preferredWidth;// 实际缩放比例

        setPreferredSize(new Dimension(wh, wh));// 设置首选大小

    }

    @Override
    public void paint(Graphics g) {										// 重构该方法

        Graphics2D g2d = (Graphics2D) g;									// 获得G2D对象

        g2d.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));	// 设置透明度

        g2d.scale(zoom, zoom);// 设置缩放比例

        if (angle != 0) {// 旋转

            if (clockwise) {// 顺时针旋转

                g2d.rotate(Math.toRadians(angle), xy, xy);// 设置旋转弧度和旋转中心

            } else {// 逆时针旋转

                g2d.rotate(Math.toRadians(360 - angle), xy, xy);// 设置旋转弧度和旋转中心

            }
        }
        g2d.drawString(watermark, 0, xy);// 绘制水印文本

    }
}
