package com.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.util.RegisterMark;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class encrypt {
	public BigInteger Execencrypt(String str, String register) {
		// TODO �Զ����ɹ��캯�����
		BigInteger codeStringBigint = BigInteger.ZERO;
		try {
			File f = new File("a.ini");
			System.out.println(!f.exists());
			if (!f.exists()) {
				new genKey();
				// ��ȡ��Կ
				FileInputStream FIS = new FileInputStream("RSAPubKey.dat");
				ObjectInputStream OIS = new ObjectInputStream(FIS);
				RSAPublicKey RSAPK = (RSAPublicKey) OIS.readObject();
				// �õ�������Կ�Ĳ���
				BigInteger e = RSAPK.getPublicExponent();
				BigInteger n = RSAPK.getModulus();
				// System.out.println("���ܵ���ԿΪ:"+e);
				// System.out.println("ȡģ��ģ��Ϊ:"+n);
				// ������תΪ������
				byte[] strByte = (str.concat(register)).getBytes("UTF8");
				BigInteger m = new BigInteger(strByte);
				// ���м��ܲ���
				codeStringBigint = m.modPow(e, n);
				System.out.println("���ܺ������Ϊ��" + codeStringBigint);

				// ��������
				Properties pro = new Properties();
//				SimpleDateFormat sf = new SimpleDateFormat(
//						"yyyy-MM-hh HH:mm:ss");
				pro.put("register", String.valueOf(codeStringBigint));
				try {
					// ����Ϣ������a.ini�ļ���
					pro.store(new FileOutputStream("a.ini", true), null);
					// ���Դ�a.ini��ͨ��Properties.get������ȡ������Ϣ
					pro.load(new FileInputStream("a.ini"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// String codeString =codeStringBigint.toString();
			// BufferedWriter out=new BufferedWriter(new OutputStreamWriter(new
			// FileOutputStream("RSAmi.dat")));
			// out.write(codeString,0,codeString.length());
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codeStringBigint;
	}
	public static void main(String[] args) {
		new encrypt().Execencrypt(RegisterMark.getMAC(), "11923036320024028839");
		System.out.println(new decrypt().Execdecrypt());
	}
}
