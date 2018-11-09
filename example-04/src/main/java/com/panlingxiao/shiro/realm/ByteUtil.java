package com.panlingxiao.shiro.realm;

import java.util.Arrays;

public class ByteUtil {
    private static final char[] HEX_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f'
    };

    public static String byte2Hex(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            int j = i * 2;
            // 对高4位进行处理
            chars[j] = HEX_CHARS[(data[i] >> 4) & 0x0f];
            // 对低4位进行处理
            chars[j + 1] = HEX_CHARS[data[i] & 0x0f];
        }

        return new String(chars);
    }
}
