package com.project.gamerscreed.control.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypter
 * This class provides a method to return a hash in SHA1 of a String.
 *
 * @author: Xavi Rueda
 * @version 1.0, 5-10-2013 
 */
public class Encrypter {

	public static String getHash(String message) {
		MessageDigest md;
		byte[] buffer, digest;
		String hash;
		
		hash = new String();
		buffer = message.getBytes();
		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(buffer);
			digest = md.digest();

			for (byte aux : digest) {
				int b = aux & 0xff;
				if (Integer.toHexString(b).length() == 1)
					hash += "0";
				hash += Integer.toHexString(b);
			}
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
}
