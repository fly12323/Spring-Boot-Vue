package org.example.webctf1;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseTest {
    // Base64 编码
    @Test
    public void testBase64Encode() {
        String data = "123";
        String out = Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 Encoded: " + out);
    }

    // Base64 解码
    @Test
    public void testBase64Decode() {
        String data = "MTIz";
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        System.out.println("Base64 Decoded: " + new String(decodedBytes, StandardCharsets.UTF_8));
    }

    // Base32 编码
    @Test
    public void testBase32Encode() {
        String data = "123";
        Base32 base32 = new Base32();
        String encoded = base32.encodeAsString(data.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base32 Encoded: " + encoded);
    }

    // Base32 解码
    @Test
    public void testBase32Decode() {
        String data = "GEZDGNBV"; // 示例 Base32 编码 "123" 的结果
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(data);
        System.out.println("Base32 Decoded: " + new String(decodedBytes, StandardCharsets.UTF_8));
    }

    // Base16 编码
    @Test
    public void testBase16Encode() {
        String data = "123";
        String encoded = Hex.encodeHexString(data.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base16 Encoded: " + encoded.toUpperCase()); // 输出大写
    }

    // Base16 解码
    @Test
    public void testBase16Decode() {
        String data = "313233"; // 示例 Base16 编码 "123" 的结果
        try {
            byte[] decodedBytes = Hex.decodeHex(data);
            System.out.println("Base16 Decoded: " + new String(decodedBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
