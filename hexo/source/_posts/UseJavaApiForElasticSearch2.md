date : 2016-12-01 10:40:20
title : 使用ElasticSearch的JavaAPI2(UseJavaApiForElasticSearch2)
categories : 2016-12
tags : 
 - Programming
 - DistributedComputation
 - ELK
 - ElasticSearch
 - Translate
---


## 单独文档的APIs

### Index API

#### 生成Json

* 手动
* 使用`Map`
* 使用第三方库如Jackson
* 使用內建的jsonBuilder()

#### 建立索引文档

下面的例子是建立一个索引为twitter,类型为tweet,id为1的文档,
```
import static org.elasticsearch.common.xcontent.XContentFactory.*;
 
IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
        .setSource(jsonBuilder()
                    .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                    .endObject()
                  )
        .get();
```
或者通过另一种建立json的方式
```
String json = "{" +
        "\"user\":\"kimchy\"," +
        "\"postDate\":\"2013-01-30\"," +
        "\"message\":\"trying out Elasticsearch\"" +
    "}";
 
IndexResponse response = client.prepareIndex("twitter", "tweet")
        .setSource(json)
        .get();
```
之后你可以通过调用上面的`response`来查看建立的结果
```
// Index name
String _index = response.getIndex();
// Type name
String _type = response.getType();
// Document ID (generated or not)
String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
long _version = response.getVersion();
// isCreated() is true if the document is a new one, false if it has been updated
boolean created = response.isCreated();
```

### GetAPI
下面使用client调用 index是 twitter type是tweet id为1的文档
```
GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
```
更多的getapi则需要参考[get docs](http://www.elastic.co/guide/en/elasticsearch/reference/5.0/docs-get.html)

其它的delete update multiget  bulkapi都需要查看es的相应rest调用文档

### BulkAPI

bulk API 允许你通过一个请求来检索或者删除多个文档,增加吞吐量,下面是一个应用:
```
import static org.elasticsearch.common.xcontent.XContentFactory.*;
 
BulkRequestBuilder bulkRequest = client.prepareBulk();
 
// either use client#prepare, or use Requests# to directly build index/delete requests
bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
        .setSource(jsonBuilder()
                    .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                    .endObject()
                  )
        );
 
bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
        .setSource(jsonBuilder()
                    .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                    .endObject()
                  )
        );
 
BulkResponse bulkResponse = bulkRequest.get();
if (bulkResponse.hasFailures()) {
    // process failures by iterating through each bulk response item
}
```

### 使用Using Bulk Processor
`BulkProcessor`类提供了一个简单的接口可以通过请求的数量或者给定的时间来自动的进行flush操作.
使用这个类,首先要创建一个BulkProcessor实例:
```
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
 
BulkProcessor bulkProcessor = BulkProcessor.builder(
        client, 
        new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId,
                                   BulkRequest request) { ... }
 
            @Override
            public void afterBulk(long executionId,
                                  BulkRequest request,
                                  BulkResponse response) { ... }
 
            @Override
            public void afterBulk(long executionId,
                                  BulkRequest request,
                                  Throwable failure) { ... }
        })
        .setBulkActions(10000)
        .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
        .setFlushInterval(TimeValue.timeValueSeconds(5))
        .setConcurrentRequests(1)
        .setBackoffPolicy(
            BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
        .build();
```
其中的`setConcurrentRequests(1)`指同时允许多少个并发,0的话意味着不允许,1则表示允许一个并发请求.
`setBackoffPolicy`则可以允许用户自定义当一个或者多个bulk请求失败后,该执行如何操作,而这个失败是要求基于`EsRejectedExecutionException`,也就是说集群内的计算资源不够导致的请求失败,如果不设定的话使用`BackoffPolicy.noBackoff()`
当所有的需要执行的操作加载到bulk中之后,可使用两种方式来关闭这个bulk
```
bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
or
bulkProcessor.close();
```
这两个操作都会flush所有的剩余bulk操作,前者会等待一段时间,如果在这段时间 flush成功,则返回`true` 否则返回`false`,
后者则会直接退出,不在等待所有的bulk执行操作完成




## 参考
[ElasticSearch Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/)
