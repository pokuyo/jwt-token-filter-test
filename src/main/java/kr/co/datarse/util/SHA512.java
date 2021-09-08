package kr.co.datarse.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SHA512 {
	
	public static String getSHA512(String plain) {
		String hashedText = null;
		
		
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			String salt = new String(Base64.getEncoder().encode(bytes));
			
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(salt.getBytes());
			digest.update(plain.getBytes("utf-8"));
			hashedText = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashedText;
	}
	
	public static void main(String[] args) {
		
//		String test = SHA512.getSHA512("hongildong@gmail.com");
//		System.out.println(test);
		String test = "92a826d7a5c0bb972aba086c2d8af30af964671ab149abd3aaa3dfcba30e3504d8ec5970895ba6414d45f80daa89e4f270ae7307d54db1f9555568a208c451e5";
		System.out.println(test.length());
	}
}
