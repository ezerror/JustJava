package me.ezerror.designmodel.builder;

public class Client {
    public static void main(String[] args) {
        Computer.Builder builder = new Computer.Builder("i9-10900k", "kingston").setDisplay("benq").setKeyboard("logic");
        Computer computer = builder.build();
        System.out.println(computer);
    }
}
