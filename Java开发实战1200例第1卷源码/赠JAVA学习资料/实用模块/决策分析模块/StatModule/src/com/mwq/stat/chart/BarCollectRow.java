/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.chart;

import com.mwq.stat.dataform.DataForm;
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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 版权所有：明日科技有限公司
 * @author Administrator
 */
public class BarCollectRow extends ChartPanel implements DataFormInterface {

    public BarCollectRow() {
        super(createChart());
    }

    public static JFreeChart createChart() {

        // 创建绘图数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int column = 1; column < columnName.size(); column++) {
            for (int row = 0; row < tableData.size(); row++) {
                Vector rowData = tableData.get(row);
                dataset.setValue(new Double((Integer) rowData.get(column)), columnName.get(column), rowData.get(0).toString());
            }
        }

        // 创建3D效果图
        JFreeChart chart = ChartFactory.createStackedBarChart3D("数据按行汇总", "商品", "金额（单位：元）", dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.addSubtitle(new TextTitle("—— 统计日期：" + Today.getDate()));
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.YELLOW, 0, 300, Color.GREEN, false));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        CategoryAxis3D domainAxis = (CategoryAxis3D) plot.getDomainAxis();

        domainAxis.setLowerMargin(0.03);
        domainAxis.setUpperMargin(0.02);

        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6));

        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();

        renderer.setMinimumBarLength(30);
        renderer.setItemLabelsVisible(true);
        renderer.setItemLabelFont(new Font("黑体", Font.PLAIN, 12));
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        return chart;
    }
}
