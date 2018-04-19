package me.qihao.exception;

import org.junit.Test;

import java.io.IOException;

public class ExceptionTest {

    @Test
    public void testCirculationInTry() {
        try {
            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    throw new IOException("test");
                } else {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCirculationOutTry() {
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                try {
                    throw new IOException("test");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(i);
            }
        }
    }
}
