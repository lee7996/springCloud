package com.javbus.common.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.javbus.common.enums.RSAResponseEnum;
import com.javbus.common.exception.ResponseException;

public class RSAUtil {

	public static final String CHARSET = "UTF-8";
	public static final String RSA_TYPE = "RSA";
	
	public static Map<String, String> createKeys(int keySize) {
		// 创建RSA密钥对象
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance(RSA_TYPE);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseException(RSAResponseEnum.CREATE_KEYS_ERROR.getCode(), RSAResponseEnum.CREATE_KEYS_ERROR.getMessage());
		}
		
		// 初始化RSA密钥对象，设置密钥长度
		kpg.initialize(keySize);
		// 生成密钥对
		KeyPair keyPair = kpg.generateKeyPair();
		// 获取公钥
		Key publicKey = keyPair.getPublic();
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
		// 获取私钥
		Key privateKey = keyPair.getPrivate();
		String privateKeyStr =  Base64.encodeBase64String(privateKey.getEncoded());
		
		Map<String, String> keys = new HashMap<String, String>();
		keys.put("publicKey", publicKeyStr);
		keys.put("privateKey", privateKeyStr);
		return keys;
	}
	
	/**
	 * 获取公钥
	 * @param publicKey base64编码的公钥字符串
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance(publicKey);
		X509EncodedKeySpec X509keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		return (RSAPublicKey) keyFactory.generatePublic(X509keySpec);
	}
	
	/**
	 * 获取私钥
	 * @param privateKey  base64编码的私钥字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance(privateKey);
		X509EncodedKeySpec X509keySpec = new X509EncodedKeySpec(Base64.decodeBase64(privateKey));
		return (RSAPrivateKey) keyFactory.generatePrivate(X509keySpec);
	}
	
	/**
	 * 公钥加密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicEncrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
		} catch (Exception e) {
			throw new RuntimeException("加密公钥[" + data + "]异常", e);
		}
	}
	
	/**
	 * 私钥加密
	 * @param data
	 * @param privateKey
	 * @return
	 */
	public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
		} catch (Exception e) {
			throw new RuntimeException("加密私钥[" + data + "]异常", e);
		}
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicDecrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException("解密公钥[" + data + "]异常", e);
		}
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @param privateKey
	 * @return
	 */
	public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException("解密私钥[" + data + "]异常", e);
		}
	}

	private static byte[] rsaSplitCodec(Cipher cipher, int encryptMode, byte[] bytes, int bitLength) {
		int maxBlock = 0;
		if (encryptMode == Cipher.DECRYPT_MODE) {
			maxBlock = bitLength / 8;
		} else {
			maxBlock = bitLength / 8 - 11;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] buff;
		int i = 0;
		try {
			while(bytes.length > offSet) {
				if (bytes.length - offSet > maxBlock) {
					buff = cipher.doFinal(bytes, offSet, maxBlock);
				} else {
					buff = cipher.doFinal(bytes, offSet, bytes.length - offSet);
				}
				out.write(buff, 0, buff.length);
				i++;
				offSet = 1 * maxBlock;
			}
		} catch (Exception e) {
			throw new RuntimeException("加解密数据异常", e);
		}
		byte[] resultDatas = out.toByteArray();
		IOUtils.closeQuietly(out);
		return resultDatas;
	}
}
