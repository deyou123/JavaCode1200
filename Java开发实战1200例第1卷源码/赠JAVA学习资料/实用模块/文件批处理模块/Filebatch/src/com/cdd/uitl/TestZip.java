package com.cdd.uitl;
import java.util.zip.*;
import java.io.*;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class TestZip 

{
	public void zip(String zipFileName, String inputFile) throws Exception {
		File file = new File(inputFile);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));			//����ZipOutputStream�����
		zip(out, file, "");				//���÷���
		out.close();					//�����ر�
	}
	private void zip(ZipOutputStream out, File f, String base) throws Exception { //��������
		if (f.isDirectory()) {				//���Դ˳���·������ʾ���ļ��Ƿ���һ��Ŀ¼��
			File[] fl = f.listFiles();			//��ȡ·������
			out.putNextEntry(new ZipEntry(base + "/"));	//д���Ŀ¼��entry
			base = base.length() == 0 ? "" : base + "/";		//�жϲ����Ƿ�Ϊ��
			for (int i = 0; i < fl.length; i++) {				//ѭ�������������ļ�
				zip(out, fl[i], base + fl[i]);
			}
		} else {
			out.putNextEntry(new ZipEntry(base));		//�����µĽ����
			FileInputStream in = new FileInputStream(f);	//����FileInputStream����
			int b;									//����int�ͱ���
			System.out.println(base);				
			while ((b = in.read()) != -1) {				//���û�е�������β��
				out.write(b);						//���ֽ�д�뵱ǰZip��Ŀ
			}
			in.close();								//�ر���
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
