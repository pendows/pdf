package com.pdf.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能 PDF读写类
 * @CreateTime 2011-4-14 下午02:44:11
 */
public class PDFUtil {
    /**
     * 功能：创建导出数据的目标文档
     *
     * @return
     */
    public static void main(String[] args) throws IOException {
        String outputPath = "D:\\111.pdf";
        PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath));
        String fileName = "D:\\ticket_out.pdf";

        readPdf(fileName);//直接读全PDF面

        //readPdf_filter(fileName);//读取PDF面的某个区域

    }

    public static Map<String,String> readPdf(String fileName) {
        Map<String,String> map = new HashMap<>();
        String pageContent = "";
        try {
            PdfReplacer textReplacer = new PdfReplacer(fileName);
            //textReplacer.replaceText("1638.97", "     1638");
            textReplacer.replaceText(55,710,70,70,"");
            PdfReader reader = new PdfReader(fileName);
            int pageNum = reader.getNumberOfPages();
            for (int i = 1; i <= pageNum; i++) {
                pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                if(pageContent.contains("证明")){
                    map.put("fileNo",pageContent.replace("\n","").substring(0,20));
                }

                if(pageContent.contains("（大写）")){
                    int index = pageContent.lastIndexOf("（大写）");
                    int endIndex = pageContent.lastIndexOf("￥");
                    map.put("fileCapitalize",pageContent.substring(index+5,endIndex).replace("\n","").trim());
                }

                if(pageContent.contains("填 发 日 期")){
                    int index = pageContent.lastIndexOf("填 发 日 期");
                    map.put("fileDate",pageContent.substring(index+7,index+20).replace("\n","").trim());
                }

                if(pageContent.contains("￥")){
                    int index = pageContent.lastIndexOf("￥");
                    map.put("fileAmout",pageContent.substring(index,index+11).replace("\n","").trim());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


