package com.example.craw.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class EncodeUtil {

    public static void main(String[] args) {
        String secret = "quote_web";

        Map<String, String> abcd = new LinkedHashMap<>();
        abcd.put("marketType", "2");

        System.out.println(getQuoteToken(abcd));
    }

    public static String getQuoteToken(Map<String, String> linkedHashMap) {
        // 参数map转json
        String jsonStr = JSON.toJSONString(linkedHashMap);
        // HmacSHA512 加密
        String encodeByHmacSHA512 = encodeByHmacSHA512(jsonStr, "quote_web");
        String encodeBySHA256 = encodeBySHA256(encodeByHmacSHA512.substring(0, 10));
        return encodeBySHA256.substring(0, 10);
    }



    // SHA256 加密
    private static String encodeBySHA256(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            // 转成十六进制表示形式
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA256 发生异常", e);
            return null;
        }
    }

    // HmacSHA512 加密
    private static String encodeByHmacSHA512(String message, String secret) {
        try {
            Mac sha512HMAC = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            sha512HMAC.init(secretKey);
            byte[] hashed = sha512HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            // 转成十六进制表示形式
            return bytesToHex(hashed);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("HmacSHA512 发生异常", e);
            return null;
        }
    }

    // 转成16进制
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        // 返回HMAC-SHA512的十六进制表示形式
        return hexString.toString();
    }
}
