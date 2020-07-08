package com.lts;

/**
 * @Date 2020/7/7 22:31
 * @Version 1.0
 **/
public class FourFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX;
        int bY;
        bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tank.getTankFrame().gameFactory.createBullet(bX, bY, dir, tank.getGroup(), tank.getTankFrame());
        }
    }
}
