package me.qihao.encoding;

import java.io.PrintStream;
import java.nio.charset.Charset;

public class EncodingTest {

    public static void main(String[] args) {
        String str = "文";
        char[] charArrays = str.toCharArray();
        int x = charArrays[0];
        System.out.println(x);

        byte[] defaultEncodingBytes = str.getBytes(); // with default charset - file.encoding
        byte[] specifiedEncodingBytes = str.getBytes(Charset.forName("UTF-16"));
        printBytesToHex(defaultEncodingBytes, System.out);
        printBytesToHex(specifiedEncodingBytes, System.out);

        String decodedStr = new String(defaultEncodingBytes, Charset.forName("UTF-8"));
        System.out.println((int) decodedStr.toCharArray()[0]);

        // 前两个代码单元为字符 U+1D56B，由两个代码单元表示，最后两个代码单元为emoji
        String beyondCharLimitStr = "\uD835\uDD6B\uD835\uDD6C\uD83D\uDE02";
        System.out.println(beyondCharLimitStr);
        // code unit length 代码单元长度（该字符由两个代码单元编码）
        System.out.println(beyondCharLimitStr.length());
        // code point count 代码点数量（一个字符一个代码点）
        System.out.println(beyondCharLimitStr.codePointCount(0, beyondCharLimitStr.length()));
        // 获得下标位置1的代码单元
        System.out.println(Integer.toHexString(beyondCharLimitStr.charAt(1)));
        // 获得第2个代码点
        int nextCodePointIndex = beyondCharLimitStr.offsetByCodePoints(0, 1); // 从0开始，偏移量为1个代码点长度
        System.out.println(Integer.toHexString(beyondCharLimitStr.codePointAt(nextCodePointIndex)));
    }

    static void printBytesToHex(byte[] bytes, PrintStream printStream) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b)); // %X整数十六进制，02，最多两位，高位补0。
        }
        printStream.println(sb.toString());
    }
}
