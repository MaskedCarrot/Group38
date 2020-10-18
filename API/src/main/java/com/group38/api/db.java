package com.group38.api;


import java.sql.*;

public class db {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://remotemysql.com:3306/jHTIizRmgW";

    //  Database credentials
    static final String USER = "jHTIizRmgW";
    static final String PASS = "VhsB4vR2fM";



    public String checkUserLogin(int userId , String password) {
        Connection conn = null;
        Statement stmt = null;
        try{

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL , USER ,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT COUNT(userId) as \"check\" FROM User where userId = "+userId + " and  password = "+password;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            if(rs.getInt("check") == 1)
                return "success";

            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException ignored){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return "failed";
    }


    public String checkAdminLogin(int userId , String password) {
        Connection conn = null;
        Statement stmt = null;
        try{

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL , USER ,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT COUNT(userId) as \"check\" FROM Admin where userId = "+userId + " and  password = "+password;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            if(rs.getInt("check") == 1)
                return "success";

            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception se){
            se.printStackTrace();
        }
        finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException ignored){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return "failed";
    }



}