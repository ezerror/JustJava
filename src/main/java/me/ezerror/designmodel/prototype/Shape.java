package me.ezerror.designmodel.prototype;

import java.awt.*;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 17:54
 * @description：
 * @modified By：
 * @version:
 */
public abstract class Shape implements Cloneable {
    int width ;
    int height ;
    Color color;

    public Shape() {
    }

    public Shape(Shape target) {
        if (target != null) {
            this.width = target.width;
            this.height  = target.height ;
            this.color = target.color;
        }
    }

    @Override
    public abstract Shape clone();
}
