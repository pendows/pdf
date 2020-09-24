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
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class pdfController {

    @Autowired
    private PdfSeervice pdfSeervice;

    @RequestMapping("/pdf/index")
    public String getFileContent(@RequestParam(required = false) String id, Model model,HttpServletResponse response){
        model.addAttribute("fileId",id);
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "dzswj";
    }

    @RequestMapping("/pdf/getFileContent")
    @ResponseBody
    public FileContent getFileContent(@RequestBody FileContent fileContent) throws Exception {
        fileContent = pdfSeervice.getFileContent(fileContent);
        return fileContent;
    }


    @RequestMapping("/generateQR")
    public void generateQR(@RequestParam(required = false) FileContent fileContent, HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        try{
            in =new FileInputStream("D:\\广弘食品2020.pdf");
            byte[] pdfBytes = new byte[in.available()];
            in.read(pdfBytes);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pdfBytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            in.close();
        }
    }

}
