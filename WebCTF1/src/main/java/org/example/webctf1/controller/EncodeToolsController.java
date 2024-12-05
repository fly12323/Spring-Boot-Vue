package org.example.webctf1.controller;

import org.example.webctf1.dao.Result;
import org.example.webctf1.utils.BaseUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tools", method = RequestMethod.POST)
public class EncodeToolsController {

    // Base64 编码接口，调用 BaseUtil.base64Encode 方法
    @GetMapping("/base64Encode")
    public Result<String> base64Encode(@RequestParam String data) {
        System.out.println(data);
        try {
            String encodedString = BaseUtil.base64Encode(data);
            return Result.success(encodedString);
        } catch (Exception e) {
            return Result.error("Error encoding string to Base64: " + e.getMessage());
        }
    }

    // Base64 解码接口，调用 BaseUtil.base64Decode 方法
    @GetMapping("/base64Decode")
    public Result<String> base64Decode(@RequestParam String data) {
        System.out.println(data);
        try {
            String decodedString = BaseUtil.base64Decode(data);
            return Result.success(decodedString);
        } catch (Exception e) {
            return Result.error("Error decoding Base64 string: " + e.getMessage());
        }
    }

    // Base32 编码接口，调用 BaseUtil.base32Encode 方法
    @PostMapping("/base32Encode")
    public Result<String> base32Encode(@RequestParam String data) {
        try {
            String encodedString = BaseUtil.base32Encode(data);
            return Result.success(encodedString);
        } catch (Exception e) {
            return Result.error("Error encoding string to Base32: " + e.getMessage());
        }
    }

    // Base32 解码接口，调用 BaseUtil.base32Decode 方法
    @PostMapping("/base32Decode")
    public Result<String> base32Decode(@RequestParam String data) {
        try {
            String decodedString = BaseUtil.base32Decode(data);
            return Result.success(decodedString);
        } catch (Exception e) {
            return Result.error("Error decoding Base32 string: " + e.getMessage());
        }
    }

    // Base16 (Hex) 编码接口，调用 BaseUtil.base16Encode 方法
    @PostMapping("/base16Encode")
    public Result<String> base16Encode(@RequestParam String data) {
        try {
            String encodedString = BaseUtil.base16Encode(data);
            return Result.success(encodedString);
        } catch (Exception e) {
            return Result.error("Error encoding string to Base16: " + e.getMessage());
        }
    }

    // Base16 (Hex) 解码接口，调用 BaseUtil.base16Decode 方法
    @PostMapping("/base16Decode")
    public Result<String> base16Decode(@RequestParam String data) {
        try {
            String decodedString = BaseUtil.base16Decode(data);
            return Result.success(decodedString);
        } catch (Exception e) {
            return Result.error("Error decoding Base16 string: " + e.getMessage());
        }
    }

    // Base58 编码接口，调用 BaseUtil.base58Encode 方法
    @PostMapping("/base58Encode")
    public Result<String> base58Encode(@RequestParam String data) {
        try {
            String encodedString = BaseUtil.base58Encode(data);
            return Result.success(encodedString);
        } catch (Exception e) {
            return Result.error("Error encoding string to Base58: " + e.getMessage());
        }
    }

    // Base58 解码接口，调用 BaseUtil.base58Decode 方法
    @PostMapping("/base58Decode")
    public Result<String> base58Decode(@RequestParam String data) {
        try {
            String decodedString = BaseUtil.base58Decode(data);
            return Result.success(decodedString);
        } catch (Exception e) {
            return Result.error("Error decoding Base58 string: " + e.getMessage());
        }
    }
}
