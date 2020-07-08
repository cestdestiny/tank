package com.lts.abstractFactory;

import java.awt.*;

/**
 * @Date 2020/7/7 23:14
 * @Version 1.0
 **/
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank baseTank);
}
