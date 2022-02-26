package dao;

import entity.MD5;
import util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static util.SqlUtil.getConnection;

/**
 * @author mo7984130
 * @Classname MD5DAO
 * @Description TODO
 * @Date 2021/11/28 3:14 下午
 */
public class MD5DAO{

    public static boolean exists(String md5) {
        //language=MySQL
        String sql = "select * from MD5OfFiles where MD5 = ?";
        try(
                Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1,md5);

            int count = 0;
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                count++;
            }

            return count != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
