package com.pdf.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerificationCodeUtil
{
    public static void main(String[] args) throws IOException
    {


        //VerificationCodeUtil.createCode(new Random());
    }

    /**
     * 生产验证码并且返回验证码数字
     * @param ran
     * @return
     * @throws IOException
     */
    public static List<Integer> createCode(Random ran) throws IOException
    {
        List<Integer> resultList = new ArrayList<>();
        int id = 4;
        int w = id * 30;
        int h = 28;
        BufferedImage buf = new BufferedImage(w, h,1);
        Graphics2D g = buf.createGraphics();
        //为了边框效果先画白背景再画蓝背景最后画白背景
        g.setColor(Color.WHITE);
        g.fillRect(0,0,w,h);
        g.setColor(Color.BLUE);
        g.fillRect(1,1,w,h);
        g.setColor(Color.WHITE);
        g.fillRect(2,2,w - 3,h - 3);
        //添加线条
        BasicStroke bs1 = new BasicStroke(1);
        g.setFont(new Font("宋体", Font.PLAIN,25));
        g.setStroke(bs1);
        for (int a = 0; a < 5; a++) {
            g.setColor(Color.GREEN);
            int x = ran.nextInt(w);
            int y = ran.nextInt(h);
            int x2 = 24 + ran.nextInt(w);
            int y2 = 10 + ran.nextInt(h + 5);
            g.drawLine(x, y, x2, y2);
        }
        //设置验证码数字
        Font font = new Font("宋体", Font.BOLD+ Font.ITALIC,22);
        g.setFont(font);
        for(int i = 0;i < id;i++) {
            Color color = new Color(200,45,33,255);
            g.setColor(color);
            String chars = "1234567890";
            String str = chars.charAt(ran.nextInt(chars.length()))+"";
            int x = 7 + i * 28;
            int y = 20;
            //字体旋转
            AffineTransform trans = new AffineTransform();
            trans.rotate(ran.nextInt(15)*3.14/180, 15*i+8, 7) ;
            //缩放文字
            float scaleSize = ran.nextFloat() + 0.8f ;
            if(scaleSize>1f){ scaleSize = 1f ; }
            trans.scale(scaleSize, scaleSize) ;
            g.setTransform(trans) ;
            resultList.add(Integer.parseInt(str));
            g.drawString(str, x, y);
        }
        g.dispose();
        File file = new File("/Users/aquarius/IdeaProjects/pdf-item/src/main/resources/static/five.jpg");
        ImageIO.write(buf,"jpg",file);

        return resultList;
    }
}



