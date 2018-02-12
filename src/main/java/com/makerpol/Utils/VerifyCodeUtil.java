package com.makerpol.Utils;

import java.util.Random;

/**
 * 验证码
 * @author user
 *
 */
public class VerifyCodeUtil {
	private static String randomStr = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String getVerifyCode() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for(int i=0 ; i<4 ; i++) {
			buffer.append(randomStr.charAt(random.nextInt(randomStr.length())));
		}
		
		return buffer.toString();
	} 
}
