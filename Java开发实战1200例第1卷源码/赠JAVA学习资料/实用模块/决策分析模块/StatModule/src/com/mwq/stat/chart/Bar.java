/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.chart;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.tool.Today;
import java.awt.Color;
import java.awt.GradientPaint;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * ��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class Bar extends ChartPanel implements DataFormInterface {

    public Bar() {
        super(createChart());//����JFreeChart���󴴽�ChartPanel����

    }

    public static JFreeChart createChart() {//����JFreeChart����

        // ������ͼ���ݼ�
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            String cutline = rowData.get(0).toString();
            for (int column = 1; column < columnName.size(); column++) {
                dataset.setValue(
                        new Double((Integer) rowData.get(column)), cutline, columnName.get(column));
            }
        }

        // ����3DЧ��ͼ
        JFreeChart chart = ChartFactory.createBarChart3D("���ݸ���", "����", "����λ��Ԫ��",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.addSubtitle(new TextTitle("���� ͳ�����ڣ�" + Today.getDate()));//���ø�����

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW,
                0, 300, Color.GREEN, false));//���ñ���ɫ

        CategoryPlot plot = (CategoryPlot) chart.getPlot();//��û�ͼ������

        CategoryAxis domainAxis = plot.getDomainAxis();//��÷��ࣨX�������

        domainAxis.setLowerMargin(0.03);//����������������ͼ����߿�ľ���

        domainAxis.setUpperMargin(0.02);//�������Ҳ��������ͼ���ұ߿�ľ���

        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();//������ζ���

        renderer.setMinimumBarLength(30);//�������ε���С�߶�

        return chart;
    }
}
