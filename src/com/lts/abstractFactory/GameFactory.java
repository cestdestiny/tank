package com.lts.abstractFactory;

import com.lts.*;

/**
 * @Date 2020/7/7 23:16
 * @Version 1.0
 **/
public class GameFactory {
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame){
        return new Bullet(x,y,dir,group,tankFrame);
    }

    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame){
        return new Tank(x,y,dir,group,tankFrame);
    }

    public BaseExplode createExplode(int x, int y, TankFrame tankFrame){
        return new Explode(x,y,tankFrame);
    }
}
