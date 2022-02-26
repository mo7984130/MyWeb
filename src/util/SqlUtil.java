package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author mo7984130
 * @Classname Sql
 * @Description TODO
 * @Date 2021/12/20 11:58 下午
 */
public class SqlUtil {


    public static Connection getConnection(){
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JG&SZ");
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection javaGetConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/JG&SZ";
            String user = "root";
            String password = "mo7984130";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void runSql(String sql){
        try(
                Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runSql(String[] sqls){
        for (String sql : sqls){
            runSql(sql);
        }
    }

    public static void runSqlFile(File sqlFile){
        try {
            FileReader fr = new FileReader(sqlFile);
            BufferedReader br = new BufferedReader(fr);

            while(true){
                String line = br.readLine();

                if (line == null){
                    break;
                }

                runSql(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
