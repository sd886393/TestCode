package zcah.xydy863.jsonrpc.test;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import zcah.xydy863.jsonrpc.demo.DemoBean;
import zcah.xydy863.jsonrpc.demo.DemoService;
import zcah.xydy863.jsonrpc.demo.DemoServiceImply;

import java.net.URL;

/**
 * Created by Jimmy on 2016/12/7.
 */
public class jsonrpctest2 {
    public static void main(String [] args) throws Throwable {
        try {
            JsonRpcHttpClient client = new JsonRpcHttpClient(
                    new URL("http://localhost:8080/rpc"));

            DemoService userService = ProxyUtil.createClientProxy(
                    client.getClass().getClassLoader(),
                    DemoService.class,
                    client);

           // System.out.println(userService.getString("aa"));
            client.invoke("getString", "aa", String.class);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
