package Servlet.photoclass;

import org.apache.commons.fileupload.FileItem;
import util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mo7984130
 * @Classname UploadPhoto
 * @Description TODO
 * @Date 2022/1/30 6:05 下午
 */
public class UploadPhoto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUploadSuccess = false;

        for (FileItem item : UploadUtil.getItemList(request)) {
            //如果有字段，则输出到控制台
            if (item.isFormField()) {
                System.out.print(item.getFieldName() + ":" + item.getString());
            }
            //如果是文件，则保存
            else if (!item.isFormField()) {
                isUploadSuccess = UploadUtil.uploadPhoto(request , item);
            }
        }
        if (isUploadSuccess){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
}
