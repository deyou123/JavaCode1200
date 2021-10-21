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
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class AreaCollectRow extends ChartPanel implements DataFormInterface {

    public AreaCollectRow() {
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
        JFreeChart chart = ChartFactory.createStackedAreaChart("��������", "����", "����λ��Ԫ��",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        chart.addSubtitle(new TextTitle("���� ͳ�����ڣ�" + Today.getDate()));
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW, 0, 300, Color.GREEN, false));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        plot.setForegroundAlpha(0.5f);

        plot.setDomainGridlinesVisible(true);

        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setLowerMargin(0.001);
        domainAxis.setUpperMargin(0.001);

        return chart;
    }
}
