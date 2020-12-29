package me.ezerror.designmodel.factory.simple;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 17:36
 * @description：
 * @modified By：
 * @version:
 */
public class Client {
    public static void main(String[] args) {
        SimpleHouseFactory factory = new SimpleHouseFactory();
        House wooden = factory.createHouse("wooden");
        System.out.println(wooden.getMaterial());
    }
}
