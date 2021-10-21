/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.chart;

import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.tool.Today;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 * 
 * @author Administrator
 */
public class PieAnalyseRow extends ChartPanel implements DataFormInterface {

    public PieAnalyseRow(int row) {
        super(createChart(row));//����JFreeChart���󴴽�ChartPanel����

        setPreferredSize(new Dimension(400, 300));//����ͼ��������ѡ��С

    }

    public static JFreeChart createChart(int row) {//����JFreeChart����

        // ������ͼ���ݼ�
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector rowData = tableData.get(row);
        for (int column = 1; column < columnName.size(); column++) {
            dataset.setValue(columnName.get(column), new Double((Integer) rowData.get(column)));
        }

        // ����3DЧ��ͼ
        JFreeChart chart = ChartFactory.createPieChart3D(
                rowData.get(0).toString() + "�ı�������", dataset, true, true, false);

        chart.addSubtitle(new TextTitle("���� ͳ�����ڣ�" + Today.getDate()));//���ø�����

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW,
                0, 300, Color.GREEN, false));//���ñ���ɫ

        PiePlot3D plot = (PiePlot3D) chart.getPlot();//��û�ͼ������

        plot.setDirection(Rotation.ANTICLOCKWISE);//���û��Ʒ���

        plot.setForegroundAlpha(0.8f);//����ǰ��������ͼ��͸����

        // ����ͼ����ʽ
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}��{1}��",
                new DecimalFormat("��0.0E0"), new DecimalFormat()));

        // ������ͨ��ǩ��ʽ
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}��{2}��",
                new DecimalFormat(), new DecimalFormat("0%")));

        // ����������ǩ��ʽ
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}��{1}��{2}��",
                new DecimalFormat("��#,###.00"), new DecimalFormat("0.00%")));

        return chart;
    }
}
