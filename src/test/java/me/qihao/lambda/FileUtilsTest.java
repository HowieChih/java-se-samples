package me.qihao.lambda;

import org.junit.Test;

import java.io.File;

public class FileUtilsTest {

    @Test
    public void testListDirectoryUnder() {
        String pathName = "/Users/chih/Downloads";
        FileUtils.listDirectoryUnder(pathName);
    }

    @Test
    public void testListFilesWithSpecifiedSuffixUnder() {
        String pathName = "/Users/chih/Downloads";
        String suffix = ".dmg";
        FileUtils.listFilesWithSpecifiedSuffixUnder(pathName, suffix);
    }

    @Test
    public void testSortFiles() {
        File[] files = new File("/Users/chih/Downloads").listFiles();
        FileUtils.sortFiles(files);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
