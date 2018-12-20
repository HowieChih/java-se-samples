package me.qihao.jdbc;

import java.sql.*;
import java.util.Properties;

public class StreamReadExample {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/mytest";

    public static void testStreamRead() {
        // mysql jdbc url configuration properties
        // https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-configuration-properties.html
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

    public static void testUpdateResult() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("useSSL", "false");
        properties.setProperty("zeroDateTimeBehavior", "round");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone", "PRC");

        try {
            Connection con = DriverManager.getConnection(url, properties);
            PreparedStatement ps = con.prepareStatement("update user set name=? where id=?");
            ps.setString(1, "two");
            ps.setInt(2, 2);

            // way 1:
            // int matchedRows = ps.executeUpdate();
            // System.out.println(matchedRows);

            // way 2:
            if (!ps.execute()) {
                System.out.println(ps.getUpdateCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testBatchInsert() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("useSSL", "false");
        properties.setProperty("zeroDateTimeBehavior", "round");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone", "PRC");
        properties.setProperty("profileSQL", "true");
        properties.setProperty("rewriteBatchedStatements", "true"); // if u want to do batching ops, this property is needed.

        try {
            Connection conn = DriverManager.getConnection(url, properties);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("insert into user(name) values (?)");
            ps.setString(1, "batchInsert1");
            ps.addBatch();
            ps.setString(1, "batchInsert2");
            ps.addBatch();

            ps.executeBatch();
            conn.commit();

            // executed sql: insert into user(name) values ('batchInsert1'),('batchInsert2')
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testBatchInsertOnDuplicateKey() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("useSSL", "false");
        properties.setProperty("zeroDateTimeBehavior", "round");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone", "PRC");
        properties.setProperty("profileSQL", "true");
        properties.setProperty("rewriteBatchedStatements", "true"); // if u want to do batching ops, this property is needed.

        try {
            Connection conn = DriverManager.getConnection(url, properties);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(name, no) VALUES (?, ?)" +
                    "  ON DUPLICATE KEY UPDATE name=?");
            ps.setString(1, "test111");
            ps.setInt(2, 7);
            ps.setString(3, "dddd");
            ps.addBatch();
            ps.setString(1, "test222");
            ps.setInt(2, 7);
            ps.setString(3, "dddd");
            ps.addBatch();
            ps.setString(1, "test222");
            ps.setInt(2, 9);
            ps.setString(3, "dddd");
            ps.addBatch();
            ps.setString(1, "test222");
            ps.setInt(2, 9);
            ps.setString(3, "dddd");
            ps.addBatch();

            ps.executeBatch();
            conn.commit();

            // executed sql: insert into ....;insert into ...;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testBatchUpdate() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("useSSL", "false");
        properties.setProperty("zeroDateTimeBehavior", "round");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone", "PRC");
        properties.setProperty("profileSQL", "true");
        properties.setProperty("rewriteBatchedStatements", "true"); // if u want to do batching ops, this property is needed.

        try {
            Connection conn = DriverManager.getConnection(url, properties);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("update user set name=? where id=?");
            ps.setString(1, "batchUpdate1");
            ps.setInt(2, 1002);
            ps.addBatch();
            ps.setString(1, "batchUpdate2");
            ps.setInt(2, 1003);
            ps.addBatch();
            ps.setString(1, "batchUpdate2");
            ps.setInt(2, 1004);
            ps.addBatch();
            ps.setString(1, "batchUpdate2");
            ps.setInt(2, 1005);
            ps.addBatch();

            ps.executeBatch();
            conn.commit();

            // executed sql:
            // if executed count <=3: 一次发送一条update语句
            // else: 批量发3条
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testBatchUpdateWithStatement() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("useSSL", "false");
        properties.setProperty("zeroDateTimeBehavior", "round");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone", "PRC");
        properties.setProperty("profileSQL", "true");
        properties.setProperty("rewriteBatchedStatements", "true"); // if u want to do batching ops, this property is needed.

        try {
            Connection conn = DriverManager.getConnection(url, properties);
            conn.setAutoCommit(false);

            Statement stmt = conn.createStatement();
            stmt.addBatch("update user set name='batchUpdate4' where id=1002");
            stmt.addBatch("update user set name='batchUpdate3' where id=1003");
            stmt.addBatch("update user set name='batchUpdate2' where id=1004");
            stmt.addBatch("update user set name='batchUpdate1' where id=1005");

            stmt.executeBatch();
            conn.commit();

            // executed sql: 一条条发
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testBatchUpdateWithStatement();
    }
}