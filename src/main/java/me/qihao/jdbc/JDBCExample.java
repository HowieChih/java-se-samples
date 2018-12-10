package me.qihao.jdbc;

import java.sql.*;

public class JDBCExample {

    // mysql jdbc url configuration properties
    // https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-configuration-properties.html

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://10.150.20.91:3306/bank_api?useUnicode=true&useSSL=false&zeroDateTimeBehavior=round&autoReconnect=true&serverTimezone=PRC",
                    "devbj",
                    "gb8tVSJCSw!kUPnE");
            PreparedStatement ps = con.prepareStatement("select * from b_tx_log", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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
