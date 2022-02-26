package test;

import entity.User;
import entity.Xiangji;
import util.DAOUtil;
import util.SqlUtil;
import util.UploadUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import static java.lang.String.format;
import static java.lang.System.out;

/**
 * @author mo7984130
 * @Classname test
 * @Description TODO
 * @Date 2022/1/26 5:37 下午
 */
public class test {

    public static void main(String[] args) {

        String formatString = "<div class=\"col-xs-3 \"><a href=\"../../%s\"><img class=\"img-thumbnail\" src=\"../../%s\"></a></div>\n";
        int pageIndex = Integer.parseInt(String.valueOf(1));
        Xiangji xiangji = new Xiangji();
        DAOUtil<Xiangji> daoUtil = new DAOUtil<>(xiangji);
        ArrayList<Xiangji> list = daoUtil.list((pageIndex -1) * 100 , pageIndex * 100);
        int count = 0;
        for (int i = 0 ; i < list.size() ; i++) {

            Xiangji j = list.get(i);

            if (count == 0) {
                out.println("<div class=\"row\">");
            }

            out.println(format(formatString, j.getWebPath(), j.getWebPath()));
            count++;

            if (count == 4 || i == list.size()-1) {
                out.println("</div>");
                count = 0;
            }

        }

    }
}

