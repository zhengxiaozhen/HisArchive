package com.ttwb.historyArchive.web.controller;

/**
 * @Classname UploadResource
 * @Description
 * @Date 2019/2/13 8:59
 * @Created by zhoulq
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.ttwb.historyArchive.pdf.jpgpdf.PDFCreatorJPG;
import com.ttwb.historyArchive.pdf.pdfmerge.PDFMerge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/uploadController")
public class UploadResource
{


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws ServletException
    {
        binder.registerCustomEditor(CommonsMultipartFile.class,
                new ByteArrayMultipartFileEditor());
    }


    @Value("${upload.url}")
    private String url;

    @RequestMapping("upload")
    @ResponseBody
    public String upload(String myUploadFile, HttpServletRequest request)
    {

        //多部件请求对象
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        //获取文件list集合
        List<MultipartFile> files = mh.getFiles(myUploadFile);
        //创建jersey服务器，进行跨服务器上传
        Client client = Client.create();
        //json格式的图片路径
        List<Object> listJsonPath = new ArrayList<Object>();
        for (MultipartFile file : files)
        {
            String newFileName = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            newFileName = sdf.format(new Date());
            Random r = new Random();
            //{'':''}
            String jsonPath = "";
            for (int i = 0; i < 3; i++)
            {
                newFileName = newFileName + r.nextInt(10);
            }
            //原始的文件名
            String originalFilename = file.getOriginalFilename();
            //截取文件扩展名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //绝对路径（另一台服务器文件路径）
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
            String uploaddate = sdf2.format(new Date());
            /*Properties prop = new Properties();
            try
            {
                String filePath = request.getServletContext().getRealPath("/upload.properties");
                // 通过输入缓冲流进行读取配置文件
                InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
                // 加载输入流
                prop.load(InputStream);
                //printAllProperty(prop);
                String ss = prop.getProperty("url");
                System.out.println("tt=" + ss);
            } catch (Exception e)
            {
                e.printStackTrace();
            }*/
            //String fullPath = url + File.separator + "upload" + File.separator + uploaddate + File.separator + newFileName + suffix;
            String fullPath = url +  "/upload/"  + uploaddate + "/" + newFileName + suffix;
            //相对路径（数据库中存放的文件名）
            String relativePath = newFileName + suffix;
            //各自的流
            InputStream inputStream = null;
            try
            {
                inputStream = file.getInputStream();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
            //将文件传入文件服务器
            WebResource resource = client.resource(fullPath);
            resource.put(String.class, inputStream);
            jsonPath = "{\"fullPath\":\"" + fullPath + "\",\"relativePath\":\"" + relativePath + "\"}";
            JSONObject jsonObject = JSONObject.parseObject(jsonPath);
            listJsonPath.add(jsonObject);
        }
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(listJsonPath));
        String ss = jsonArray.toString();
        return ss;
    }

    public static void main(String[] args)
    {
        File fileRoot2 = new File("");
        System.out.println("绝对路径：" + fileRoot2.getAbsolutePath());
        String mm=fileRoot2.getAbsolutePath()+"/pdf/src/ptimg.jpg";
        String tt="paaa.pdf";
        String[] file={mm};
        String[] pdf={tt};

        PDFCreatorJPG pj=new PDFCreatorJPG();
        PDFMerge merge = new PDFMerge();
        String pdf_file=fileRoot2.getAbsolutePath()+"/pdf/src/ptimg.pdf,"+fileRoot2.getAbsolutePath()+"/pdf/src/ptimg2.pdf";
        String jpgpdfname=fileRoot2.getAbsolutePath()+"/pdf/src/pddddd.pdf";
        try
        {
            pj.jpgToPdf(file, pdf, fileRoot2.getAbsolutePath()+"/pdf/src/");



            merge.mergePdfFiles(pdf_file.split(","), jpgpdfname);
        } catch (Exception e)
        {
            e.printStackTrace();
        }



    }


}