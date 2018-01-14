date : 2016-12-06 19:50:20
title : jsonrpc4j初步使用(Quick Start For Jsonrpc4j)
categories : 2016-12
tags : 
 - Programming
 - Java
 - Rpc
 - jsonrpc4j
---

## 概述
目前有个项目需要向外提供服务:
 1. 不打算用spring相对来说比较重的框架
 2. 后来考虑过jersey+jetty的restful框架,但是restful本身的应用场景,或者说是抽象较为局限,主要是编者水平有限
 3. 再之后又看了thrift的使用,稍微上手,认为操作还是较为繁杂.
 4. json-rpc以前并没有接触过,json相对来说又有传输占用带宽较小等广为人知的优点,便以此篇为个契机学习一下.
jsonrpc4j是briandilley开发,项目的地址是[github](https://github.com/briandilley/jsonrpc4j)
jsonrpc4j 服从json-rpc的规范,[规范链接在此](http://www.jsonrpc.org/)
其主要支持下面这4类客户端:
Streaming server (InputStream \ OutputStream)
HTTP Server (HttpServletRequest \ HttpServletResponse)
Portlet Server (ResourceRequest \ ResourceResponse)
Socket Server (StreamServer)
这次主要使用的是HTTP Server

## Maven
jsonrpc项目通过Maven构建,首先需要在pom.xm中加入对jsonrpc4j的依赖,如下面所示:
```
    <!-- jsonrpc4j -->
    <dependency>
        <groupId>com.github.briandilley.jsonrpc4j</groupId>
        <artifactId>jsonrpc4j</artifactId>
        <version>1.4.6</version>
    </dependency>
```
但是仅仅加入上面的依赖还不够,完整的pom.xml如下所示:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
 
    <groupId>com.littleji</groupId>
    <artifactId>jsonrpc-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>com.github.briandilley.jsonrpc4j</groupId>
            <artifactId>jsonrpc4j</artifactId>
            <version>1.4.6</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.7.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.7.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.7.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.7.2</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.9</version>
        </dependency>
 
        <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpcore-nio</artifactId>
            <version>4.4.4</version>
        </dependency>
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.10</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.portlet/portlet-api -->
        <dependency>
            <groupId>javax.portlet</groupId>
            <artifactId>portlet-api</artifactId>
            <version>2.0</version>
        </dependency>
        <dependency>
            <groupId>net.iharder</groupId>
            <artifactId>base64</artifactId>
            <version>2.3.9</version>
        </dependency>
    </dependencies>
 
</project>
```

## 配置测试用的model,service

### model
```
package com.littleji.jsonrpc.demo;
 
import java.io.Serializable;
 
public class DemoBean implements Serializable{
    private static final long serialVersionUID = -5141784935371524L;
    private int code;
    private String msg;
 
    public int getCode() {
        return code;
    }
 
    public void setCode(int code) {
        this.code = code;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```

### Service和其Implement
```
package com.littleji.jsonrpc.demo;
 
/**
* Created by Jimmy on 2016/12/6.
*/
public interface DemoService {
        public DemoBean getDemo(String code, String msg);
 
        public Integer getInt(Integer code);
 
        public String getString(String msg);
 
        public void doSomething();
 
}
```

```
public class DemoServiceImply implements DemoService {
 
    public DemoBean getDemo(String code, String msg) {
        DemoBean bean1 = new DemoBean();
        bean1.setCode(Integer.parseInt(code));
        bean1.setMsg(msg);
        return bean1;
    }
 
    public Integer getInt(Integer code) {
        return code;
    }
 
    public String getString(String msg) {
        return "{"+msg+"}";
    }
 
    public void doSomething() {
        System.out.println("do something");
    }
 
}
```

## 配置server端
```
package com.littleji.jsonrpc.demo;
 
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
```

## Servlet的web.xml
```
<!DOCTYPE web-app PUBLIC
"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
"http://java.sun.com/dtd/web-app_2_3.dtd" >
 
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <servlet>
      <servlet-name>RpcServer</servlet-name>
      <display-name>RpcServer</display-name>
      <description></description>
      <servlet-class>com.littleji.jsonrpc.demo.RpcServer</servlet-class>
  </servlet>
  <servlet-mapping>
      <servlet-name>RpcServer</servlet-name>
      <url-pattern>/rpc</url-pattern>
  </servlet-mapping>
</web-app>
```

## 测试类
```
package com.littleji.jsonrpc.test;
 
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.littleji.jsonrpc.demo.DemoService;
 
 
import java.net.URL;
 
 
/**
* Created by Jimmy on 2016/12/7.
*/
public class jsonrpctest2 {
    public static void main(String [] args) throws Throwable {
        try {
            JsonRpcHttpClient client = new JsonRpcHttpClient(
                    new URL("http://127.0.0.1:8080/rpc"));
 
            DemoService userService = ProxyUtil.createClientProxy(
                    client.getClass().getClassLoader(),
                    DemoService.class,
                    client);
             //两种调用方式
            System.out.println(userService.getString("aa"));
            System.out.println( client.invoke("getString", new String[] { "haha" }, String.class));
            System.out.println( client.invoke("getInt", new Integer[] { 2 }, Integer.class));
 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Streaming server and client
jsonrpc4j 主要是通过流服务器和客户端来处理应用(不仅仅是HTTP)的请求,而`JsonRpcClient`和`JsonRpcServer`有一些简单的方法来获得输入和输出流,且在库中附带着一个JsonRpcHttpClient,用来扩展JsonRpcClient对于HTTP的支持

## Without the Spring Framework
jsonrpc4j可以不使用spring框架,事实上二者均可运行于Android环境中

## 对测试的解释
其实测试类就是一个客户端的简单使用
下面是一个客户端使用JSON-RPC服务的例子,具体如下:
```
JsonRpcHttpClient client = new JsonRpcHttpClient(
    new URL("http://example.com/UserService.json"));
 
User user = client.invoke("createUser", new Object[] { "bob", "the builder" }, User.class);
```
或者使用ProxyUtil类结合接口,来创建一个动态的代理,如下所示:
```
JsonRpcHttpClient client = new JsonRpcHttpClient(
    new URL("http://example.com/UserService.json"));
 
UserService userService = ProxyUtil.createClientProxy(
    getClass().getClassLoader(),
    UserService.class,
    client);
 
User user = userService.createUser("bob", "the builder");
```



## 参考
[jsonrpc4j](https://github.com/briandilley/jsonrpc4j)
[JSON-RPC（jsonrpc4j）使用demo](http://blog.csdn.net/hjcenry/article/details/52440925)
