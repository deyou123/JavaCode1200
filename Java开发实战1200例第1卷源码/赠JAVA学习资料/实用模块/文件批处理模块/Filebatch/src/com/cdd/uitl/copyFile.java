package com.cdd.uitl;

import java.io.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class copyFile {
	public boolean cFile(String from,String to){

	    File fromFile,toFile;
	    fromFile = new File(from);
	    toFile = new File(to);
	    FileInputStream fis = null;
	    FileOutputStream fos = null;

	    try{
	      toFile.createNewFile();
	      fis = new FileInputStream(fromFile);
	      fos = new FileOutputStream(toFile);
	      int bytesRead;
	      byte[] buf = new byte[4 * 1024];  // 4K buffer
	      while((bytesRead=fis.read(buf))!=-1){
	        fos.write(buf,0,bytesRead);
	      }
	      fos.flush();
	      fos.close();
	      fis.close();
	    }catch(IOException e){
	      System.out.println(e);
	      return false;
	    }
	    return true;

	  }
	public static void main(String[] args) {
		copyFile cf = new copyFile();
		cf.cFile("E:/cfbx.java","E:/2.java");
	}
}
