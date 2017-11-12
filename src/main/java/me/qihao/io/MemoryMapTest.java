package me.qihao.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest {

    public static long checkSumInputStream(Path fileName) throws IOException {
        try (InputStream in = Files.newInputStream(fileName)) {
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checkSumBufferedInputStream(Path fileName) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(fileName))) {
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checkSumRandomAccessFile(Path fileName) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileName.toFile(), "r")) {
            long length = file.length();
            CRC32 crc = new CRC32();
            for (long p = 0; p < length; p++) {
                file.seek(p);
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checkSumMappedFile(Path fileName) throws IOException {
        try (FileChannel channel = FileChannel.open(fileName)){
            CRC32 crc = new CRC32();
            long length = channel.size();
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            for (int p  = 0; p < length; p++){
                int c = buffer.get(p);
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static void main(String[] args){
        Path fileName = Paths.get("D:\\Program Files\\Java\\jdk1.7.0_75\\jre\\lib\\rt.jar");
        try {
            System.out.println("Input Stream");
            long start = System.currentTimeMillis();
            long crcValue = checkSumInputStream(fileName);
            System.out.println(Long.toHexString(crcValue));
            System.out.println(System.currentTimeMillis() - start);

            System.out.println("Buffered Input Stream");
            start = System.currentTimeMillis();
            crcValue = checkSumBufferedInputStream(fileName);
            System.out.println(Long.toHexString(crcValue));
            System.out.println(System.currentTimeMillis() - start);

            System.out.println("Randome Access File");
            start = System.currentTimeMillis();
            crcValue = checkSumRandomAccessFile(fileName);
            System.out.println(Long.toHexString(crcValue));
            System.out.println(System.currentTimeMillis() - start);

            System.out.println("Mapped File");
            start = System.currentTimeMillis();
            crcValue = checkSumMappedFile(fileName);
            System.out.println(Long.toHexString(crcValue));
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
