package test;

import entity.User;
import util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static util.SqlUtil.javaGetConnection;

/**
 * @author mo7984130
 * @Classname AddUser
 * @Description TODO
 * @Date 2022/1/30 7:50 下午
 */
public class AddUser {

    static String[] students = {
            "刘佳",
            "张佳文",
            "刘心怡",
            "柯佳",
            "纪德鼎",
            "刘添",
            "徐华卿",
            "纪润",
            "梁师棋",
            "余云枫",
            "涂亮",
            "吴诗韵",
            "姜梓杰",
            "刘翔飞",
            "石茜",
            "熊世杰",
            "张恩心",
            "吕浪",
            "胡源",
            "柯君铭",
            "陈栗",
            "陈一凡",
            "胡雄",
            "熊文浩",
            "袁朝阳",
            "侯依凡",
            "柯诗语",
            "曹然",
            "陈泽",
            "刘浩强",
            "华志锋",
            "祝家仪",
            "郑磊",
            "邱梽乘",
            "许嘉祚",
            "方浩宇",
            "胡鑫",
            "姜润欣",
            "陈全欣",
            "黄磊",
            "刘沅霖",
            "陈浩淼",
            "胡浩",
            "曹睿",
            "张杨",
            "叶鑫",
            "吴子聪",
            "秦锦",
            "郑再道",
            "陈子熙"
    };

    public static void main(String[] args) {

        for (String student : students) {

            //language=MySQL
            String sql = "insert into user values(null,?,?)";
            try(
                    Connection c = javaGetConnection(); PreparedStatement ps = c.prepareStatement(sql)
            ) {

                ps.setString(1,student);
                ps.setString(2,"123456");

                ps.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
