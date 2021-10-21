package com.cdd.uitl;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class FileHeald {
	// 复制单个文件方法
	
public void copyFile(String oldPath, String newPath) {
	try {
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // 文件存在时
			InputStream inStream = new FileInputStream(oldPath); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	//删除整个文件夹
/**
 * @param file 要删除的文件对象
 */
public void deleteDirs(File file){
	if(file.exists()){
		if(file.isFile()){
			file.delete();				
		}
		else if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0;i<files.length;i++){
				this.deleteDirs(files[i]);
			}
			file.delete();
		}
	}
}
	// 复制整个文件夹方法
public void copyFolder(String oldPath, String newPath) {
	try {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File oldfile = new File(oldPath);
		String[] file = oldfile.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}
			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath
						+ "/" + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	} catch (Exception e) {
		System.out.println("复制整个文件夹内容操作出错");
		e.printStackTrace();
	}
}

// 判断date1是否在date2之前
/**
 * 
 * @param date1
 * @param date
 * @return
 */
public boolean isDateBefore(String date1, String date) {
	boolean b = true; // 根据该方法的返回值设置变量
	DateFormat df = DateFormat.getDateTimeInstance(); // 获得时间格式，为系统默认的格式
	try {
		b = df.parse(date1).before(df.parse(date)); // 判断date1是否在date2之前
	} catch (ParseException e) {

		e.printStackTrace();
	}
	return b;
}
// 新建文件夹
/**
 * @param strPath
 */
public void createFolder(String strPath) {
	try {
		String filePath = strPath;
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	} catch (Exception e) {
		System.out.println("新建文件夹操作出错");
		e.printStackTrace();
	}
}

	// 读取整个文件夹内容
public List getFileList(String strPath) {	
	LinkedList filelist = new LinkedList();
	File dir = new File(strPath);
	File[] file = dir.listFiles();
	if((file != null)&&file.length >0){
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				getFileList(file[i].getAbsolutePath());
			} else {
		filelist.add(file[i]);
			}
		}
	}
		return filelist;
}

	// 新建文件
public void createFile(String fileName) {
	try {
		String myFileName = fileName;
		File file = new File(myFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
	} catch (Exception e) {
		System.out.println("新建文件操作出错");
		e.printStackTrace();
	}
}
	
	// 文件重命名方法
public void renamePath(String oldpath, String newPath) {
	File file = new File(oldpath);
	File files = new File(newPath);
	if (!files.exists()) {
		file.renameTo(files);
	}
}

	// 文件编码格式转换方法
public void setEnd(String fileStr, String fileSave, String change) {
	try {
		File file = new File(fileStr);
		FileInputStream fis = new FileInputStream(file);
		byte byt[] = new byte[1024];
		File filea = new File(fileSave);
		filea.createNewFile();
		FileOutputStream fop = new FileOutputStream(filea);
		String str = "";
		int read = -1;
		while ((read = fis.read(byt)) != -1) {
			str = new String(byt, 0, read, change);
			fop.write(str.getBytes());
		}
		fop.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	//获取本地有效盘符方法
	public File[] getRoot(){
		File[] roots = File.listRoots();		
		return roots;
	}
	//获取磁盘所有文件方法
public List getList(String path){
	 LinkedList<File> list=new LinkedList<File>();
	 ArrayList<String> listPath = new ArrayList<String>();
     File dir=new File(path);
     File file[]=dir.listFiles();
     for(int i=0;i<file.length;i++){
      if(file[i].isDirectory())
       list.add(file[i]);
      else{
    	  listPath.add(file[i].getAbsolutePath());
      } 			
     }
     File tmp;
     while(!list.isEmpty()){
      tmp=list.removeFirst();			//移除并返回集合中第一项
      if(tmp.isDirectory()){
       file=tmp.listFiles();
       if(file==null)continue;
       for(int i=0;i<file.length;i++){
        if(file[i].isDirectory())
         list.add(file[i]);
        else{
        	 listPath.add(file[i].getAbsolutePath());
        }     
        
       }
      }else{
  
      }
     }
     return listPath;
}


}
