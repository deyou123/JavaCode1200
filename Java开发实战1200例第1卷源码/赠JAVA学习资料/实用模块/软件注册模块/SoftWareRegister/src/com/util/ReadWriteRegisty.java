package com.util;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import com.encrypt.MD5;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class ReadWriteRegisty {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		System.out.println(ReadWriteRegisty.ReadValue("myprograme", "programe1").equals("ע�����û�и�ֵ"));
	}
	public static String ReadValue(String node,String key){
		Preferences pre=Preferences.systemRoot().node(node);
		return pre.get(key, "ע�����û�и�ֵ");
		
	}
	public static void WriteValue(String node,String []keys,Object [] values){
		Preferences pre = Preferences.systemRoot().node(node); 
		for(int i = 0; i < keys.length; i++){
			pre.put(keys[i],String.valueOf(values[i]));
		}
		
	}
	public static void WriteValue(String node,String keys,String values){
		Preferences pre = Preferences.systemRoot().node(node); 
			pre.put(keys,values);
	}
	
}
