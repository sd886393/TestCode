package zcah.xydy863.jsonrpc.demo;

/**
 * Created by Jimmy on 2016/12/6.
 */
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class RpcServer extends HttpServlet {
    private static final long serialVersionUID = 12341234345L;
    private JsonRpcServer rpcServer = null;
    private DemoService demoService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        demoService = new DemoServiceImply();
        Object compositeService = ProxyUtil.createCompositeServiceProxy(
                this.getClass().getClassLoader(),
                new Object[] { demoService},
                new Class<?>[] { DemoService.class},
                true);

        rpcServer = new JsonRpcServer(compositeService);
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        rpcServer.handle(request, response);
    }

}