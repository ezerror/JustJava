package me.ezerror.test.exception;

public class Demo {


    public static void main(String[] args) {
        try {
            new LoginService().login();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }
    }


}