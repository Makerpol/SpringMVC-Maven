package com.makerpol.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * MD5加密密码
	 * @param psw
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String psw) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = bytesToHex(md5.digest(psw.getBytes("utf-8")));
		return newstr;
	}
	
	/**
	 * 检查密码
	 * @param newpsw
	 * @param oldpsw
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean checkPassword(String newpsw,String oldpsw) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(EncoderByMd5(newpsw).equals(oldpsw)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 二进制转十六进制
	 * @param bytes
	 * @return
	 */
	private static String bytesToHex(byte[] bytes) {  
		StringBuffer hexStr = new StringBuffer();  
		int num;  
		for (int i = 0; i < bytes.length; i++) {  
			num = bytes[i];  
			if(num < 0) {  
				num += 256;  
			}  
			if(num < 16){  
				hexStr.append("0");  
			}  
			hexStr.append(Integer.toHexString(num));  
		}  
		return hexStr.toString().toUpperCase();
	}
}
