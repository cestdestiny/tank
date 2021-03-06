package com.lts;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/5 20:39
 * @Version 1.0
 **/
public class TankFrame extends Frame {

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD, this);

    List<Bullet> bulletList =  new ArrayList<>();

    List<Tank> tankList =  new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    String goodTankFireStrategy;
    String badTankFireStrategy;

    public TankFrame() {
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        goodTankFireStrategy = (String) PropertiesMgr.get("goodTankFireStrategy");
        badTankFireStrategy = (String) PropertiesMgr.get("badTankFireStrategy");
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_WIDTH);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + this.bulletList.size(), 10, 60);
        g.drawString("敌人数量：" + this.tankList.size(), 10, 80);
        g.setColor(c);
        myTank.paint(g);

        for (int i = 0; i < bulletList.size(); i++){
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i < tankList.size(); i++){
            tankList.get(i).paint(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }
        for (int i = 0; i < bulletList.size(); i++){
            for (int j = 0; j < tankList.size(); j++){
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL;
        boolean bR;
        boolean bU;
        boolean bD;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    FireStrategy fireStrategy = null;
                    try {
                        Class<?> clazz = Class.forName(goodTankFireStrategy);
                        fireStrategy = (FireStrategy)clazz.newInstance();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (myTank.getGroup() == Group.GOOD){
                        myTank.fire(fireStrategy);
                    }
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){

            if (!bL && !bD && !bU && !bR){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bL) {
                    myTank.setDir(Dir.LEFT);
                } else if (bD) {
                    myTank.setDir(Dir.DOWN);
                } else if (bU) {
                    myTank.setDir(Dir.UP);
                } else if (bR) {
                    myTank.setDir(Dir.RIGHT);
                }
            }
        }
    }
}
