package dao;

import entity.Image;
import util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static util.SqlUtil.getConnection;

/**
 * @author mo7984130
 * @Classname ImageDAO
 * @Description TODO
 * @Date 2022/1/27 3:37 下午
 */
public class ImageDAO {

    DAOUtil<Image> daoUtil;

    public ImageDAO(){
        daoUtil = new DAOUtil<>(new Image());
    }

    public static boolean exists(String md5) {
        //language=MySQL
        String sql = "select * from image where md5 = ?";
        try(
                Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)
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
