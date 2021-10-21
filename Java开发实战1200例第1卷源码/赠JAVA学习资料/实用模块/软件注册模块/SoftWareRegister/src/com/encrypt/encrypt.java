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
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class encrypt {
	public BigInteger Execencrypt(String str, String register) {
		// TODO 自动生成构造函数存根
		BigInteger codeStringBigint = BigInteger.ZERO;
		try {
			File f = new File("a.ini");
			System.out.println(!f.exists());
			if (!f.exists()) {
				new genKey();
				// 读取公钥
				FileInputStream FIS = new FileInputStream("RSAPubKey.dat");
				ObjectInputStream OIS = new ObjectInputStream(FIS);
				RSAPublicKey RSAPK = (RSAPublicKey) OIS.readObject();
				// 得到两个公钥的参数
				BigInteger e = RSAPK.getPublicExponent();
				BigInteger n = RSAPK.getModulus();
				// System.out.println("加密的密钥为:"+e);
				// System.out.println("取模的模数为:"+n);
				// 将明文转为大整数
				byte[] strByte = (str.concat(register)).getBytes("UTF8");
				BigInteger m = new BigInteger(strByte);
				// 进行加密操作
				codeStringBigint = m.modPow(e, n);
				System.out.println("加密后的密文为：" + codeStringBigint);

				// 保存密文
				Properties pro = new Properties();
//				SimpleDateFormat sf = new SimpleDateFormat(
//						"yyyy-MM-hh HH:mm:ss");
				pro.put("register", String.valueOf(codeStringBigint));
				try {
					// 将信息保存在a.ini文件中
					pro.store(new FileOutputStream("a.ini", true), null);
					// 可以从a.ini中通过Properties.get方法读取配置信息
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
