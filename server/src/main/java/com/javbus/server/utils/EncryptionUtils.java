package com.javbus.server.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptionUtils {

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	public static final int SALT_BYTE_SIZE = 32 / 2; // 盐的长度
	public static final int HASH_BIT_SIZE = 128 * 2; // 生成密文的长度
	public static final int PBKDF2_ITERATIONS = 1000; // 迭代次数

	public static String accountEncode(String plain, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(plain.toCharArray(), fromHex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return toHex(factory.generateSecret(spec).getEncoded());
	}

	/**
	 * 生成盐
	 * 
	 * @param seed
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return toHex(salt);
	}

	/**
	 * 二进制字符串转十六进制字符串
	 * 
	 * @param array
	 * @return
	 */
	private static String toHex(byte[] array) {
		log.info(">>>>>>>>>>>>>>>>>>>>二进制字符串转十六进制字符串 array[]: {}", array);
		// TODO Auto-generated method stub
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			hex = String.format("%0" + paddingLength + "d", 0) + hex;
		}
		return hex;
	}

	/**
	 * 二进制转十六进制
	 * 
	 * @param hex
	 * @return
	 */
	private static byte[] fromHex(String hex) {
		log.info(">>>>>>>>>>>>>>>>>>>>二进制转十六进制  hex: {}", hex);
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

}
