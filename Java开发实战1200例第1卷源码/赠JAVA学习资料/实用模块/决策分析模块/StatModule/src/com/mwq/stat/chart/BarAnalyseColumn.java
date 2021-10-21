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
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * ��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class BarAnalyseColumn extends ChartPanel implements DataFormInterface {

    public BarAnalyseColumn(int row) {
        super(createChart(row));//����JFreeChart���󴴽�ChartPanel����

    }

    public static JFreeChart createChart(int column) {

        // ������ͼ���ݼ�
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String cutline = columnName.get(column).toString();
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            dataset.setValue(new Double((Integer) rowData.get(column)), cutline, rowData.get(0).toString());
        }

        // ����3DЧ��ͼ
        JFreeChart chart = ChartFactory.createBarChart3D(cutline + "�����ݷ���", "��Ʒ", "����λ��Ԫ��",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        chart.addSubtitle(new TextTitle("���� ͳ�����ڣ�" + Today.getDate()));
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW, 0, 300, Color.GREEN, false));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        CategoryAxis3D domainAxis = (CategoryAxis3D) plot.getDomainAxis();

        domainAxis.setLowerMargin(0.03);
        domainAxis.setUpperMargin(0.02);

        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6));

        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();

        renderer.setItemLabelsVisible(true);
        renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, //���α�ǩê��
                TextAnchor.CENTER_LEFT, //��ǩ�ı�ê��
                TextAnchor.CENTER_LEFT, //��ǩ��תê��
                -Math.PI / 2));//��ǩ��ת�Ƕ�

        return chart;
    }
}
