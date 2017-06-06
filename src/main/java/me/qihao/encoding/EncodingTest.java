package me.qihao.encoding;

import java.io.PrintStream;
import java.nio.charset.Charset;

public class EncodingTest {

    public static void main(String[] args) {
        String str = "文";
        char[] charArrays = str.toCharArray();
        System.out.println((int) charArrays[0]);

        byte[] defaultEncodingBytes = str.getBytes(); // with default charset - file.encoding
        byte[] specifiedEncodingBytes = str.getBytes(Charset.forName("UTF-16"));
        printBytesToHex(defaultEncodingBytes, System.out);
        printBytesToHex(specifiedEncodingBytes, System.out);

        String decodedStr = new String(defaultEncodingBytes, Charset.forName("UTF-8"));
        System.out.println((int) decodedStr.toCharArray()[0]);

        // 字符 U+1D56B，由两个代码单元表示
        String beyondCharLimitStr = "\uD835\uDD6B";
        System.out.println(beyondCharLimitStr);
        // code unit length 代码单元长度（该字符由两个代码单元编码）
        System.out.println(beyondCharLimitStr.length());
        // code point count 代码点数量（一个字符一个代码点）
        System.out.println(beyondCharLimitStr.codePointCount(0, beyondCharLimitStr.length()));

        // https://docs.oracle.com/javase/tutorial/i18n/text/examples/StringConverter.java
    }

    static void printBytesToHex(byte[] bytes, PrintStream printStream) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        printStream.println(sb.toString());
    }
}
