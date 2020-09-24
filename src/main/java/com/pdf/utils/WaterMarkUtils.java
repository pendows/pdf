package com.pdf.utils;

import java.awt.image.BufferedImage;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
/**
 *
 * <pre>
 *  进行水印处理. <br>
 * </pre>
 *
 * @author mazq
 * @date 2019/06/11
 */
public class WaterMarkUtils {

    public static void setWatermark(BufferedOutputStream bos, String filePath,  Long id )
            throws  IOException, DocumentException {
        FileInputStream in = null;
        try{
            in =new FileInputStream(filePath);
            byte[] pdfBytes = new byte[in.available()];
            in.read(pdfBytes);
            PdfReader reader = new PdfReader(pdfBytes);
            PdfStamper stamper = new PdfStamper(reader, bos);
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte waterMar;

            PdfGState gs = new PdfGState();
            long startTime = System.currentTimeMillis();
            System.out.println("PDF加图片水印>> start");
            for (int i = 1; i < total; i++) {
                //content = stamper.getOverContent(i);// 在内容上方加水印
                waterMar = stamper.getOverContent(i);//在内容下方加水印
                // 设置图片透明度为0.2f
                gs.setFillOpacity(0.2f);
                // 设置笔触字体不透明度为0.4f
                gs.setStrokeOpacity(0.4f);
                // 开始水印处理
                waterMar.beginText();
                // 设置透明度
                //waterMar.setGState(gs);
                // 设置水印字体参数及大小
                waterMar.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 60);
                // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
                waterMar.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！",  500, 430, 45);
                // 设置水印颜色
                waterMar.setColorFill(BaseColor.GRAY);
                BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://raha2n.natappfree.cc/pdf/index?id="+id, 0, 0, null);
                Image image = barcodeQRCode.getImage();

                image.setGrayFill(1);
                // 水印图片位置
                image.setAbsolutePosition(50, 700);
                // 边框固定
                image.scaleToFit(200, 200);
                // 设置旋转弧度
                //image.setRotation(30);// 旋转 弧度
                // 设置旋转角度
                //image.setRotationDegrees(45);// 旋转 角度
                // 设置等比缩放
                //image.scalePercent(90);
                // 自定义大小
                image.scaleAbsolute(88,88);
                // 附件加上水印图片
                waterMar.addImage(image);
                // 完成水印添加
                waterMar.endText();
                // stroke
                waterMar.stroke();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("PDF加图片水印>> ok 所用时间:[{}]"+(endTime-startTime)+"s");
            stamper.close();
            reader.close();
        }finally{
            in.close();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\ticket_out1.pdf"));
        WaterMarkUtils.setWatermark(bos,"D:\\ticket_out.pdf",0L);
    }
}

