package me.ezerror.designmodel.factory.factory_method;

public class StoneHouseFactory implements HouseFactory{

    public House createHouse() {
        return new StoneHouse();
    }
}
