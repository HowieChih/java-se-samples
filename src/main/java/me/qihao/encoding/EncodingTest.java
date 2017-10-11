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

        // 前两个代码单元为字符 U+1D56B，由两个代码单元表示，最后两个代码单元为emoji 😁
        String beyondCharLimitStr = "\uD835\uDD6B\uD835\uDD6C\uD83D\uDE01";
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

        // 观察 中 字，从 utf-8编码到 iso-8859-1 解码，再利用iso-8859-1编码到utf-8解码的过程
        String encodeWithISO = new String("中".getBytes(Charset.forName("utf-8")), Charset.forName("iso-8859-1"));
        System.out.println(encodeWithISO);
        String decodeWithISO = new String(encodeWithISO.getBytes(Charset.forName("iso-8859-1")), Charset.forName("utf-8"));
        System.out.println(decodeWithISO);
        // 要将下面直接利用iso解码utf-16内部存储的情况和上面利用iso作为中间编码，先解码，再编码的情况做区分。
        // 下面这种情况会导致中这个字符的3个存储字节转为一个iso字符，而不是三个，由于iso编码表中找不到3个字节对应数值的解码值，所以会得到一个question mark.
        byte[] t = "中".getBytes(Charset.forName("ISO-8859-1"));
        String recv = new String(t, Charset.forName("ISO-8859-1"));
        System.out.println(recv);
    }

    static void printBytesToHex(byte[] bytes, PrintStream printStream) {
        printStream.println(bytes.length);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b)); // %X整数十六进制，02，最多两位，高位补0。
        }
        printStream.println(sb.toString());
    }
}
