package me.sample.netchanger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author ：师源
 * @date ：Created in 2020/12/21 15:45
 * @description：
 * @modified By：
 * @version:
 */
public class NetChange {

    public static void main(String[] args) throws IOException {
        String[] cmd2 = new String[]{"cmd.exe", "/C", "ping www.baidu.com"};
        String exec = exec(cmd2);
        if (exec.contains("Ping 请求找不到主机")) {
            String[] cmd = new String[]{"cmd.exe", "/C", "netsh interface ip set address name=\"以太网\" source=dhcp"};
            exec(cmd);
            exec = exec(cmd2);
            if (exec.contains("Ping 请求找不到主机")) {
                cmd = new String[]{"cmd.exe", "/C", "netsh interface ip set address name=\"以太网\" source=static addr=192.168.156.81 mask=255.255.255.0 gateway=192.168.156.1 gwmetric=0"};
                exec(cmd);
            }
        }

    }

    public static String exec(String[] command) throws IOException {
        Scanner input = null;
        StringBuilder result = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            try {
                //等待命令执行完成
                process.waitFor(10, TimeUnit.SECONDS);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            InputStream is = process.getInputStream();
            input = new Scanner(is, "GBK");
            while (input.hasNextLine()) {
                result.append(input.nextLine()).append("\n");
            }
        }
        finally {
            if (input != null) {
                input.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return result.toString();
    }
}
