package com.lts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Date 2020/7/6 22:45
 * @Version 1.0
 **/
public class ImageMgr {

    private ImageMgr(){}

    // goodTank
    public static BufferedImage goodTankL;
    public static BufferedImage goodTankD;
    public static BufferedImage goodTankU;
    public static BufferedImage goodTankR;

    // badTank
    public static BufferedImage badTankL;
    public static BufferedImage badTankD;
    public static BufferedImage badTankU;
    public static BufferedImage badTankR;


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
            goodTankU = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletL = ImageIO.read(classLoader.getResourceAsStream("images/bulletL.gif"));
            bulletD = ImageUtil.rotateImage(bulletL, -90);
            bulletU = ImageUtil.rotateImage(bulletL, 90);
            bulletR = ImageUtil.rotateImage(bulletL, 180);

            for (int i = 0; i < explodes.length; i++){
                explodes[i] = ImageIO.read(classLoader.getResourceAsStream("images/e"+ (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
