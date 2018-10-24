package test.java.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

public class CryptoUtils {

	private static Random r = new Random();

	public static int randomInt(int from, int to) {
		return from + r.nextInt(to - from);
	}

	public static String randChar(int charCount) {
		String charValue = "";
		// 生成随机字母串
		for (int i = 0; i < charCount; i++) {
			// 键盘上字符产生随机数
			char c = (char) (randomInt(33, 128));
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public static SecretKey getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException("初始化密钥出现异常");
		}
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 *            加密后转换为base64格式的字符串
	 * @param strKey
	 *            加密用的Key
	 * @return 解密的字符串
	 * 
	 *         首先base64解密，而后在用key解密
	 */
	public static String getDecString(String strMi, String strKey) {

		try {
			SecretKey secretKey = getKey(strKey);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化

			byte[] bytes = Base64.decode(strMi);

			byte[] result = cipher.doFinal(bytes);
			String strMing = new String(result, "utf-8");

			return strMing; // 解密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * RSA私钥解密密文
	 * 
	 * @param content
	 *            待解密密文
	 * @return 明文
	 */
	public static String privateDec(String content, String prikeyStr) {
		try {
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey privkey = null;
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			InputStream key = new ByteArrayInputStream(prikeyStr.getBytes("utf-8"));
			byte[] pribytes = new byte[new Long(prikeyStr.length()).intValue()];
			if (key.read(pribytes) > 0) {
				// 生成私钥
				PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(new String(pribytes)));
				privkey = keyf.generatePrivate(priPKCS8);
				cipher.init(Cipher.DECRYPT_MODE, privkey);
				byte[] newPlainText = cipher.doFinal(Base64.decode(content));

				return (new String(newPlainText));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	/**
	 * RSA公钥加密明文
	 * 
	 * @param content
	 *            待加密明文
	 * @return 密文
	 */
	public static String publicEnc(String content, String pk) {
		try {
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			PublicKey pubkey = null;
			InputStream is = new ByteArrayInputStream(pk.getBytes("utf-8"));

			byte[] pubbytes = new byte[new Long(pk.length()).intValue()];
			if (is.read(pubbytes) > 0) {
				X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decode(new String(pubbytes)));

				pubkey = keyf.generatePublic(pubX509);
				cipher.init(Cipher.ENCRYPT_MODE, pubkey);
				byte[] cipherText = cipher.doFinal(content.getBytes());
				// 转换为Base64编码存储，以便于internet传送
				return Base64.encode(cipherText);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 加密以String明文输入,String密文输出
	 * 
	 * @param strContent
	 *            待加密字符串
	 * @param strKey
	 *            加密用的Key
	 * @return 加密后转换为base64格式字符串
	 */
	public static String getEncString(String strContent, String strKey) {
		String strMi = "";
		try {
			SecretKey secretKey = getKey(strKey);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			byte[] byteContent = strContent.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);

			strMi = Base64.encode(result);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strMi; // 加密
	}

}
