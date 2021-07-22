package com.lee.servlet;

import com.sun.scenario.effect.impl.sw.sse.SSERendererDelegate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "FileServlet", value = "/FileServlet")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断上传form为普通表单或文件上传表单
        if (!ServletFileUpload.isMultipartContent(request)) {
            //普通表单，终止方法运行,栈里的东西都弹出去
            return;
        }

        //建议保存在WEB——INF下，用户无法之间访问
        String uploadPath = this.getServletContext().getRealPath("WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            //创建空目录
            uploadFile.mkdir();
        }

        //缓存
        //超过一定时间删除临时文件，或提醒用户永久存储
        String tmpPath = this.getServletContext().getRealPath("WEB-INF/tmp");
        File tmpFile = new File(tmpPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }

        //处理上传文件，一般通过IO流，request.getInputStream()
        //建议Apache文件上传组件，common-fileupload,依赖于common-io组件

        //1创建一个DiskFileItemFactory对象，处理文件路径、限制文件大小
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(tmpFile);
        //2获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);

        //3处理上传文件 大致思路
        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //判断表达中每一项是否是上传文件
        //如果是上传文件
        for (FileItem fileItem : fileItems) {
            String uploadFileName = fileItem.getName();
            String fileName = uploadFileName.substring(uploadFileName.lastIndexOf('\\' ) + 1);
            String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf('.' + 1));

            //UUID唯一识别通用符
            //UUID.randomUUID();

        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
