package me.ezerror.designmodel.factory.factory_method;

public class WoodenHouseFactory implements HouseFactory {

    public House createHouse() {
        return new WoodenHouse();
    }
}
