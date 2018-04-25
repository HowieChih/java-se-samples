package me.qihao.lambda;

import java.io.File;
import java.util.Arrays;

public class FileUtils {

    public static void listDirectoryUnder(String pathName) {
        File file = new File(pathName);
        File[] list = file.listFiles(File::isDirectory);
        if (list == null)
            return;
        for (File directory : list) {
            System.out.println(directory);
        }
    }

    public static void listFilesWithSpecifiedSuffixUnder(String pathName, String suffix) {
        File file = new File(pathName);
        String[] nameList = file.list((dir, name) -> name.endsWith(suffix));
        if (nameList == null)
            return;
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    public static void sortFiles(File[] files) {
        Arrays.sort(files, (first, second) -> {
            if (first.isDirectory() && second.isDirectory() || first.isFile() && second.isFile()) {
                return first.compareTo(second);
            } else {
                if (first.isDirectory())
                    // first < second, return negative integer, 小的排前面
                    return -1;
                else
                    return 1;
            }
        });

    }
}
