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
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class ExportTableToPDF implements DataFormInterface {

    private static BaseFont chineseFont;//�����������
    private Document document;//�ĵ�����
    private PdfWriter writer;//PDFд��������
    private PdfContentByte contentByte;//�߼�PDFд��������
    private Chapter chapter;//�¶���
    private Font chapterFont;//�±�������
    private Section section;//�ڶ���
    private Font sectionFont;//�ڱ�������
    private Section section2;//�����ڶ���
    private Font section2Font;//�����ڱ�������
    private Section section3;//�����ڶ���
    private Font section3Font;//�����ڱ�������
    private Font textFont;//�ı�����
    

    static {
        //���������������
        try {
            chineseFont = BaseFont.createFont(
                    "STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExportTableToPDF(File file, String heading, String subheading, String inscribe, ChartDialog chartDialog) {
        //�����ĵ�����
        chapterFont = new Font(chineseFont, 24, Font.BOLD, Color.BLACK);
        sectionFont = new Font(chineseFont, 20, Font.BOLD, Color.BLACK);
        section2Font = new Font(chineseFont, 16, Font.BOLD, Color.BLACK);
        section3Font = new Font(chineseFont, 14, Font.BOLD, Color.BLACK);
        textFont = new Font(chineseFont, 12, Font.NORMAL, Color.BLACK);
        try {
            //�����ĵ�����
            document = new Document();

            document.setPageSize(PageSize.A4.rotate());//����A4ֽ��Ŵ�ӡ

            document.setMargins(50, 50, 50, 50);//����ҳ�߾�

            //���PDFд����
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));

            //���ĵ�
            document.open();

            //��ø߼�PDFд����
            contentByte = writer.getDirectContent();

            //��ӷ���

            //���������
            Paragraph headingParagraph = new Paragraph(heading,
                    new Font(chineseFont, 28, Font.BOLD, Color.BLACK));//������������

            headingParagraph.setAlignment(Paragraph.ALIGN_CENTER);//������ʾ

            headingParagraph.setLeading(0, 6);//���λ��

            document.add(headingParagraph);//�����ĵ�

            //��Ӹ�����
            Paragraph subheadingParagraph = new Paragraph(subheading,
                    new Font(chineseFont, 20, Font.BOLD, Color.BLACK));//������������

            subheadingParagraph.setAlignment(Paragraph.ALIGN_CENTER);//������ʾ

            subheadingParagraph.setLeading(0, 4);//���λ��

            document.add(subheadingParagraph);//�����ĵ�

            //������
            Paragraph inscribeParagraph = new Paragraph(inscribe,
                    new Font(chineseFont, 12, Font.BOLD, Color.BLACK));//���������

            inscribeParagraph.setAlignment(Paragraph.ALIGN_RIGHT);//���Ҳ���ʾ

            inscribeParagraph.setIndentationRight(150);//����������

            inscribeParagraph.setLeading(0, 10);//���λ��

            document.add(inscribeParagraph);//�����ĵ�

            //�����ĵ�

            //����ҳüҳ��

            //ҳü
            HeaderFooter header = new HeaderFooter(new Phrase("���� ����ͳ�Ʒ��� ����", textFont), false);
            header.setBorder(HeaderFooter.NO_BORDER);
            header.setAlignment(HeaderFooter.ALIGN_CENTER);
            document.setHeader(header);

            //ҳ��
            HeaderFooter footer = new HeaderFooter(new Phrase("�� ", textFont), new Phrase(" ҳ", textFont));
            footer.setBorder(HeaderFooter.NO_BORDER);
            footer.setAlignment(HeaderFooter.ALIGN_RIGHT);
            document.setFooter(footer);

            //�����

            createChapter("���ͳ��", 1);//������

            addTable(chapter, columnName, tableData);//��ӱ��

            document.add(chapter);//������ӵ��ĵ�

            //�����

            createChapter("ͼ�η���", 2);//������

            addParagraph(chapter, "�ò��ֽ�����ͳ��ͼ�Ա����з������������˶Ա�������ݵ�����������ְ����˾ֲ���������ʱ���Ա���л���Ϊ������λ��ͨ��ͳ��ͼ�����Էֱ�����ݡ����������ƵĽǶȶԱ���е����ݽ��з�����");//��Ӷ���

            //��ӽ�

            boolean hasSection = false;//Ĭ��Ϊδ���С��

            if (chartDialog.getBarCheckBox().isSelected()) {//��������ͼ����

                hasSection = true;
                addSection("����ͼ����");//��ӽڱ���

                section.setTriggerNewPage(true);//������ҳ

                addImage(section, Bar.createChart(), 740, 390);//���ͳ��ͼ

            }
            if (chartDialog.getBarCollectCheckBox().isSelected()) {//��������ͼ����

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("����ͼ����");//��ӽڱ���

                addParagraph(section, "�ò��ֽ���������ͼ�Ա���к��н��л��ܷ���������ÿһ�к��еĻ������ݵĽǶȽ��з�����ͬʱ�����Բ鿴����������ռ�ı��أ�");//��Ӷ���

                addSection2("������");//��Ӷ����ڱ���

                section2.setTriggerNewPage(true);//������ҳ

                addImage(section2, BarCollectRow.createChart(), 740, 390);//���ͳ��ͼ

                addSection2("������");//��Ӷ����ڱ���

                section2.setTriggerNewPage(true);//������ҳ

                addImage(section2, BarCollectColumn.createChart(), 740, 390);//���ͳ��ͼ

            }
            if (chartDialog.getBarAnalyesCheckBox().isSelected()) {//��������ͼ����

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("����ͼ����");//��ӽڱ���

                addParagraph(section, "�ò��ֽ��Ա���У��У�Ϊ��λ��������ͼ���з�����������ÿһ�У��У������и����У��У��ľ�����ֵ��");//��Ӷ���

                addSection2("������");//��Ӷ����ڱ���

                addParagraph(section2, "�ò��ֽ��Ա����Ϊ��λ��������ͼ���з�����������ÿһ�������и����еľ�����ֵ��");//��Ӷ���

                for (int row = 0; row < tableData.size(); row++) {
                    addSection3(tableData.get(row).get(0).toString());//��������ڱ���

                    addImage(section3, BarAnalyseRow.createChart(row), 740, 390);//���ͳ��ͼ

                }
                addSection2("������");//��Ӷ����ڱ���

                addParagraph(section2, "�ò��ֽ��Ա����Ϊ��λ��������ͼ���з�����������ÿһ�������и����еľ�����ֵ��");//��Ӷ���

                for (int col = 1; col < columnName.size(); col++) {
                    addSection3(columnName.get(col));//��������ڱ���

                    addImage(section3, BarAnalyseColumn.createChart(col), 740, 390);//���ͳ��ͼ

                }
            }
            if (chartDialog.getPieAnalyesCheckBox().isSelected()) {//��������ͼ����

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("����ͼ����");//��ӽڱ���

                addParagraph(section, "�ò��ֽ��Ա���У��У�Ϊ��λ���ñ���ͼ���з�����������ÿһ�У��У������и����У��У���ռ�İٷֱȣ�");//��Ӷ���

                addSection2("������");//��Ӷ����ڱ���

                addParagraph(section2, "�ò��ֽ��Ա����Ϊ��λ���ñ���ͼ���з�����������ÿһ�������и�������ռ�İٷֱȣ�");//��Ӷ���

                for (int row = 0; row < tableData.size(); row++) {
                    addSection3(tableData.get(row).get(0).toString());//��������ڱ���

                    addImage(section3, PieAnalyseRow.createChart(row), 740, 390);//���ͳ��ͼ

                }
                addSection2("������");//��Ӷ����ڱ���

                addParagraph(section2, "�ò��ֽ��Ա����Ϊ��λ���ñ���ͼ���з�����������ÿһ�������и�������ռ�İٷֱȣ�");//��Ӷ���

                for (int col = 1; col < columnName.size(); col++) {
                    addSection3(columnName.get(col));//��������ڱ���

                    addImage(section3, PieAnalyseColumn.createChart(col), 740, 390);//���ͳ��ͼ

                }
            }
            if (chartDialog.getAreaCollectCheckBox().isSelected()) {//��������ͼ����

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("����ͼ����");//��ӽڱ���

                section.setTriggerNewPage(true);//������ҳ

                addImage(section, AreaCollectRow.createChart(), 740, 390);//���ͳ��ͼ

            }
            if (chartDialog.getAreaAnalyesCheckBox().isSelected()) {//��������ͼ����

                if (!hasSection) {
                    hasSection = true;
                }
                addSection("����ͼ����");//��ӽڱ���

                addParagraph(section, "�ò��ֽ��Ա����Ϊ��λ��������ͼ���з�����������ÿһ�����ݵľ������ƣ�");//��Ӷ���

                section.setBookmarkOpen(false);//������ǩΪ�۵�״̬

                for (int row = 0; row < tableData.size(); row++) {
                    addSection2(tableData.get(row).get(0).toString());//��Ӷ����ڱ���

                    section2.setTriggerNewPage(true);//������ҳ

                    addImage(section2, AreaAnalyseRow.createChart(row), 740, 390);//���ͳ��ͼ

                }
            }

            if (hasSection) {//�������˽�

                document.add(chapter);//������ӵ��ĵ�

            }

            //�ر��ĵ�
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportTableToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //������
    private void createChapter(String title, int number) throws DocumentException {
        chapter = new Chapter(new Paragraph(title.toString(), chapterFont), number);//�����¶���

        chapter.setNumberDepth(0);//����ʾ�½ڱ��

    }

    //��ӽ�
    private void addSection(String title) {
        section = chapter.addSection(new Paragraph(title, sectionFont));//��Ӳ����ؽڶ���

    }

    //��ӽ�2
    private void addSection2(String title) {
        section2 = section.addSection(new Paragraph(title, section2Font));//��Ӳ����ؽڶ���

        section2.setBookmarkOpen(false);//������ǩΪ�۵�״̬

    }

    //��ӽ�3
    private void addSection3(String title) {
        section3 = section2.addSection(new Paragraph(title, section3Font));//��Ӳ����ؽڶ���

        section3.setTriggerNewPage(true);//������ҳ

    }

    //����ı�����
    private void addParagraph(Section section, String text) {
        Paragraph paragraph = new Paragraph(text, textFont);//�����������

        paragraph.setFirstLineIndent(20);//������������

        section.add(paragraph);//��ӵ���Ӧ����

    }

    //��ӱ��
    private void addTable(Section section, Vector<String> columnName, Vector<Vector> tableData)
            throws BadElementException {

        //��ñ�������
        int columnAmount = columnName.size();

        //����������
        Table table = new Table(columnAmount);

        //������
        table.setAutoFillEmptyCells(true);//�Զ����յ�Ԫ��

        table.setPadding(2);//���õ�Ԫ��������

        table.setWidth(100F);//���ñ����ռҳ����ÿ�ȵİٷֱ�

        //���ø�����Ԫ��Ŀ��ռ����ȵİٷֱ�
        float width = 1F / (columnAmount + 1);
        float[] columnWidths = new float[columnAmount];
        columnWidths[0] = width * 2;
        for (int column = 1; column < columnAmount; column++) {
            columnWidths[column] = width;
        }
        table.setWidths(columnWidths);

        //��������
        Font headerFont = new Font(chineseFont, 12, Font.BOLD, Color.RED);//������������

        for (int column = 0; column < columnAmount; column++) {
            Cell cell = new Cell(new Paragraph(columnName.get(column), headerFont));//���嵥Ԫ�����

            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);//ˮƽ������ʾ

            cell.setHeader(true);//ָ��Ϊ���������Ĭ��Ϊfalse

            table.addCell(cell);//��ӵ������

        }
        table.endHeaders();//�����������

        //��д���
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            for (int column = 0; column < rowData.size(); column++) {
                Cell cell = new Cell(
                        new Paragraph(rowData.get(column).toString(), textFont));//���嵥Ԫ�����

                cell.setHorizontalAlignment(Cell.ALIGN_CENTER);//ˮƽ������ʾ

                table.addCell(cell);//��ӵ������

            }
        }

        section.add(table);//��ӵ���Ӧ����

    }

    //���ͼ��
    private void addImage(Section section, JFreeChart chart, int width, int height)
            throws BadElementException, IOException {
        Image img = Image.getInstance(contentByte, chart.createBufferedImage(width, height), 1);
        img.setAlignment(Image.MIDDLE);//������ʾ

        section.add(img);//��ӵ���Ӧ����

    }
}
