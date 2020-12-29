package me.ezerror.designmodel.prototype;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 17:55
 * @description：
 * @modified By：
 * @version:
 */
public class Rectangle extends Shape {

    public int width;
    public int height;

    public Rectangle() {
    }

    public Rectangle(Shape target) {
        super(target);
        if (target != null) {
            this.width = target.width;
            this.height = target.width;
        }
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

}
