package com.pdf.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Imageyan
{
    public static void main(String[] args) throws IOException
    {
        Random random = new Random();
        int id = 4;
        int w = id*30;//50
        int h = 28;//80
        BufferedImage buf = new BufferedImage(w,h,1);
        Graphics2D g = buf.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,w,h);

        g.setColor(Color.WHITE);
        g.fillRect(1,1,w - 2,h - 2);
        Random ran = new Random();

        //添加线条
        BasicStroke bs1 = new BasicStroke(2);
        g.setFont(new Font("宋体",Font.PLAIN,25));
        g.setStroke(bs1);
        for (int a = 0; a < 5; a++) {
            Color color = new Color(38, 229, 12,235);
            g.setColor(color);
            int x = ran.nextInt(w);
            int y = ran.nextInt(h);
            int x2 = 24 + ran.nextInt(w);
            int y2 = 10 + ran.nextInt(h + 5);
            g.drawLine(x, y, x2, y2);
        }

        //设置字
        Font font = new Font("宋体",Font.BOLD+ Font.ITALIC,22);
        g.setFont(font);
        for(int i = 0;i < id;i++) {
            Color color = new Color(240,50,49,255);
            g.setColor(color);
            String chars = "1234567890";
            String str = "";
            str = chars.charAt(ran.nextInt(chars.length()))+"";
            int x = 7 + i * 28;
            int y = 20;
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(15)*3.14/180, 15*i+8, 7) ;
            //缩放文字
            float scaleSize = random.nextFloat() + 0.8f ;
            if(scaleSize>1f){ scaleSize = 1f ; }
            trans.scale(scaleSize, scaleSize) ;
            g.setTransform(trans) ;
            g.drawString(str, x, y);
        }


        System.out.println();
        g.dispose();
        ImageIO.write(buf,"jpg",new File("/Users/aquarius/IdeaProjects/pdf-item/src/main/resources/static/five.jpg"));
        System.out.println("绘画完毕！");


    }
}



