package me.ezerror.designmodel.factory.factory_method;

import me.ezerror.designmodel.factory.simple.SimpleHouseFactory;

/**
 * @author ：lionzz
 * @date ：Created in 2020/12/27 17:36
 * @description：
 * @modified By：
 * @version:
 */
public class Client {
    public static void main(String[] args) {
        HouseFactory factory = new WoodenHouseFactory();
        House house = factory.createHouse();
        System.out.println(house.getMaterial());

        factory = new StoneHouseFactory();
        house = factory.createHouse();
        System.out.println(house.getMaterial());
    }
}
