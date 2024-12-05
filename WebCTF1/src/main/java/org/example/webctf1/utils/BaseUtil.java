package org.example.webctf1.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

public class BaseUtil {

    // Base64 编码
    public static String base64Encode(String data) {
        return java.util.Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    // Base64 解码
    public static String base64Decode(String data) throws Exception {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(data);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    // Base32 编码
    public static String base32Encode(String data) {
        Base32 base32 = new Base32();
        return base32.encodeAsString(data.getBytes(StandardCharsets.UTF_8));
    }

    // Base32 解码
    public static String base32Decode(String data) throws Exception {
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(data);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    // Base58 编码
    public static String base58Encode(String data) {
        byte[] input = data.getBytes(StandardCharsets.UTF_8);
        long value = 0;
        for (byte b : input) {
            value = (value << 8) | (b & 0xFF);
        }

        char[] alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            sb.append(alphabet[(int) (value % 58)]);
            value /= 58;
        }

        for (byte b : input) {
            if (b == 0) {
                sb.append(alphabet[0]);
            } else {
                break;
            }
        }

        return sb.reverse().toString();
    }

    // Base58 解码
    public static String base58Decode(String data) throws Exception {
        char[] alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
        long value = 0;

        for (char c : data.toCharArray()) {
            int digit = Arrays.binarySearch(alphabet, c);
            if (digit < 0) throw new IllegalArgumentException("Invalid character in Base58 string");

            value = (value * 58) + digit;
        }

        byte[] bytes = new byte[(data.length() + 7) / 8];
        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) ((int) value & 0xFF);
            value >>= 8;
        }

        // Remove leading zeros
        int firstNonZero = 0;
        while (firstNonZero < bytes.length && bytes[firstNonZero] == 0) {
            firstNonZero++;
        }

        byte[] result = Arrays.copyOfRange(bytes, firstNonZero, bytes.length);

        return new String(result, StandardCharsets.UTF_8);
    }

    // Base16 (Hex) 编码
    public static String base16Encode(String data) {
        return Hex.encodeHexString(data.getBytes(StandardCharsets.UTF_8));
    }

    // Base16 (Hex) 解码
    public static String base16Decode(String data) throws Exception {
        byte[] decodedBytes = Hex.decodeHex(data);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
