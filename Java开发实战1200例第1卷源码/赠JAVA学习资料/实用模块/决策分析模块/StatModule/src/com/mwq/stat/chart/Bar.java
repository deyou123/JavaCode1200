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
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class Bar extends ChartPanel implements DataFormInterface {

    public Bar() {
        super(createChart());//利用JFreeChart对象创建ChartPanel对象

    }

    public static JFreeChart createChart() {//创建JFreeChart对象

        // 创建绘图数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            String cutline = rowData.get(0).toString();
            for (int column = 1; column < columnName.size(); column++) {
                dataset.setValue(
                        new Double((Integer) rowData.get(column)), cutline, columnName.get(column));
            }
        }

        // 创建3D效果图
        JFreeChart chart = ChartFactory.createBarChart3D("数据概览", "日期", "金额（单位：元）",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.addSubtitle(new TextTitle("—— 统计日期：" + Today.getDate()));//设置副标题

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW,
                0, 300, Color.GREEN, false));//设置背景色

        CategoryPlot plot = (CategoryPlot) chart.getPlot();//获得绘图区对象

        CategoryAxis domainAxis = plot.getDomainAxis();//获得分类（X）轴对象

        domainAxis.setLowerMargin(0.03);//设置最左侧柱形与绘图区左边框的距离

        domainAxis.setUpperMargin(0.02);//设置最右侧柱形与绘图区右边框的距离

        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();//获得柱形对象

        renderer.setMinimumBarLength(30);//设置柱形的最小高度

        return chart;
    }
}
