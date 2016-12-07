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

public class RpcServer extends HttpServlet {
    private static final long serialVersionUID = 12341234345L;
    private JsonRpcServer rpcServer = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        rpcServer = new JsonRpcServer(new DemoServiceImply(), DemoServiceImply.class);
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        rpcServer.handle(request, response);
    }

}