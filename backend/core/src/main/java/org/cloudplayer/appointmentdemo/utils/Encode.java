package org.cloudplayer.appointmentdemo.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {
	public static String MD5(String param) {
		byte[] secrets = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(param.getBytes());
			secrets = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String md5code = new BigInteger(1, secrets).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		int length = md5code.length();
		for (int i = 0; i < 32 - length; i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	//获取文件的md5值
	public static String getFileMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null){
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		System.out.println(Encode.MD5("lunacia"));
		System.out.println(Integer.valueOf("6"));
	}

}
