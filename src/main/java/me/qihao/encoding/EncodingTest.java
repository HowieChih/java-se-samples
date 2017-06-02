package me.qihao.encoding;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Properties;

public class EncodingTest {

    public static void main(String[] args){
        String str = "文";
        byte[] defaultEncodingBytes = str.getBytes();
        byte[] specifiedEncodingBytes = str.getBytes(Charset.forName("utf-16"));

        printBytes(defaultEncodingBytes, System.out);
        System.out.println();
        printBytes(specifiedEncodingBytes, System.out);
        /*Properties properties = System.getProperties();
        properties.list(System.out);*/

        String y = new StringBuilder().appendCodePoint(0x10ffff).toString();
        System.out.println(y);
    }

    static void printBytes(byte[] bytes, PrintStream printStream) {
        for (byte b : bytes) {
            printStream.print(b + " ");
        }
    }
}
