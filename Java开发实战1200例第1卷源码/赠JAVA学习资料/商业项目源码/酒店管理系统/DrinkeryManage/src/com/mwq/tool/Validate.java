package com.mwq.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class Validate {

	public static boolean execute(String rule, String content) {
		Pattern pattern = Pattern.compile(rule);// ������֤���򴴽�Pattern����
		Matcher matcher = pattern.matcher(content);// ������֤���ݻ��Matcher����
		return matcher.matches();// ������֤���
	}

}
