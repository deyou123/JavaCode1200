package com.cdd.fileBatch.medol;
import java.io.File;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MyFile extends File{	
	public MyFile(String pathname) {
		super(pathname);		
	}
	public String toString(File file){
		String fileStr = file.getAbsolutePath();
		int index = fileStr.lastIndexOf("\\");
		String newiPath = null;
		if(index != -1){
			newiPath = fileStr.substring(index+1, fileStr.length());
		}
		return newiPath;
	}
}
