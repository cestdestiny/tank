package com.lts;

import java.awt.*;

/**
 * @Date 2020/7/5 22:31
 * @Version 1.0
 **/
public class Bullet {
    private static final int SPEND = 5;

    private static int WIDTH = 20;
    private static int HEIGHT = 20;

    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (dir){
            case UP:
                y -= SPEND;
                break;
            case DOWN:
                y += SPEND;
                break;
            case LEFT:
                x -= SPEND;
                break;
            case RIGHT:
                x += SPEND;
                break;
            default:
                break;
        }
    }
}
