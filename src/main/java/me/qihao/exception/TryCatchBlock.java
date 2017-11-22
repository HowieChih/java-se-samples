package me.qihao.exception;

import java.util.ArrayList;
import java.util.List;

public class TryCatchBlock {

    public static void main(String[] args) {
        // System.out.println(getNum());
        // System.out.println(getNumWithFinally());
        // System.out.println(getList());
        afterCatch();
    }

    public static void afterCatch() {
        try {
            int x = Integer.parseInt("xx");
        } catch (Exception e) {
            // swallow e
        }
        System.out.println("after catch");
    }

    public static int getNum() {
        int x = 2;
        try {
            return x;
        } finally {
            x = 100;
        }
    }

    public static int getNumWithFinally() {
        int x = 2;
        try {
            return x;
        } finally {
            x = 100;
            return x;
        }
    }

    public static List getList() {
        List<String> list = new ArrayList<>();
        list.add("entry_1");
        try {
            return list;
        } finally {
            list.add("entry_2");
            list = null;
        }
    }

    /*
        list --->   [1]    <---- return
        list --->   [1, 2] <---- return
        list ---> null [1, 2] <---- return
     */
}
