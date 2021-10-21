/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.chart;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.tool.Today;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class BarCollectColumn extends ChartPanel implements DataFormInterface {

    public BarCollectColumn() {
        super(createChart());
    }

    public static JFreeChart createChart() {
        // ������ͼ���ݼ�
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            String cutline = rowData.get(0).toString();
            for (int column = 1; column < columnName.size(); column++) {
                dataset.setValue(new Double((Integer) rowData.get(column)), cutline, columnName.get(column));
            }
        }

        // ����3DЧ��ͼ
        JFreeChart chart = ChartFactory.createStackedBarChart3D("���ݰ��л���", "����", "����λ��Ԫ��",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.addSubtitle(new TextTitle("���� ͳ�����ڣ�" + Today.getDate()));
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW, 0, 300, Color.GREEN, false));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setLowerMargin(0.03);
        domainAxis.setUpperMargin(0.02);

        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();

        renderer.setMinimumBarLength(30);

        renderer.setItemLabelsVisible(true);//������ʾ���α�ǩ

        renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));//���ñ�ǩ����

        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());//���ñ�ǩ������

        return chart;
    }
}
