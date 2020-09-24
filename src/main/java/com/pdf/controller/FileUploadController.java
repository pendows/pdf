package com.pdf.controller;

import com.pdf.pojo.FileContent;
import com.pdf.service.PdfSeervice;
import com.pdf.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.Map;

@Controller
public class FileUploadController {
    @Value("${youku1327.file.root.path}")
    private String fileRootPath;
    @Autowired
    private PdfSeervice pdfSeervice;

    @RequestMapping("/file/upload")
    public String fileUpload(@RequestParam("files") MultipartFile[] files){
        String filePath = "";
        // 多文件上传
        for (MultipartFile file : files){
            try {
                // 上传简单文件名
                String originalFilename = file.getOriginalFilename();
                // 存储路径
                filePath = new StringBuilder(fileRootPath).append(this.getFileName(originalFilename)).toString();
                file.transferTo(new File(filePath));
                Map<String, String> result = PDFUtil.readPdf(filePath);
                FileContent fileContent = new FileContent();
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                fileContent.setId(idWorker.nextId());
                fileContent.setFileDate(result.get("fileDate"));
                fileContent.setFileName(originalFilename);
                fileContent.setPath(filePath);
                fileContent.setFileNo(result.get("fileNo"));
                fileContent.setFileAmout(result.get("fileAmout"));
                fileContent.setFileCapitalize(result.get("fileCapitalize"));
                pdfSeervice.saveFileInfo(fileContent);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                WaterMarkUtils.setWatermark(bos,filePath,fileContent.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    private String getFileName(String originalFilename){
        String date = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YY_MM_DD);
        return originalFilename.replace(".pdf","")+date +".pdf";
    }
}
