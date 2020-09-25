package com.pdf.controller;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.pojo.FileContent;
import com.pdf.service.PdfSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class pdfController {

    @Autowired
    private PdfSeervice pdfSeervice;

    /**
     * 扫描二维码
     * @param id
     * @param model
     * @param response
     * @return
     */
    @RequestMapping("/pdf/index")
    public String getFileContent(@RequestParam(required = false) String id, Model model,HttpServletResponse response){
        model.addAttribute("fileId",id);
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "dzswj";
    }

    @RequestMapping("/pdf/fail")
    public String getFail(HttpServletResponse response){
        return "fail";
    }

    @RequestMapping("/pdf/getFileContent")
    @ResponseBody
    public FileContent getFileContent(@RequestBody FileContent fileContent) throws Exception {
        fileContent = pdfSeervice.getFileContent(fileContent);
        return fileContent;
    }


    @RequestMapping(value = "/generateQR")
    public void generateQR(FileContent fileContent, HttpServletResponse response, HttpServletRequest request) throws IOException {
        FileInputStream in = null;
        try{
            ServletOutputStream outputStream = response.getOutputStream();
            FileContent fileContent1 = pdfSeervice.getFileContent(fileContent);
            if(fileContent1 == null){
                response.setContentType("text/html;charset:utf-8;");
                response.sendRedirect("http://127.0.0.1/pdf/fail");
            }else{
                byte[] pdfBytes = new byte[in.available()];
                in =new FileInputStream(fileContent1.getPath());
                in.read(pdfBytes);
                response.setContentType("application/pdf");
                outputStream.write(pdfBytes);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(in != null){
                in.close();
            }
        }
    }

}
