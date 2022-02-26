package util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * @author mo7984130
 * @Classname MD5Util
 * @Description TODO
 * @Date 2022/1/27 2:09 下午
 */
public class MD5Util {

    public static String fileToMd5(File file){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while((length = fis.read(b)) != -1){
                md5.update(b,0,length);
            }
            fis.close();
            byte[] byteArray = md5.digest();
            for (byte by : byteArray) {
                sb.append(String.format("%02x", by));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
