package me.ezerror.test.exception;

public class LoginException extends Exception {

    @Override
    public String getMessage() {
        return "测试异常";
    }
}
