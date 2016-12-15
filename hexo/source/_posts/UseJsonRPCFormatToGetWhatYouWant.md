date : 2016-12-12 19:50:20
title : json-rpc的标准调用(UseJsonRPCFormatToGetWhatYouWant)
categories : 2016-12
tags : 
 - Programming
 - Java
 - Rpc
 - jsonrpc4j
---

## 概述
本文承接上文的[jsonrpc4j初步使用](http://littleji.com/2016/12/06/QuickStartForJsonrpc4j/),在知道了如何部署Server端以及通过Java的接口调用部署的服务后,下面就是通过json-rpc本身的标准来实际的调用rpc借口和处理返回的相关信息.

## json-rpc的标准详细说明
这方面具体请参考:
 1. [中文](http://wiki.geekdream.com/Specification/json-rpc_2.0.html)
 2. [English](http://www.json.org/)
这里拿出一些必要且简单的说明.

##  rpc请求所必要的参数
 1. 请求的对象需要通过json的格式发往server端
 2. 请求对象需要包括下面四个对象
  1. jsonrpc:来说明所使用的JSON-RPC的版本
  2. methode:来说明说调用的方法名称
  3. params:调用方法的结构化参数值
  4. id:来唯一的表明发起的请求,客户端同样通过这个id来唯一的表示响应的信息,这个id可以是字符串,数字和NULL.如果没有这个值则认为这个请求是一个通知

## rpc响应所必要的参数
 1. jsonrpc:同请求
 2. result: 如果一个请求成功的调用了方法,则必须有这一项,否则不必有这一项
 3. error:错误的对象.如果一个方法失败了,必须有该项,否则不必有
 4. id:该成员必须有,同请求.

## 错误对象所必要的参数
 1. code:使用整数来表明的异常错误类型,-32768到-32000为保留的错误代码,详细的错误代码所对应的解释请参考 [中文](http://wiki.geekdream.com/Specification/json-rpc_2.0.html)
 2. message:描述错误信息
 3. data:包含错误复杂信息的成员,可忽略.

## 使用curl来测试已经建立的json-rpc服务
* 需要按照前文,建立好对应的服务

### 测试单个请求
通过使用curl来发送http的请求,当然也可以通过java的HttpClient来发送,假设RpcServer部署相应的服务,且url为`/rpc`,我们使用server端的"getString"方法,server端部署在192.168.1.10的8080端口上,那么代码如下
```
curl -XPOST "http://192.168.1.10:8080/rpc" -d "{"jsonrpc":"2.0", "id":"10", "method":"getString", "params":["Test"]}"
```

可以看到服务端返回这样的信息
```
{"jsonrpc":"jsonrpc","id":"null","error":{"code":-32700,"message":"JSON parse error"}}
```

说明发送的信息不是一个合法的JSON串,检查后发现因为curl发送数据的问题,需要对引号进行转义,更改后的请求命令如下:
```
curl -XPOST "http://192.168.1.10:8080/rpc" -d "{\"jsonrpc\":\"2.0\", \"id\":\"10\", \"method\":\"getString\", \"params\":[\"Test\"]}"
```

服务端返回信息:
```
{"jsonrpc":"2.0","id":"10","result":"{Test}"}
```
信息正常的返回了,说明调用成功

### 测试批的方式发送请求
原理非常简单,只需要将发送的一组请求使用数组的形式括起来即可,代码如下:
```
curl -XPOST "http://192.168.101.188:8080/rpc" -d "[{\"jsonrpc\":\"2.0\", \"id\":\"1\", \"method\":\"getString\", \"params\":[\"Test\"]},{\"jsonrpc\":\"2.0\", \"id\":\"2\", \"method\":\"getString\", \"params\":[\"哈哈\"]}]"
```

服务端返回:
```
[{"jsonrpc":"2.0","id":"1","result":"{Test}"}
,{"jsonrpc":"2.0","id":"2","result":"{哈哈}"}
```

