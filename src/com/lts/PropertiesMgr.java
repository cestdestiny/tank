package com.lts;

import java.io.IOException;
import java.util.Properties;

/**
 * @Date 2020/7/7 21:48
 * @Version 1.0
 **/
public class PropertiesMgr {

    public static Properties prop = new Properties();

    private PropertiesMgr(){
    }


    static {
        try {
            prop.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        return prop.get(key);
    }
}
