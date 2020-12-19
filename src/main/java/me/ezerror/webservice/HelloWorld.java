package me.ezerror.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.UnsupportedEncodingException;

/**
 * @author ：师源
 * @date ：Created in 2020/11/12 9:46
 * @description：
 * @modified By：
 * @version:
 */


@WebService
public class HelloWorld {
    @WebMethod
    public String sayHello(String str) {
        System.out.println("get Message...");
        String result = "Hello World, " + str;
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("server is running");
        String address = "http://localhost:9000/HelloWorld";
        Object implementor = new HelloWorld();
        Endpoint.publish(address, implementor);
    }



}
