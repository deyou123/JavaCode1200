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
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class WatermarkLabel extends JLabel {

    private static final WaterPanel waterPanel = InstancePool.getWaterPanel();
    private String watermark = waterPanel.getWatermark();// ˮӡ�ı�
    private Color color = waterPanel.getColor();// ˮӡ��ɫ
    private float alpha = waterPanel.getOpaqueSliderValue() / 100F;// ͸����
    private double zoom = waterPanel.getSizeSliderValue() / 100D;// ���ű���
    private boolean clockwise = waterPanel.isClockwiseRadioButtonSelected();// ��ת����
    private int angle = waterPanel.getAngle();// ��ת�Ƕ�
    private int xy;// ��ת����

    public WatermarkLabel(int width) {
        super();
        setText(watermark);// ����ˮӡ�ı�

        setForeground(color);// ����ˮӡ��ɫ

        setFont(new Font("����", Font.BOLD, 16));// ����ˮӡ����

        int wh = (int) (width * zoom);// ���ź�Ĵ�С

        double preferredWidth = getPreferredSize().getWidth();// �����ѡ���

        xy = (int) (preferredWidth / 2);// ��ת����

        zoom = wh / preferredWidth;// ʵ�����ű���

        setPreferredSize(new Dimension(wh, wh));// ������ѡ��С

    }

    @Override
    public void paint(Graphics g) {										// �ع��÷���

        Graphics2D g2d = (Graphics2D) g;									// ���G2D����

        g2d.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));	// ����͸����

        g2d.scale(zoom, zoom);// �������ű���

        if (angle != 0) {// ��ת

            if (clockwise) {// ˳ʱ����ת

                g2d.rotate(Math.toRadians(angle), xy, xy);// ������ת���Ⱥ���ת����

            } else {// ��ʱ����ת

                g2d.rotate(Math.toRadians(360 - angle), xy, xy);// ������ת���Ⱥ���ת����

            }
        }
        g2d.drawString(watermark, 0, xy);// ����ˮӡ�ı�

    }
}
