package com.lts;

import java.awt.*;
import java.util.Random;

/**
 * @Date 2020/7/5 21:33
 * @Version 1.0
 **/
public class Tank {

    private int x,y;

    private Dir dir;
    private static final int SPEND = 3;

    private boolean moving = true;

    private TankFrame tankFrame;

    private Random random = new Random();

    private Group group;

    private boolean living = true;

    public static int WIDTH = ImageMgr.tankD.getWidth();
    public static int HEIGHT = ImageMgr.tankD.getHeight();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living)
            tankFrame.tankList.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage(ImageMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ImageMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ImageMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ImageMgr.tankD,x,y,null);
                break;
        }
        move();
        if (Group.BAD == this.group){
            if (random.nextInt(10) > 8){
                this.fire();
            }
        }
    }

    private void move() {
        if (!moving)
            return;
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

    public void fire(){
        int bX;
        int bY;
        if (dir == Dir.DOWN || dir == Dir.UP){
            bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
            bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        }else{
            // 待处理
            bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
            bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        }

        tankFrame.bulletList.add(new Bullet(bX, bY, this.dir, this.group, tankFrame));
    }

    public void die() {
        this.living = false;
    }
}
