package com.ccw.common;

public class DesEncrypt {
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for(int n=0; n<b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if(stmp.length() == 1) {
				hs = hs + "0" + stmp;
			}else {
				hs = hs + stmp;
			}
		}
		
		return hs.toUpperCase();
	}
	
	public static byte[] hex2byte(byte[] b) {
		if((b.length%2) != 0) {
			throw new IllegalArgumentException("length error!");
		}
		byte[] b2 = new byte[b.length/2];
		for(int n=0; n<b.length; n+=2) {
			String item = new String(b, n, 2);
			b2[n/2] = (byte)Integer.parseInt(item, 16);
		}
		
		return b2;
	}
	
	public static String encrypt(String input) {
		byte[] start = input.getBytes();
		return DesEncrypt.byte2hex(start);
	}
	
	public static String decrypt(String input) {
		byte[] end = input.getBytes();
		return new String(DesEncrypt.hex2byte(end));
	}
	
//	public static void main(String[] args) {
//		String words = ("C-1234567890" + Params.ADMIN_MAIL_INTERVAL + "vincezzh" + Params.ADMIN_MAIL_INTERVAL + "abc123");
//		byte[] start = words.getBytes();
//		String startContent = DesEncrypt.byte2hex(start);
//		System.out.println(startContent);
//		byte[] end = startContent.getBytes();
//		byte[] endContent = DesEncrypt.hex2byte(end);
//		String endEnd = new String(endContent);
//		System.out.println(endEnd);
//	}
}
