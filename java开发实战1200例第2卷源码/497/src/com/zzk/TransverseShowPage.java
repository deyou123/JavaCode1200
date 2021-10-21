package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class TransverseShowPage {

	public static void main(String[] args) {
		Document document = new Document();// 创建文档对象设置文档大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\横向显示页面.pdf"));// 关联文档对象与输出流
			Rectangle pageSize= new Rectangle(150,220);// 设置页面大小
			pageSize = pageSize.rotate();
			document.setPageSize(pageSize); // 设置页面大小
			document.open();// 打开文档
			document.add(new Paragraph("Page Size"));// 向文档中添加内容   
	        document.close();// 关闭文档  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}			          
	}
}
