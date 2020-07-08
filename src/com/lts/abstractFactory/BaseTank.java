package com.lts.abstractFactory;

import com.lts.Group;

import java.awt.*;

/**
 * @Date 2020/7/7 23:15
 * @Version 1.0
 **/
public abstract class BaseTank {
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public abstract Group getGroup();

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
