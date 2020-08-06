package me.nicadtest;

import me.ezerror.test.exception.LoginException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginService3 {
    public static final Map<String, String> usernameAndPwd = new HashMap<String, String>();

    static {
        usernameAndPwd.put("sy", "root");
        usernameAndPwd.put("admin", "12333");
    }

    public void login() throws LoginException {
        System.out.print("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.print("请输入密码：");
        String password = sc.next();

        if (!(usernameAndPwd.containsKey(username) && usernameAndPwd.get(username).equals(password))) {
            throw new LoginException();
        }
        else {
            System.out.println("登录成功");
        }
    }
}
