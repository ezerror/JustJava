package me.ezerror.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIpModify {
    public static void main(String[] args) {
        try {
            // Get the first argument as the hostname.
            if (args.length > 0) {
                InetAddress[] inetAddresses = InetAddress.getAllByName(args[0]);
                for (InetAddress ia : inetAddresses) {
                    System.out.println(ia);
                }
            }
            else {
                //if No argument, get the localhost ip address.
                InetAddress localHostAddress = InetAddress.getLocalHost();
                System.out.println(localHostAddress);
                System.out.println(localHostAddress);
            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    // local - 接口名称
// static - 设置使用本地静态配置设置IP地址。
// 10.0.0.9 - 要修改的ip
// 255.0.0.0 － 子网掩码
// 10.0.0.1 － 网关，如果为none: 不设置默认网关。
// 1 －默认网关的跃点数。如果网关设置为 ’none’，则不应设置此字段。
    public static void setIP(String newip) throws Exception {
        Runtime.getRuntime().exec("netsh    interface    ip    set    addr    \"本地连接\"    static    "
                + newip + "    255.0.0.0     10.0.0.1     1");
    }
}
