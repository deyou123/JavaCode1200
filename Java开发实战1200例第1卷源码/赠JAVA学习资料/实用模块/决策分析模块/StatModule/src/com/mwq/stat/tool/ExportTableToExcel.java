/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.stat.tool;

import com.mwq.stat.dataform.DataFormInterface;
import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class ExportTableToExcel implements DataFormInterface {

    public ExportTableToExcel(File file, String heading, String subheading, String inscribe) {
        try {
            WritableWorkbook workbook = null;// ����������

            if (file.exists()) {// �ļ��Ѿ�����

                workbook = Workbook.createWorkbook(file, Workbook.getWorkbook(file));
            } else {// �ļ���������

                workbook = Workbook.createWorkbook(file);
            }

            WritableSheet sheet = workbook.createSheet(
                    heading, workbook.getNumberOfSheets());// ����������

            int mergeNumberOfColumns = columnName.size();// ��ȡ�������

            fillHeading(sheet, heading, mergeNumberOfColumns);// ��д������

            fillSubheading(sheet, subheading, mergeNumberOfColumns);// ��д������

            fillInscribe(sheet, inscribe, mergeNumberOfColumns);// ��д���

            fillColumnName(sheet);// ��д����

            fillCell(sheet);// ��д����

            workbook.write();// д�빤����

            workbook.close();// �رչ�����

        } catch (Exception ex) {
            Logger.getLogger(ExportTableToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ��д������
    private void fillHeading(WritableSheet sheet, String heading, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// ��������

        WritableCellFormat format = new WritableCellFormat(font);// ������ʽ������

        format.setAlignment(Alignment.CENTRE);// ˮƽ������ʾ

        format.setVerticalAlignment(VerticalAlignment.CENTRE);// ��ֱ������ʾ

        sheet.mergeCells(0, 0, mergeNumberOfColumns - 1, 0);// �ϲ���Ԫ��

        sheet.setRowView(0, 600);// �����и�

        sheet.addCell(new Label(0, 0, heading, format));// ��д������

    }

    //��д������
    private void fillSubheading(WritableSheet sheet, String subheading, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// ��������

        WritableCellFormat format = new WritableCellFormat(font);// �����ʽ������

        format.setAlignment(Alignment.CENTRE);// ˮƽ������ʾ

        format.setVerticalAlignment(VerticalAlignment.CENTRE);// ��ֱ������ʾ

        sheet.mergeCells(0, 1, mergeNumberOfColumns - 1, 1);// �ϲ���Ԫ��

        sheet.setRowView(1, 400);// �����и�

        sheet.addCell(new Label(0, 1, subheading, format));// ��д������

    }

    //��д���
    private void fillInscribe(WritableSheet sheet, String inscribe, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// ��������

        WritableCellFormat format = new WritableCellFormat(font);// �����ʽ������

        format.setAlignment(Alignment.CENTRE);// ˮƽ������ʾ

        sheet.mergeCells(0, 2, mergeNumberOfColumns - 1, 2);// �ϲ���Ԫ��

        sheet.addCell(new Label(0, 2, inscribe, format));// ��д������

    }

    //��д����
    private void fillColumnName(WritableSheet sheet) throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// ��������

        WritableCellFormat format = new WritableCellFormat(font);// �����ʽ������

        format.setAlignment(Alignment.CENTRE);// ˮƽ������ʾ

        sheet.setColumnView(0, 15);// �����п�

        //��д������
        for (int column = 0; column < columnName.size(); column++) {
            sheet.addCell(new Label(column, 3, columnName.get(column), format));
        }
    }

    //��д����
    private void fillCell(WritableSheet sheet) throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);// ��������

        WritableCellFormat format = new WritableCellFormat(font);// �����ʽ������

        format.setAlignment(Alignment.CENTRE);// ˮƽ������ʾ

        //��д������
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            for (int column = 0; column < rowData.size(); column++) {
                sheet.addCell(new Label(column, row + 4, rowData.get(column).toString(), format));
            }
        }
    }
}
