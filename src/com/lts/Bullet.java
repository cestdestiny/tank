package com.lts;

import sun.plugin.dom.css.Rect;

import java.awt.*;

import static com.lts.TankFrame.GAME_HEIGHT;
import static com.lts.TankFrame.GAME_WIDTH;

/**
 * @Date 2020/7/5 22:31
 * @Version 1.0
 **/
public class Bullet {
    private static final int SPEND = 10;

    public static int WIDTH = ImageMgr.bulletD.getWidth();
    public static int HEIGHT = ImageMgr.bulletD.getHeight();

    private int x,y;
    private Dir dir;

    private boolean living = true;

    private Group group;

    private TankFrame tankFrame;

    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.rect.x = x;
        this.rect.y = y;
        this.rect.width = Tank.WIDTH;
        this.rect.height = Tank.HEIGHT;
        tankFrame.bulletList.add(this);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){
        if (!living){
            tankFrame.bulletList.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ImageMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ImageMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ImageMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ImageMgr.bulletD,x,y,null);
                break;
        }
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
        if (x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT){
            this.living = false;
        }
        rect.x = x;
        rect.y = y;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup())
            return;

        if (this.rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodeList.add(new Explode(eX, eY, this.tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}
