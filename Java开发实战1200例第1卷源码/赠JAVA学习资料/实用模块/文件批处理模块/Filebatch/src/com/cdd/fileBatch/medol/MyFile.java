package com.cdd.fileBatch.medol;
import java.io.File;
/**
 *版权所有：明日科技有限公司
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
