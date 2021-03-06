package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AddTableInSection {

	public static void main(String[] args) {
		Document document = new Document();// 创建文档对象
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\在小节中添加表格.pdf"));// 关联文档对象与输出流
			document.open();// 打开文档
			BaseFont Chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 定义基础字体
			Font fontChinese1 = new Font(Chinese, 18, Font.BOLDITALIC,
					BaseColor.RED);// 实例化字体类、设置字体大小和颜色
			Font fontChinese2 = new Font(Chinese, 15, Font.BOLDITALIC,
					BaseColor.BLUE);// 实例化字体类、设置字体大小和颜色
			Font fontChinese3 = new Font(Chinese, 12, Font.NORMAL,
					BaseColor.BLACK);// 实例化字体类、设置字体大小和颜色
			Paragraph paragraph = new Paragraph("章节", fontChinese1);// 创建段落对象
			Chapter chapter = new Chapter(paragraph, 1);// 创建章节对象
			paragraph = new Paragraph("小节", fontChinese2);// 创建段落对象
			Section section = chapter.addSection(paragraph);// 创建并加入小节对象
			PdfPTable table = new PdfPTable(3);// 创建表格对象
			table.addCell("1,1");// 将单元格顺次的加入到表格，当一行充满时自动换行
			table.addCell("1,2");
			table.addCell("1,3");
			table.addCell("2,1");
			table.addCell("2,2");
			table.addCell("2,3");
			table.addCell("3,1");
			table.addCell("3,2");
			table.addCell("3,3");
			paragraph = new Paragraph("\n下面是小节中添加的表格：\n\n", fontChinese3);// 创建段落
			section.add(paragraph);// 向小节添加段落内容
			section.add(table);// 将表格添加到小节
			document.add(chapter);// 向文档添加章节
			document.close();// 关闭文档
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
