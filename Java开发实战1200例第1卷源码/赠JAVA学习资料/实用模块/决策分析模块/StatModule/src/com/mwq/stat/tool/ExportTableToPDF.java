/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.tool;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.mwq.stat.chart.AreaAnalyseRow;
import com.mwq.stat.chart.AreaCollectRow;
import com.mwq.stat.chart.Bar;
import com.mwq.stat.chart.BarAnalyseColumn;
import com.mwq.stat.chart.BarAnalyseRow;
import com.mwq.stat.chart.BarCollectColumn;
import com.mwq.stat.chart.BarCollectRow;
import com.mwq.stat.chart.PieAnalyseColumn;
import com.mwq.stat.chart.PieAnalyseRow;
import com.mwq.stat.dataform.DataFormInterface;
import com.mwq.stat.frame.ChartDialog;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.JFreeChart;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ExportTableToPDF implements DataFormInterface {

    private static BaseFont chineseFont;//中文字体对象
    private Document document;//文档对象
    private PdfWriter writer;//PDF写入器对象
    private PdfContentByte contentByte;//高级PDF写入器对象
    private Chapter chapter;//章对象
    private Font chapterFont;//章标题字体
    private Section section;//节对象
    private Font sectionFont;//节标题字体
    private Section section2;//二级节对象
    private Font section2Font;//二级节标题字体
    private Section section3;//三级节对象
    private Font section3Font;//三级节标题字体
    private Font textFont;//文本字体
    

    static {
        //定义中文字体对象
        try {
            chineseFont = BaseFont.createFont(
                    "STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExportTableToPDF(File file, String heading, String subheading, String inscribe, ChartDialog chartDialog) {
        //定义文档字体
        chapterFont = new Font(chineseFont, 24, Font.BOLD, Color.BLACK);
        sectionFont = new Font(chineseFont, 20, Font.BOLD, Color.BLACK);
        section2Font = new Font(chineseFont, 16, Font.BOLD, Color.BLACK);
        section3Font = new Font(chineseFont, 14, Font.BOLD, Color.BLACK);
        textFont = new Font(chineseFont, 12, Font.NORMAL, Color.BLACK);
        try {
            //创建文档对象
            document = new Document();

            document.setPageSize(PageSize.A4.rotate());//采用A4纸横放打印

            document.setMargins(50, 50, 50, 50);//定义页边距

            //获得PDF写入器
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));

            //打开文档
            document.open();

            //获得高级PDF写入器
            contentByte = writer.getDirectContent();

            //添加封面

            //添加主标题
            Paragraph headingParagraph = new Paragraph(heading,
                    new Font(chineseFont, 28, Font.BOLD, Color.BLACK));//主标题段落对象

            headingParagraph.setAlignment(Paragraph.ALIGN_CENTER);//居中显示

            headingParagraph.setLeading(0, 6);//输出位置

            document.add(headingParagraph);//加入文档

            //添加副标题
            Paragraph subheadingParagraph = new Paragraph(subheading,
                    new Font(chineseFont, 20, Font.BOLD, Color.BLACK));//副标题段落对象

            subheadingParagraph.setAlignment(Paragraph.ALIGN_CENTER);//居中显示

            subheadingParagraph.setLeading(0, 4);//输出位置

            document.add(subheadingParagraph);//加入文档

            //添加落款
            Paragraph inscribeParagraph = new Paragraph(inscribe,
                    new Font(chineseFont, 12, Font.BOLD, Color.BLACK));//落款段落对象

            inscribeParagraph.setAlignment(Paragraph.ALIGN_RIGHT);//靠右侧显示

            inscribeParagraph.setIndentationRight(150);//设置右缩进

            inscribeParagraph.setLeading(0, 10);//输出位置

            document.add(inscribeParagraph);//加入文档

            //加入文档

            //定义页眉页脚

            //页眉
            HeaderFooter header = new HeaderFooter(new Phrase("—— 数据统计分析 ——", textFont), false);
            header.setBorder(HeaderFooter.NO_BORDER);
            header.setAlignment(HeaderFooter.ALIGN_CENTER);
            document.setHeader(header);

            //页脚
            HeaderFooter footer = new HeaderFooter(new Phrase("第 ", textFont), new Phrase(" 页", textFont));
            footer.setBorder(HeaderFooter.NO_BORDER);
            footer.setAlignment(HeaderFooter.ALIGN_RIGHT);
            document.setFooter(footer);

            //添加章

            createChapter("表格统计", 1);//创建章

            addTable(chapter, columnName, tableData);//添加表格

            document.add(chapter);//将章添加到文档

            //添加章

            createChapter("图形分析", 2);//创建章

            addParagraph(chapter, "该部分将利用统计图对表格进行分析，即包括了对表格中数据的整体分析，又包括了局部分析，此时将以表格行或列为分析单位；通过统计图，可以分别从数据、比例和走势的角度对表格中的数据进行分析！");//添加段落

            //添加节

            boolean hasSection = false;//默认为未添加小节

            if (chartDialog.getBarCheckBox().isSelected()) {//包含柱形图概览

                hasSection = true;
                addSection("柱形图概览");//添加节标题

                section.setTriggerNewPage(true);//触发新页

                addImage(section, Bar.createChart(), 740, 390);//添加统计图

            }
            if (chartDialog.getBarCollectCheckBox().isSelected()) {//包含柱形图汇总

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("柱形图汇总");//添加节标题

                addParagraph(section, "该部分将利用柱形图对表格行和列进行汇总分析，即从每一行和列的汇总数据的角度进行分析，同时还可以查看各个部分所占的比重！");//添加段落

                addSection2("汇总行");//添加二级节标题

                section2.setTriggerNewPage(true);//触发新页

                addImage(section2, BarCollectRow.createChart(), 740, 390);//添加统计图

                addSection2("汇总列");//添加二级节标题

                section2.setTriggerNewPage(true);//触发新页

                addImage(section2, BarCollectColumn.createChart(), 740, 390);//添加统计图

            }
            if (chartDialog.getBarAnalyesCheckBox().isSelected()) {//包含柱形图分析

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("柱形图分析");//添加节标题

                addParagraph(section, "该部分将以表格行（列）为单位利用柱形图进行分析，即分析每一行（列）数据中各个列（行）的具体数值！");//添加段落

                addSection2("分析行");//添加二级节标题

                addParagraph(section2, "该部分将以表格行为单位利用柱形图进行分析，即分析每一行数据中各个列的具体数值！");//添加段落

                for (int row = 0; row < tableData.size(); row++) {
                    addSection3(tableData.get(row).get(0).toString());//添加三级节标题

                    addImage(section3, BarAnalyseRow.createChart(row), 740, 390);//添加统计图

                }
                addSection2("分析列");//添加二级节标题

                addParagraph(section2, "该部分将以表格列为单位利用柱形图进行分析，即分析每一列数据中各个行的具体数值！");//添加段落

                for (int col = 1; col < columnName.size(); col++) {
                    addSection3(columnName.get(col));//添加三级节标题

                    addImage(section3, BarAnalyseColumn.createChart(col), 740, 390);//添加统计图

                }
            }
            if (chartDialog.getPieAnalyesCheckBox().isSelected()) {//包含饼形图分析

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("饼形图分析");//添加节标题

                addParagraph(section, "该部分将以表格行（列）为单位利用饼形图进行分析，即分析每一行（列）数据中各个列（行）所占的百分比！");//添加段落

                addSection2("分析行");//添加二级节标题

                addParagraph(section2, "该部分将以表格行为单位利用饼形图进行分析，即分析每一行数据中各个列所占的百分比！");//添加段落

                for (int row = 0; row < tableData.size(); row++) {
                    addSection3(tableData.get(row).get(0).toString());//添加三级节标题

                    addImage(section3, PieAnalyseRow.createChart(row), 740, 390);//添加统计图

                }
                addSection2("分析列");//添加二级节标题

                addParagraph(section2, "该部分将以表格列为单位利用饼形图进行分析，即分析每一列数据中各个行所占的百分比！");//添加段落

                for (int col = 1; col < columnName.size(); col++) {
                    addSection3(columnName.get(col));//添加三级节标题

                    addImage(section3, PieAnalyseColumn.createChart(col), 740, 390);//添加统计图

                }
            }
            if (chartDialog.getAreaCollectCheckBox().isSelected()) {//包含区域图汇总

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("区域图汇总");//添加节标题

                section.setTriggerNewPage(true);//触发新页

                addImage(section, AreaCollectRow.createChart(), 740, 390);//添加统计图

            }
            if (chartDialog.getAreaAnalyesCheckBox().isSelected()) {//包含区域图分析

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("区域图分析");//添加节标题

                addParagraph(section, "该部分将以表格行为单位利用区域图进行分析，即分析每一行数据的具体走势！");//添加段落

                section.setBookmarkOpen(false);//设置书签为折叠状态

                for (int row = 0; row < tableData.size(); row++) {
                    addSection2(tableData.get(row).get(0).toString());//添加二级节标题

                    section2.setTriggerNewPage(true);//触发新页

                    addImage(section2, AreaAnalyseRow.createChart(row), 740, 390);//添加统计图

                }
            }

            if (hasSection) {//如果添加了节

                document.add(chapter);//将章添加到文档

            }

            //关闭文档
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //创建章
    private void createChapter(String title, int number) throws DocumentException {
        chapter = new Chapter(new Paragraph(title.toString(), chapterFont), number);//创建章对象

        chapter.setNumberDepth(0);//不显示章节编号

    }

    //添加节
    private void addSection(String title) {
        section = chapter.addSection(new Paragraph(title, sectionFont));//添加并返回节对象

    }

    //添加节2
    private void addSection2(String title) {
        section2 = section.addSection(new Paragraph(title, section2Font));//添加并返回节对象

        section2.setBookmarkOpen(false);//设置书签为折叠状态

    }

    //添加节3
    private void addSection3(String title) {
        section3 = section2.addSection(new Paragraph(title, section3Font));//添加并返回节对象

        section3.setTriggerNewPage(true);//触发新页

    }

    //添加文本段落
    private void addParagraph(Section section, String text) {
        Paragraph paragraph = new Paragraph(text, textFont);//创建段落对象

        paragraph.setFirstLineIndent(20);//设置首行缩进

        section.add(paragraph);//添加到相应节中

    }

    //添加表格
    private void addTable(Section section, Vector<String> columnName, Vector<Vector> tableData)
            throws BadElementException {

        //获得表格的列数
        int columnAmount = columnName.size();

        //创建表格对象
        Table table = new Table(columnAmount);

        //定义表格
        table.setAutoFillEmptyCells(true);//自动填充空单元格

        table.setPadding(2);//设置单元格的填充量

        table.setWidth(100F);//设置表格宽度占页面可用宽度的百分比

        //设置各个单元格的宽度占表格宽度的百分比
        float width = 1F / (columnAmount + 1);
        float[] columnWidths = new float[columnAmount];
        columnWidths[0] = width * 2;
        for (int column = 1; column < columnAmount; column++) {
            columnWidths[column] = width;
        }
        table.setWidths(columnWidths);

        //定义列名
        Font headerFont = new Font(chineseFont, 12, Font.BOLD, Color.RED);//定义列名字体

        for (int column = 0; column < columnAmount; column++) {
            Cell cell = new Cell(new Paragraph(columnName.get(column), headerFont));//定义单元格对象

            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);//水平居中显示

            cell.setHeader(true);//指明为表格列名，默认为false

            table.addCell(cell);//添加到表格中

        }
        table.endHeaders();//结束列名添加

        //填写表格
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            for (int column = 0; column < rowData.size(); column++) {
                Cell cell = new Cell(
                        new Paragraph(rowData.get(column).toString(), textFont));//定义单元格对象

                cell.setHorizontalAlignment(Cell.ALIGN_CENTER);//水平居中显示

                table.addCell(cell);//添加到表格中

            }
        }

        section.add(table);//添加到相应节中

    }

    //添加图象
    private void addImage(Section section, JFreeChart chart, int width, int height)
            throws BadElementException, IOException {
        Image img = Image.getInstance(contentByte, chart.createBufferedImage(width, height), 1);
        img.setAlignment(Image.MIDDLE);//居中显示

        section.add(img);//添加到相应节中

    }
}
