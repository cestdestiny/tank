package com.lts;

import java.awt.*;

import static com.lts.TankFrame.GAME_HEIGHT;
import static com.lts.TankFrame.GAME_WIDTH;

/**
 * @Date 2020/7/5 22:31
 * @Version 1.0
 **/
public class Explode {
    public static int WIDTH = ImageMgr.explodes[0].getWidth();
    public static int HEIGHT = ImageMgr.explodes[0].getHeight();

    private int x,y;
    private TankFrame tankFrame;

    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void paint(Graphics g){
//      Audio a = new Audio("audio/explode.wav");
//		Audio a = new Audio("audio/war1.wav");
//		a.loop();

        g.drawImage(ImageMgr.explodes[step++],this.x,this.y, null);
        if (step >= ImageMgr.explodes.length)
            this.tankFrame.explodeList.remove(this);
    }
}
