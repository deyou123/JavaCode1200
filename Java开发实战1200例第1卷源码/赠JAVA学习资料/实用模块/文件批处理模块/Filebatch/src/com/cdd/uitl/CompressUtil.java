package com.cdd.uitl;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class CompressUtil {
	public static void zipFile(String baseDirName,String fileName,String targetFileName){
		if(baseDirName == null){
			System.out.println("压缩失败 " +baseDirName);
			return ;
		}
		File baseDir = new File(baseDirName);
		if(!baseDir.exists() || (!baseDir.isDirectory())){
			System.out.println("压缩失败 " + baseDirName);
			return ;
		}
		String baseDirPath = baseDir.getAbsolutePath();
		File targetFile = new File(targetFileName);
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFile));
			if(fileName.equals("*")){
				CompressUtil.dirToZip(baseDirPath,baseDir,out);
			}
			else{
				File file = new File(baseDir,fileName);
				if(file.isFile()){
					CompressUtil.fileToZip(baseDirPath,file,out);
				}
				else{
					CompressUtil.dirToZip(baseDirPath, file, out);
				}
				
			}
			out.close();
			System.out.println("压缩文件成功 "+targetFileName);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public static void fileToZip(String baseDirPath, File file, ZipOutputStream out) {
		FileInputStream in = null;
		ZipEntry entry = null;
		byte[] buffer = new byte[4096];
		int bytes_read;
		if(file.isFile()){
			try {
				in = new FileInputStream(file);
				entry = new ZipEntry(getEntryName(baseDirPath, file));
				out.putNextEntry(entry);
				while((bytes_read = in.read(buffer))!= -1){
					out.write(buffer,0,bytes_read);					
				}
				out.closeEntry();
				in.close();
				System.out.println("添加问价"+file.getAbsolutePath()+"被添加到ZIP文件中");
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}

	public static void dirToZip(String baseDirPath, File dir, ZipOutputStream out) {
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			if(files.length == 0){
				ZipEntry entry = new ZipEntry(getEntryName(baseDirPath,dir));
				try {
					out.putNextEntry(entry);
					out.closeEntry();
					
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return ;
				
			}
			for(int i =0 ;i<files.length;i++){
				if(files[i].isFile()){
					CompressUtil.fileToZip(baseDirPath, files[i], out);
					
				}
				else{
					CompressUtil.dirToZip(baseDirPath,files[i], out);
				}
			}
		}
		
	}
	public static String getEntryName(String baseDirPath, File file) {
		if(!baseDirPath.endsWith(File.separator)){
			baseDirPath += File.separator;
			
		}
		String filePath  = file.getAbsolutePath();
		if(file.isDirectory()){
			filePath += "/";
			
		}
		int index = filePath.indexOf(baseDirPath);		
		return filePath.substring(index,baseDirPath.length());
	}

	public static void upzipFile(String zipFileName,String targetBaseDirName){
		if(!targetBaseDirName.endsWith(File.separator)){
			targetBaseDirName+=File.separator;
		}
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String targetFileName = null;
			byte[] buffer = new byte[4096];
			int bytes_read;
			Enumeration entrys = zipFile.entries();
			while(entrys.hasMoreElements()){
				entry = (ZipEntry)entrys.nextElement();
				entryName = entry.getName();
				targetFileName = targetBaseDirName + entryName;
				if(entry.isDirectory()){
					new File(targetFileName).mkdirs();
					continue;
				}
				else{
					new File(targetFileName).getParentFile().mkdirs();
					File targetFile = new File(targetFileName);
					System.out.println("创建文件 "+targetFile.getAbsolutePath());
					FileOutputStream os = new FileOutputStream(targetFile);
					InputStream is = new FileInputStream(targetFile);
					while((bytes_read = is.read(buffer))!=-1){
						os.write(buffer,0,bytes_read);
					}
					os.close();
					is.close();
				}
				System.out.println("解压缩文件成功");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
}
