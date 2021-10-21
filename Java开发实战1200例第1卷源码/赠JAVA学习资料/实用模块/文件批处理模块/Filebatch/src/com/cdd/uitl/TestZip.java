package com.cdd.uitl;
import java.util.zip.*;
import java.io.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class TestZip 

{
	public void zip(String zipFileName, String inputFile) throws Exception {
		File file = new File(inputFile);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));			//创建ZipOutputStream类对象
		zip(out, file, "");				//调用方法
		out.close();					//将流关闭
	}
	private void zip(ZipOutputStream out, File f, String base) throws Exception { //方法重载
		if (f.isDirectory()) {				//测试此抽象路径名表示的文件是否是一个目录。
			File[] fl = f.listFiles();			//获取路径数组
			out.putNextEntry(new ZipEntry(base + "/"));	//写入此目录的entry
			base = base.length() == 0 ? "" : base + "/";		//判断参数是否为空
			for (int i = 0; i < fl.length; i++) {				//循环遍历数组中文件
				zip(out, fl[i], base + fl[i]);
			}
		} else {
			out.putNextEntry(new ZipEntry(base));		//创建新的进入点
			FileInputStream in = new FileInputStream(f);	//创建FileInputStream对象
			int b;									//定义int型变量
			System.out.println(base);				
			while ((b = in.read()) != -1) {				//如果没有到达流的尾部
				out.write(b);						//将字节写入当前Zip条目
			}
			in.close();								//关闭流
		}
	}
	public void unzip(String zipFileName,String outputDirectory){
		ZipInputStream in;
		try {
			in = new ZipInputStream(new FileInputStream(zipFileName));
			ZipEntry z;
			while ((z=in.getNextEntry() )!= null)
			{	
			if (z.isDirectory())
				{
					String name=z.getName();
					name=name.substring(0,name.length()-1);
					File f=new File(outputDirectory+File.separator+name);
					f.mkdir();
				}
				else{
					String str = z.getName();
					int index = str.lastIndexOf("\\");
					File f=new File(outputDirectory+"\\"+str.substring(index+1 , str.length()));					
					System.out.println(" STR "+str.substring(index+1 , str.length()));
					System.out.println(" FILE "+outputDirectory+"\\");
					f.createNewFile();
					FileOutputStream out=new FileOutputStream(f);
					int b;
					while ((b=in.read()) != -1)
						out.write(b);
					out.close();
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
