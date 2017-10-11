package me.qihao.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("target", "randomAccessFile.dat");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.write(300); // 1 byte
        raf.write(10); // 1 byte
        raf.writeInt(10); // 4 bytes
        raf.writeBoolean(false); // 1 byte. boolean (primitive type is 1 bit), this method write as 1 byte(8 bit).
        String str = "10";
        raf.writeChars(str); // 2 bytes per char
        System.out.println(raf.length());
    }
}
