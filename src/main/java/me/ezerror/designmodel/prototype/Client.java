package me.ezerror.designmodel.prototype;

import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 17:58
 * @description：
 * @modified By：
 * @version:
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        ShapeRegister register = new ShapeRegister();
        Shape blue_rect = register.get("blue rect");
    }
}
