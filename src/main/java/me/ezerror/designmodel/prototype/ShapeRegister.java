package me.ezerror.designmodel.prototype;

import java.awt.*;
import java.util.HashMap;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 18:02
 * @description：
 * @modified By：
 * @version:
 */
public class ShapeRegister {

    HashMap<String,Shape> map = new HashMap<>();

    public ShapeRegister() throws CloneNotSupportedException {
        Rectangle rect = new Rectangle();
        rect.width = 100;
        rect.height =200;
        rect.color = Color.BLUE;
        map.put("blue rect", rect);
    }

    public Shape put(String key, Shape shape) {
        map.put(key, shape);
        return shape;
    }

    public Shape get(String key) {
        return map.get(key).clone();
    }
}
