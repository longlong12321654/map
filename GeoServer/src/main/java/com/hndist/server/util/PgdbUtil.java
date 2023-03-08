package com.hndist.server.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PgdbUtil {
    public static void main(String[] args) {
       System.out.println(conntest("192.168.1.119","xingxing","postgres","wwwddosi110"));

    }

    public static boolean conntest(String dburl,String dbname,String dbusr,String dbpw){

        Connection c;
        try {
            Statement stmt;
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://"+dburl+":5432/"+dbname,dbusr,dbpw);

            stmt=c.createStatement();

            stmt.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
