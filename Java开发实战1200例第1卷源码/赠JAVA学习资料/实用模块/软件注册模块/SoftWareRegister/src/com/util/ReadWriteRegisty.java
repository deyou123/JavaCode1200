package com.util;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import com.encrypt.MD5;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class ReadWriteRegisty {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		System.out.println(ReadWriteRegisty.ReadValue("myprograme", "programe1").equals("注册表中没有该值"));
	}
	public static String ReadValue(String node,String key){
		Preferences pre=Preferences.systemRoot().node(node);
		return pre.get(key, "注册表中没有该值");
		
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
