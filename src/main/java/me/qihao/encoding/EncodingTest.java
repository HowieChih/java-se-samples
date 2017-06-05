package me.qihao.encoding;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Properties;

public class EncodingTest {

    public static void main(String[] args) {
        String str = "文";
        char[] charArrays = str.toCharArray();
        System.out.println((int) charArrays[0]);
        System.out.println((byte) charArrays[0]);

        byte[] defaultEncodingBytes = str.getBytes(); // default: file.encoding UTF-8
        byte[] specifiedEncodingBytes = str.getBytes(Charset.forName("UTF-16"));

        printBytes(defaultEncodingBytes, System.out);
        System.out.println();
        printBytes(specifiedEncodingBytes, System.out);
        /*Properties properties = System.getProperties();
        properties.list(System.out);*/
    }

    static void printBytes(byte[] bytes, PrintStream printStream) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        printStream.print(sb.toString());
    }
}
