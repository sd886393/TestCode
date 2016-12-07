date : 2016-11-30 14:27:20
title : 使用ElasticSearch的JavaAPI 1(UseJavaApiForElasticSearch 1)
categories : 2016-11
tags : 
 - Programming
 - DistributedComputation
 - ELK
 - ElasticSearch
 - Translate
---


## 配置(configuration)
### Maven
要使用相应的API,必须引入所需要的jar包,这里使用的ElasticSearch5.0版本相应的Maven配置如下所示:
```
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>5.0.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.6.2</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.6.2</version>
</dependency>
```
### 日志(Logger)
之后配置日志,这里使用的是log4j2,在`src/main/resources`下添加名为`log4f2.properties`的文件,并在其中添加如下的内容:
```
appender.console.type = Console
appender.console.name = console
appender.console.layout.type = PatternLayout
 
rootLogger.level = info
rootLogger.appenderRef.console.ref = console
```

### 整体打包(Embedding jar with dependencies)
将所使用的jar依赖包和你的应用同时打包为一个jar文件,此时不应该使用`maven-assembly-plugin`, 因为没有Lucene的jar包,导致该插件无法解析`META-INF/service`的结构,相应的你可以使用`maven-shade-plugin`插件,配置如下所示
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.4.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals><goal>shade</goal></goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
如果有main.class 可加入下面的配置:
```
<transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
    <mainClass>org.elasticsearch.demo.Generate</mainClass>
</transformer>
```

### 使用客户端(Client)
客户端可以做以下几件事情:
 1. 在已有的集群之上进行,索引,插入,删除,搜索等操作
 2. 在已有的集群上运行管理员的任务
 * 注意: 客户端的主版本号必须同集群节点的版本号相一致
一般通过`TransportClient` 来连接ES集群

### TransportClient
当你使用TansportClient来连接一个ES集群的时候,你并不参与到这个集群中,而是获得这个集群的一个或多个地址,并在实际执行操作的时候,依次的操作它们,尽管大多数的操作都可能是两段式的(It does not join the cluster, but simply gets one or more initial transport addresses and communicates with them in round robin fashion on each action(though most actions will probably be "two hop" operations).)
下面是如何开始和结束一个TransportClient
```
// on startup
 
TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
 
// on shutdown
 
client.close(); 
```
其中得Setting,按照下面所示进行设置
```
Settings settings = Settings.builder()
        .put("cluster.name", "myClusterName").build();
TransportClient client = new PreBuiltTransportClient(settings);
//Add transport addresses and do something with the client...
```
TransportClient 自身有一个集群发现的功能,其能够动态的添加host和移除之前已有的.
一旦发现功能启用,transport client就将根据其配置的节点列表进行连接,而节点列表的配置则是通过`addTransportAddress`来配置的.之后,客户端将调用集群内部的状态API,来发现可用的数据节点.内部客户端的节点列表将替换为只有数据节点,并且这份列表默认每过5秒,刷新一次
如果需要开启发现功能,设置`client.trasport.sniff`为true

## 参考
[ElasticSearch Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/)
