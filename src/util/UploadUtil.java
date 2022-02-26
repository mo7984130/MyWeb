package util;

import dao.ImageDAO;
import dao.MD5DAO;
import entity.Image;
import entity.MD5;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mo7984130
 * @Classname UploadUtil
 * @Description TODO
 * @Date 2022/1/22 7:28 下午
 */
public class UploadUtil {

    public static int getUserIdByCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if ("id".equals(cookie.getName())){
                return Integer.parseInt(cookie.getValue());
            }
        }
        return 0;
    }

    public static String getShortTime(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getLongTime(){
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }

    public static String realPathToWebPath(String context,String realPath) {
        context = context.replaceAll("/","");

        String spilt = File.separator;
        if (Objects.equals(spilt, "\\")){
            spilt = "\\\\";
        }
        String[] realPaths = realPath.split(spilt);
        StringBuilder webPath = new StringBuilder();

        ArrayList<Integer> times = new ArrayList<>();

        for (int i = 0 ; i<realPaths.length ; i++){
            if (realPaths[i].equals(context)){
                times.add(i);
            }
        }

        for (int i = times.get(times.size()-1) + 1 ; i<realPaths.length ; i++){
            if (i == realPaths.length-1){
                webPath.append(realPaths[i]);
            }else{
                webPath.append(realPaths[i]).append("/");
            }
        }

        return webPath.toString();

    }

    public static List<FileItem> getItemList(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //缓存区1MB
        factory.setSizeThreshold(1024 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static boolean uploadFile(HttpServletRequest request, FileItem item){

        //运行对应的sql文件
        SqlUtil.runSqlFile(new File(request.getSession().getServletContext().getRealPath("sql") + "/md5.sql"));

        String fileName = System.currentTimeMillis() + "_" + item.getName();

        //根据项目路径来保存
        String path = request.getSession().getServletContext().getRealPath("upload/") + getShortTime();
        //创建文件夹
        File fileFolder = new File(path);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        //创建新文件
        File file = new File(path, fileName);
        //复制
        try {
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MD5 md5 = new MD5();

        //通过md5判断是不是同一个文件
        md5.setMd5(MD5Util.fileToMd5(file));

        //文件已存在
        if (MD5DAO.exists(md5.getMd5())) {
            file.delete();
            return false;
        }
        //文件不存在
        else {
            md5.setFileName(item.getName());
            md5.setRealPath(path + File.separator + fileName);
            md5.setWebPath(realPathToWebPath(request.getContextPath(),md5.getRealPath()));
            md5.setUploadUserId(UploadUtil.getUserIdByCookies(request));
            md5.setUploadTime(getLongTime());
            new DAOUtil<>(md5).add(md5);
            return true;
        }

    }

    public static boolean uploadPhoto(HttpServletRequest request, FileItem item){

        //运行对应的sql文件
        SqlUtil.runSqlFile(new File(request.getSession().getServletContext().getRealPath("sql") + "/image.sql"));

        String fileName = System.currentTimeMillis() + "_" + item.getName();

        //根据项目路径来保存
        String path = request.getSession().getServletContext().getRealPath("image/") + getShortTime();
        //创建文件夹
        File fileFolder = new File(path);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        //创建新文件
        File file = new File(path, fileName);
        //复制
        try {
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = new Image();

        //通过md5判断是不是同一个文件
        image.setMd5(MD5Util.fileToMd5(file));

        MD5DAO md5dao = new MD5DAO();
        //文件已存在
        if (ImageDAO.exists(image.getMd5())) {
            file.delete();
            return false;
        }
        //文件不存在
        else {
            image.setFileName(item.getName());
            image.setRealPath(path + File.separator + fileName);
            image.setWebPath(realPathToWebPath(request.getContextPath(),image.getRealPath()));
            image.setUserId(getUserIdByCookies(request));
            image.setUploadTime(getLongTime());
            new DAOUtil<>(image).add(image);
            return true;
        }

    }

}
