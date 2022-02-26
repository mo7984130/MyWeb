package util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.SqlUtil.getConnection;

/**
 * @author mo7984130
 * @Classname DAOUtil
 * @Description TODO
 * @Date 2022/1/1 11:03 下午
 */
public class DAOUtil<T> {

    Class<?> cl;
    Field[] f;
    ArrayList<Object> fields = new ArrayList<>();
    String database;


    /**
     *  初始化
     * @param t 泛型实例化类
     */
    public DAOUtil(T t) {
        cl = t.getClass();
        f = cl.getDeclaredFields();
        for (Field fi : f){
            try {
                fi.setAccessible(true);
                fields.add(fi.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        database = (String) fields.get(0);
    }

    /**
     * 将类添加进数据库中
     * @param t 类
     */
    public void add(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(database).append(" values(null,");
        try {
            for (int i = 2 ; i<fields.size() ; i++){
                f[i].setAccessible(true);
                if(i != fields.size()-1){
                    sb.append("'").append(f[i].get(t)).append("',");
                }else{
                    sb.append("'").append(f[i].get(t)).append("')");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try(
                Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sb.toString())
        ) {

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库总数
     * @return
     */
    public int getTotal(){
        int total = 0;
        String sql = "select count(*) from " + database;
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public ArrayList<T> list() {
        return list(0,Short.MAX_VALUE);
    }

    public ArrayList<T> list(int start, int end){
        ArrayList<T> list = new ArrayList<>();
        String sql = "select * from " + database + " limit ?,?";
        try(
                Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1,start);
            ps.setInt(2,end);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                T t = (T) cl.newInstance();
                for (int i = 1 ;i< f.length ; i++){
                    f[i].set(t,rs.getObject(i));
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<T> get(String field , String value){
        return get(new String[]{field},new String[]{value});
    }

    public List<T> get(String[] fields , String[] values){

        List<T> list = new ArrayList<T>();

        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(database + " where ");
        for (int i = 0 ; i < fields.length ; i++) {
            if (i != fields.length - 1) {
                sql.append(fields[i]).append(" = '").append(values[i]).append("' and ");
            }else {
                sql.append(fields[i]).append(" = '").append(values[i]).append("'");
            }
        }
        try(
                Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql.toString())
        ) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                T t = (T) cl.newInstance();
                for (int i = 1 ;i< f.length ; i++){
                    f[i].set(t,rs.getObject(i));
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }



    public boolean exists(String field , String value){
        return exists(new String[]{field},new String[]{value});
    }

    public boolean exists(String[] fields , String[] values) {

        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(database).append(" where ");
        for (int i = 0 ; i < fields.length ; i++) {
            if (i != fields.length - 1) {
                sql.append(fields[i]).append(" = '").append(values[i]).append("' and ");
            }else {
                sql.append(fields[i]).append(" = '").append(values[i]).append("'");
            }
        }
        try(
                Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql.toString())
        ) {

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
