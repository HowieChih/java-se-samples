package me.qihao.jdbc;

import java.sql.*;

public class StreamReadExample {
    // mysql jdbc url configuration properties
    // https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-configuration-properties.html

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&zeroDateTimeBehavior=round&autoReconnect=true&serverTimezone=PRC",
                    "root",
                    "123456");
            PreparedStatement ps = con.prepareStatement("select * from test", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ps.setFetchSize(Integer.MIN_VALUE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getObject(1) + "|");
                System.out.print(rs.getObject(2) + "|");
                System.out.print(rs.getObject(3) + "\r\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}