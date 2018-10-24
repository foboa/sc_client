package test.java.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author suguanting on 2018/10/12.
 */
public class RASDecypt {

    public static String publicEnc(String content, String pk) {
        try {
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance(keyf.getAlgorithm());
            PublicKey pubkey = null;
            InputStream is = new ByteArrayInputStream(pk.getBytes("utf-8"));

            byte[] pubbytes = new byte[new Long(pk.length()).intValue()];
            if (is.read(pubbytes) > 0) {
                X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(new String(pubbytes)));

                pubkey = keyf.generatePublic(pubX509);
                cipher.init(Cipher.ENCRYPT_MODE, pubkey);
                byte[] byteContent = content.getBytes("utf-8");
                int inputLen = byteContent.length;
                int offLen = 0;//偏移量  
                int i = 0;  
                ByteArrayOutputStream bops = new ByteArrayOutputStream();
                while(inputLen - offLen > 0){  
                    byte [] cache;  
                    if(inputLen - offLen > 117){  
                        cache = cipher.doFinal(byteContent, offLen,117);
                    }else{  
                        cache = cipher.doFinal(byteContent, offLen,inputLen - offLen);
                    }  
                    bops.write(cache);  
                    i++;  
                    offLen = 117 * i;  
                }
                bops.close();
                byte[] encryptedData = bops.toByteArray();
                // 转换为Base64编码存储，以便于internet传送
                return Base64.encodeBase64(encryptedData).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache;
            if(inputLen - offLen > 117){
                cache = cipher.doFinal(encryptedData, offLen,117);
            }else{
                cache = cipher.doFinal(encryptedData, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        byte[] decryptedData = bops.toByteArray();
        bops.close();
        return decryptedData;
    }

    public static String publicEncNew(String content, String pk) {
        try {
            //获取公钥
            byte[] keyBytes;
            keyBytes = (new BASE64Decoder()).decodeBuffer(pk);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] byteContent = content.getBytes("utf-8");
            int inputLen = byteContent.length;
            int offLen = 0;//偏移量
            int i = 0;
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > 117){
                    cache = cipher.doFinal(byteContent, offLen,117);
                }else{
                    cache = cipher.doFinal(byteContent, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = 117 * i;
            }
            bops.close();
            byte[] encryptedData = bops.toByteArray();
            // 转换为Base64编码存储，以便于internet传送
            //return new String(Base64.encodeBase64URLSafe(encryptedData),"utf-8");
            String encryptString = (new BASE64Encoder()).encode(encryptedData);
            return encryptString;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 得到公钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


    /**
     *利用Go语言产生的公钥加密
     * @param pubkey_from_go 从服务器（go语言实现）获取的公钥
     * @param plainText 需要加密的字符串
     */
    public static String encByGoPubKey(String pubkey_from_go,String plainText) throws Exception {
        //加解密类
        Cipher cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] plainTextBytes = plainText.getBytes();
        //用Go语言产生的公钥加密
        PublicKey pubkey_go=getPublicKey(pubkey_from_go);
        cipher.init(Cipher.ENCRYPT_MODE, pubkey_go);
        byte[] enBytes = cipher.doFinal(plainTextBytes);
        String encryptString = (new BASE64Encoder()).encode(enBytes);
        return encryptString;

    }


    public static void main(String[] args) throws Exception {
        String rsaStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCzOIlKpm1UeAr1kOe8s8qoOw3dTlb/XYFPUhVv6GDwvqt0ru9kAvnj5g4HYqrwTTqJkQ7/a18tM4YO0rB4scxNs3rs2rveCHllGsRLyvD/Ozy6v0TtdKUdT9qqzV6cNQq8g5O3p8Nj7UndBezyTFtTd0f0EJ2DwbB16CI+iKs0wIDAQAB";
        Map<String,String> map = new HashMap<>();
        map.put("trans_id", UUID.randomUUID().toString());
        map.put("mobile","18938667276");
        map.put("name","吕雪瑞");
        map.put("id_card","32132419941222008X");
        map.put("channel_front_url","www.baidu.com");
        map.put("channel_notify_url","www.baidu.com");
        String str= JSON.toJSONString(map);
        System.out.println(str);
        /*String req = publicEncNew(str,rsaStr);
        System.out.println(req);
        String result = req.replaceAll("/+","-");
        result = result.replaceAll("/","_");
        System.out.println(result);*/

        String req2 = publicEncNew(str,rsaStr);
        //String req2 = encByGoPubKey(rsaStr,str);
        System.out.println(req2);
        String result = req2.replaceAll("\\+","-");
        result = result.replaceAll("/","_");
        System.out.println(result);

        //System.out.println(CryptoUtils.publicEnc(str,rsaStr));
        /*String req2 = decryptByPublicKey(str.getBytes("utf-8"),rsaStr).toString();
        System.out.println(req2);*/

    }
}
