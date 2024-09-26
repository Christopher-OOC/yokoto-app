package com.example.demo.utils;

import java.util.Base64;

public class FileEncoderUtil {

    public static String encodedFileToString(byte[] rawContent) {

        return Base64.getEncoder().encodeToString(rawContent);
    }

    public static byte[] decodedStringToFileByte(String encodedString) {
        return Base64.getDecoder().decode(encodedString);
    }

}
