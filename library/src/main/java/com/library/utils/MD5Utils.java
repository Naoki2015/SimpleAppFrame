package com.library.utils;

import android.annotation.SuppressLint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(value.getBytes());
			return byte2hex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encrypt(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(bytes);
			return byte2hex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 二行制转字符串
	 */
	@SuppressLint("DefaultLocale")
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}

}
