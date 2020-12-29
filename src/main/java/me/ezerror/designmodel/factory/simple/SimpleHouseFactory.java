package me.ezerror.designmodel.factory.simple;

public class SimpleHouseFactory {

    public House createHouse(String type) {
        House house = null;
        if("wooden".equals(type)) {
            house = new WoodenCoffee();
        } else if("stone".equals(type)) {
            house = new StoneHouse();
        }
        return house;
    }
}
