package com.lts;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTank = Integer.parseInt((String) PropertiesMgr.get("initTank"));
        for (int i = 0; i < initTank; i++){
            tankFrame.tankList.add(new Tank(100 + i*100, 100,Dir.DOWN, Group.BAD, tankFrame));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
