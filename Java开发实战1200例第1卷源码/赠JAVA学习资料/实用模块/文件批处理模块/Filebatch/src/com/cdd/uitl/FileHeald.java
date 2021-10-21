package com.cdd.uitl;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class FileHeald {
	// ���Ƶ����ļ�����
	
public void copyFile(String oldPath, String newPath) {
	try {
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // �ļ�����ʱ
			InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // �ֽ��� �ļ���С
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	//ɾ�������ļ���
/**
 * @param file Ҫɾ�����ļ�����
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
	// ���������ļ��з���
public void copyFolder(String oldPath, String newPath) {
	try {
		(new File(newPath)).mkdirs(); // ����ļ��в����� �������ļ���
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
		System.out.println("���������ļ������ݲ�������");
		e.printStackTrace();
	}
}

// �ж�date1�Ƿ���date2֮ǰ
/**
 * 
 * @param date1
 * @param date
 * @return
 */
public boolean isDateBefore(String date1, String date) {
	boolean b = true; // ���ݸ÷����ķ���ֵ���ñ���
	DateFormat df = DateFormat.getDateTimeInstance(); // ���ʱ���ʽ��ΪϵͳĬ�ϵĸ�ʽ
	try {
		b = df.parse(date1).before(df.parse(date)); // �ж�date1�Ƿ���date2֮ǰ
	} catch (ParseException e) {

		e.printStackTrace();
	}
	return b;
}
// �½��ļ���
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
		System.out.println("�½��ļ��в�������");
		e.printStackTrace();
	}
}

	// ��ȡ�����ļ�������
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

	// �½��ļ�
public void createFile(String fileName) {
	try {
		String myFileName = fileName;
		File file = new File(myFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
	} catch (Exception e) {
		System.out.println("�½��ļ���������");
		e.printStackTrace();
	}
}
	
	// �ļ�����������
public void renamePath(String oldpath, String newPath) {
	File file = new File(oldpath);
	File files = new File(newPath);
	if (!files.exists()) {
		file.renameTo(files);
	}
}

	// �ļ������ʽת������
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
	//��ȡ������Ч�̷�����
	public File[] getRoot(){
		File[] roots = File.listRoots();		
		return roots;
	}
	//��ȡ���������ļ�����
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
      tmp=list.removeFirst();			//�Ƴ������ؼ����е�һ��
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
