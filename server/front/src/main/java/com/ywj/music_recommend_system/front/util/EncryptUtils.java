package com.ywj.music_recommend_system.front.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
public class EncryptUtils {
    public static final String algorithm = "SHA-256";

    public static final String encrypt(String data) {
        byte[] digest = new byte[0];
        try {
            digest = MessageDigest.getInstance(algorithm).digest(data.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encodeHexString = Hex.encodeHexString(digest);
        return encodeHexString;
    }
}
