package com.mwq.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class Validate {

	public static boolean execute(String rule, String content) {
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(Validate.execute("[1-9]{1}([0-9]{1})", "1"));
	}

}
