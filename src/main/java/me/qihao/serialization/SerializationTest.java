package me.qihao.serialization;

import java.io.*;

/**
 * serialize me.qihao.serialization.Student object
 * <p>
 * Created by chih on 2017/7/12.
 */
public class SerializationTest {

    public static void main(String[] args) {
        StudentSerializable student = new StudentSerializable();
        student.setId(100);
        student.setName("Selina");

        try (FileOutputStream fos = new FileOutputStream("student.ser");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("student.ser");
             ObjectInputStream in = new ObjectInputStream(fis)){
            StudentSerializable stuFromSeria = (StudentSerializable) in.readObject();
            System.out.println(stuFromSeria);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
