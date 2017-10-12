package me.qihao.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("target", "randomAccessFile.dat");
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.write(300); // 1 byte
            raf.write(10); // 1 byte
            raf.writeInt(10); // 4 bytes
            raf.writeBoolean(true); // 1 byte. boolean (primitive type is 1 bit), this method write as 1 byte(8 bit).
            String str = "10";
            raf.writeChars(str); // total 4 bytes, 2 bytes per char
            byte[] bytes = {0B10011, 0B00101};
            raf.write(bytes); // 2 bytes
            String chinese = "中文";
            // 2(these two bytes's value is the number of bytes actually written out. In this example, value is 6, binary is 0B00000000 00000110)
            // + 3 + 3 bytes
            // Java modified UTF-8, see https://stackoverflow.com/questions/7921016/what-does-it-mean-to-say-java-modified-utf-8-encoding
            raf.writeUTF(chinese);
            System.out.println(raf.length()); // which is 21
            raf.setLength(30); // fill up with 9 bytes and each value is 00000000
            System.out.println(raf.length()); // which is 30
            raf.seek(raf.length());
            raf.writeFloat(11.1F); // 4 bytes
            System.out.println(raf.length()); // which is 34

            raf.seek(7);
            System.out.println(raf.getFilePointer());
            System.out.println(raf.readChar());
            System.out.println(raf.readChar());
            raf.seek(6);
            System.out.println(raf.readBoolean());
            raf.seek(13); // index at two bytes before "中文"
            byte[] readableBytes = new byte[2];
            raf.read(readableBytes);
            System.out.println(readableBytes[0] + " " + readableBytes[1]);
            System.out.println(raf.readByte());
            raf.seek(raf.getFilePointer() - 1);
            System.out.println(raf.readUnsignedByte());

            raf.seek(raf.length() - 1);
            byte[] readableBytes2 = new byte[3];
            raf.read(readableBytes2);
            System.out.printf("%d %d %d \n", readableBytes2[0], readableBytes2[1], readableBytes2[2]);
            raf.seek(raf.length() - 1);
            // raf.readFully(new byte[3]); // throw an exception
            // readFully will read exactly b.length bytes, whereas read will read up to b.length,
            // maybe less, whatever is available from the input stream.

            raf.seek(4);
            // overwrite the third byte of int 10
            raf.write(10);
            raf.seek(2);
            System.out.println(raf.readInt()); // which binary is 00000000 00000000 00001010 00001010

            // 从指定位置开始重写，如果到了文件末尾，剩余内容就会被append进去。
            raf.seek(raf.length() -1);
            raf.write(10);
            raf.write(11);
            System.out.println(raf.length()); // which 35
            raf.seek(raf.length() - 5);
            System.out.println(raf.readFloat());
            raf.seek(raf.length() - 1);
            System.out.println(raf.readByte());
        }
    }
}
