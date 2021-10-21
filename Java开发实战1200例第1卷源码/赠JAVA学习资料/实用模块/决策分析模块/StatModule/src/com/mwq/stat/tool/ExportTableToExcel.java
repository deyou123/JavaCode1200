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
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ExportTableToExcel implements DataFormInterface {

    public ExportTableToExcel(File file, String heading, String subheading, String inscribe) {
        try {
            WritableWorkbook workbook = null;// 创建工作薄

            if (file.exists()) {// 文件已经存在

                workbook = Workbook.createWorkbook(file, Workbook.getWorkbook(file));
            } else {// 文件还不存在

                workbook = Workbook.createWorkbook(file);
            }

            WritableSheet sheet = workbook.createSheet(
                    heading, workbook.getNumberOfSheets());// 创建工作表

            int mergeNumberOfColumns = columnName.size();// 获取表格列数

            fillHeading(sheet, heading, mergeNumberOfColumns);// 填写主标题

            fillSubheading(sheet, subheading, mergeNumberOfColumns);// 填写副标题

            fillInscribe(sheet, inscribe, mergeNumberOfColumns);// 填写落款

            fillColumnName(sheet);// 填写列名

            fillCell(sheet);// 填写数据

            workbook.write();// 写入工作薄

            workbook.close();// 关闭工作薄

        } catch (Exception ex) {
            Logger.getLogger(ExportTableToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // 填写主标题
    private void fillHeading(WritableSheet sheet, String heading, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);// 创建格式化对象

        format.setAlignment(Alignment.CENTRE);// 水平居中显示

        format.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中显示

        sheet.mergeCells(0, 0, mergeNumberOfColumns - 1, 0);// 合并单元格

        sheet.setRowView(0, 600);// 设置行高

        sheet.addCell(new Label(0, 0, heading, format));// 填写工作表

    }

    //填写副标题
    private void fillSubheading(WritableSheet sheet, String subheading, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);// 定义格式化对象

        format.setAlignment(Alignment.CENTRE);// 水平居中显示

        format.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中显示

        sheet.mergeCells(0, 1, mergeNumberOfColumns - 1, 1);// 合并单元格

        sheet.setRowView(1, 400);// 设置行高

        sheet.addCell(new Label(0, 1, subheading, format));// 填写工作表

    }

    //填写落款
    private void fillInscribe(WritableSheet sheet, String inscribe, int mergeNumberOfColumns)
            throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);// 定义格式化对象

        format.setAlignment(Alignment.CENTRE);// 水平居中显示

        sheet.mergeCells(0, 2, mergeNumberOfColumns - 1, 2);// 合并单元格

        sheet.addCell(new Label(0, 2, inscribe, format));// 填写工作表

    }

    //填写列名
    private void fillColumnName(WritableSheet sheet) throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.RED);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);// 定义格式化对象

        format.setAlignment(Alignment.CENTRE);// 水平居中显示

        sheet.setColumnView(0, 15);// 设置列宽

        //填写工作表
        for (int column = 0; column < columnName.size(); column++) {
            sheet.addCell(new Label(column, 3, columnName.get(column), format));
        }
    }

    //填写数据
    private void fillCell(WritableSheet sheet) throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);// 定义格式化对象

        format.setAlignment(Alignment.CENTRE);// 水平居中显示

        //填写工作表
        for (int row = 0; row < tableData.size(); row++) {
            Vector rowData = tableData.get(row);
            for (int column = 0; column < rowData.size(); column++) {
                sheet.addCell(new Label(column, row + 4, rowData.get(column).toString(), format));
            }
        }
    }
}
