package com.lts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Date 2020/7/6 22:45
 * @Version 1.0
 **/
public class ImageMgr {

    // 坦克上下左右图片
    public static BufferedImage tankL;
    public static BufferedImage tankD;
    public static BufferedImage tankU;
    public static BufferedImage tankR;

    // 子弹上下左右图片
    public static BufferedImage bulletL;
    public static BufferedImage bulletD;
    public static BufferedImage bulletU;
    public static BufferedImage bulletR;

    // 爆炸

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        ClassLoader classLoader = ImageMgr.class.getClassLoader();
        try {
            tankL = ImageIO.read(classLoader.getResourceAsStream("images/tankL.gif"));
            tankD = ImageIO.read(classLoader.getResourceAsStream("images/tankD.gif"));
            tankU = ImageIO.read(classLoader.getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(classLoader.getResourceAsStream("images/tankR.gif"));
            bulletL = ImageIO.read(classLoader.getResourceAsStream("images/bulletL.gif"));
            bulletD = ImageIO.read(classLoader.getResourceAsStream("images/bulletD.gif"));
            bulletU = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(classLoader.getResourceAsStream("images/bulletR.gif"));

            for (int i = 0; i < explodes.length; i++){
                explodes[i] = ImageIO.read(classLoader.getResourceAsStream("images/e"+ (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
