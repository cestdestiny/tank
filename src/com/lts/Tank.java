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

    Rectangle rect = new Rectangle();

    public static int WIDTH = ImageMgr.goodTankD.getWidth();
    public static int HEIGHT = ImageMgr.goodTankD.getHeight();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.rect.x = x;
        this.rect.y = y;
        this.rect.width = Tank.WIDTH;
        this.rect.height = Tank.HEIGHT;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        if (!living)
            tankFrame.tankList.remove(this);

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ImageMgr.goodTankL : ImageMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ImageMgr.goodTankR : ImageMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ImageMgr.goodTankU : ImageMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ImageMgr.goodTankD : ImageMgr.badTankD,x,y,null);
                break;
        }
        if (Group.BAD == this.group){
            if (random.nextInt(100) > 95){
                FireStrategy fireStrategy = null;
                try{
                    Class<?> clazz = Class.forName(tankFrame.badTankFireStrategy);
                    fireStrategy = (FireStrategy)clazz.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.fire(fireStrategy);
            }
            if (random.nextInt(100) > 95){
                dir = Dir.values()[random.nextInt(4)];
            }
        }
        move();
        boundCheck();
        this.rect.x = x;
        this.rect.y = y;
    }

    private void boundCheck() {
        if (this.x < 2){
            this.x = 2;
        }else if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH -2){
            this.x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if (this.y < 32){
            this.y = 32;
        }else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2){
            this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
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

    public void fire(FireStrategy fireStrategy){
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }
}
