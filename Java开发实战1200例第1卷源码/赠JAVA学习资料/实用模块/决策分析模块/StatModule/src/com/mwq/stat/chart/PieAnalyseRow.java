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
        super(createChart(row));//利用JFreeChart对象创建ChartPanel对象

        setPreferredSize(new Dimension(400, 300));//设置图形面板的首选大小

    }

    public static JFreeChart createChart(int row) {//创建JFreeChart对象

        // 创建绘图数据集
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector rowData = tableData.get(row);
        for (int column = 1; column < columnName.size(); column++) {
            dataset.setValue(columnName.get(column), new Double((Integer) rowData.get(column)));
        }

        // 创建3D效果图
        JFreeChart chart = ChartFactory.createPieChart3D(
                rowData.get(0).toString() + "的比例分析", dataset, true, true, false);

        chart.addSubtitle(new TextTitle("—— 统计日期：" + Today.getDate()));//设置副标题

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW,
                0, 300, Color.GREEN, false));//设置背景色

        PiePlot3D plot = (PiePlot3D) chart.getPlot();//获得绘图区对象

        plot.setDirection(Rotation.ANTICLOCKWISE);//设置绘制方向

        plot.setForegroundAlpha(0.8f);//设置前景（饼形图）透明度

        // 设置图例样式
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}（{1}）",
                new DecimalFormat("￥0.0E0"), new DecimalFormat()));

        // 设置普通标签样式
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}（{2}）",
                new DecimalFormat(), new DecimalFormat("0%")));

        // 设置热区标签样式
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}（{1}，{2}）",
                new DecimalFormat("￥#,###.00"), new DecimalFormat("0.00%")));

        return chart;
    }
}
