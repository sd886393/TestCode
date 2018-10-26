title: Logstash Grok 概览
date: 2018-10-25
categories: 
- 2018-10
tags: 
 - Logstash
 - 框架
---
# 背景
Grok 插件让 Logstash 在处理一定格式的日志文件的时候有了一定程度“正则表达”的功能。
特别适用于解析一些非结构化的数据，并从中提取真正相关的信息至一些结构化 field 中。

主要的使用场景在 syslog、apache 与其他的 webserver 具有这样特点的日志：这些日志都具有易人读但不易机读的特点。

Logstash 默认有很多 patterns，你可以在 [https://github.com/logstash-plugins/logstash-patterns-core/tree/master/patterns](https://github.com/logstash-plugins/logstash-patterns-core/tree/master/patterns) 找到。当然你也可以添加你自己的 pattern，这点我们稍后就会讲到。

# 测试你的 Grok pattern
Debug 是人类进步的阶梯，想起来当时被正则表达式支配的恐惧了么？那时我们有一些工具来测试我们的正则表达式是否足够“强壮”，grok 的作者也提供了两个网站，允许你通过这个来测试你的 grok 表达式，就跟你在测试正表达式的时候一样。分别是 [http://grokdebug.herokuapp.com ](http://grokdebug.herokuapp.com ) 和 [ http://grokconstructor.appspot.com/]( http://grokconstructor.appspot.com/)

# Grok 的一些基本设定

## 语法
Grok 使用如下的语法进行匹配操作。
```
%{SYNTAX:SEMANTIC}
```
其中 `SYNTAX`是你想匹配的表达式的名称，SEMANTIC 则是存储匹配后的文本的变量名，比如：
```
# 待匹配的文本为
192.168.1.1 2016-04-26T19:55:15Z
# 一个完整的匹配式
%{IPV4:ip} %{TIMESTAMP_ISO8601:time}
# pattern 为
IPV4 (?<![0-9])(?:(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2}))(?![0-9])

TIMESTAMP_ISO8601 %{YEAR}-%{MONTHNUM}-%{MONTHDAY}[T ]%{HOUR}:?%{MINUTE}(?::?%{SECOND})?%{ISO8601_TIMEZONE}?
```
那么 `IPV4` 和 `TIMESTAMP_ISO8601`就是 `SYNTAX`，`ip` 和 `time` 就是 SEMANTIC。

## 一个实际的例子
一个 logstash 的配置文件，内容如下：
```
input {
  file {
    path => "/var/log/http.log"
  }
}
filter {
  grok {
    match => { "message" => "%{IP:client} %{WORD:method} %{URIPATHPARAM:request} %{NUMBER:bytes} %{NUMBER:duration}" }
  }
}
```
运行后，这条 message 将会在 grok 过滤之后上报一条 event，内容如下：
```
client: 55.3.244.1
method: GET
request: /index.html
bytes: 15824
duration: 0.043
```

# 正则表达式库
Grok 使用了 [oniguruma](https://github.com/kkos/oniguruma/blob/master/doc/RE) 的正则表达式，所以所有在该文档中出现的匹配语法都可以使用。

# 自定义 pattern
Grok 自带了一些常用的匹配规则，比如：IPV4、IPV6、YEAR等，但是当我们想要写一些自己的规则的时候，grok 也提供了支持，这里主要有两种方式：

## 第一种方式添加自定义 pattern
直接使用下面的 pattern 结构：
```
 (?<field_name>the pattern here)
```
其中 field_name 可以理解为上文的 SEMANTIC，后面则跟的是实际的匹配表达式，比如这样：
```
(?<queue_id>[0-9A-F]{10,11})
```

## 第二种方式添加自定义 pattern
第二种方式就如同我们一开始使用的一样，在指定的 pattern 文件夹，添加即可比如：
```
# 在 ./pattern/postfix 添加
POSTFIX_QUEUEID [0-9A-F]{10,11}
```
在实际匹配的时候写入
```
%{POSTFIX_QUEUEID:queue_id}
```
使用的效果与第一种方式完全一致。

Logstash 的配置文件中可以指定 自定义 pattern 的位置，比如：
```
filter {
  grok {
    patterns_dir => ["./patterns"]
    match => { "message" => "%{SYSLOGBASE} %{POSTFIX_QUEUEID:queue_id}: %{GREEDYDATA:syslog_message}" }
  }
}
```
就是读取当前目录下的 patterns 文件。

# Grok 插件的一些配置项
如果需要定制化 Grok 插件的一些匹配配置

配置项名称 | 匹配项类型 | 是否必须
--- | --- | --- 
break_on_match | boolean | no
keep_empty_capture | boolean | no
match | hash | no
named_captures_only | boolean | no
overwrite | array | no
pattern_definitions | hash | no
patterns_dir | array | no
patterns_files_glob | string | no
tag_on_failure | array | no
tag_on_timeout | string | no
timeout_millis | number | no

## named_captures_only
默认值： `true`
意义：当该值为 `true` 时，只会从数据中捕获有 `SEMANTIC` 的信息，其他的匹配项不存储。

## overwrite
默认值：[]
意义：这个属性允许你在输出匹配结果之前，重写该匹配结果中的一项或多项，比如：

```
# 原始数据
734742416494845952,Android,"In trade, military and EVERYTHING else, it will be AMERICA FIRST! This will quickly lead to our ultimate goal: MAKE AMERICA GREAT AGAIN!",2016-05-23T13:46:57Z
# 配置文件
filter {
  grok {
    patterns_dir => ["/root/custom-patterns.txt"]
    match => { "message" => "%{ID:id},%{DEVICE:device},%{MESSAGE_BODY:message},%{TIMESTAMP_ISO8601:timestamp}" }
  }
}
# 结果1 没有overwrite选项
"message": [
"734742416494845952,Android,"In trade, military and EVERYTHING else, it will be AMERICA FIRST! This will quickly lead to our ultimate goal: MAKE AMERICA GREAT AGAIN!",2016-05-23T13:46:57Z"
,
""In trade, military and EVERYTHING else, it will be AMERICA FIRST! This will quickly lead to our ultimate goal: MAKE AMERICA GREAT AGAIN!""
]，
"host": "localhost",
"@timestamp": "2018-10-21T01:09:04.089Z",
"device": "Android",
"id": "734742416494845952",
"timestamp": "2016-05-23T13:46:57Z",
"path": "/root/trump-test.csv",
"@version": "1"

# 结果2 设置overwrite选项     overwrite => ["message"]
"message": "In trade, military and EVERYTHING else, it will be AMERICA FIRST! This will quickly lead to our ultimate goal: MAKE AMERICA GREAT AGAIN!",
"host": "localhost",
"@timestamp": "2018-10-21T01:09:04.089Z",
"device": "Android",
"id": "734742416494845952",
"timestamp": "2016-05-23T13:46:57Z",
"path": "/root/trump-test.csv",
"@version": "1"
```
可以看到，原来的 `message` 属性存储的是原始的输入信息，增加了 overwrite 选项后，则由匹配到的 message 信息将其替换。

## pattern_definitions
默认值：空
意义：可以再该项中，以键值对的方式输入 grok 表达式，也就是把patterns_dir 文件内的 pattern 写在这个属性里，比如：
```
 pattern_definitions => {
      "ID" => "\d+(?=,)"
      "DEVICE" => "[a-zA-Z]+(?=,)"
      "MESSAGE_BODY" => ".+(?=,2)"
    }
```

## timeout_millis
默认值：30000
意义：在执行了所定义的时间后，终止正则匹配，设置为0则没有超时时间。

## add_tag 
默认值：[]
意义：在匹配成功后，增加一个或多个 tag 字段

## add_field
默认值：{}
意义：在匹配成功后，增加一个或多个 field、value对，例如：

```
# 输入
依然是上文中使用的例子

# Logstash 配置文件如下
filter {
  grok {
    pattern_definitions => {
      "ID" => "\d+(?=,)"
      "DEVICE" => "[a-zA-Z]+(?=,)"
      "MESSAGE_BODY" => ".+(?=,2)"
    }
    #patterns_dir => ["/root/custom-patterns.txt"]
    match => { "message" => "%{ID:id},%{DEVICE:device},%{MESSAGE_BODY:message},%{TIMESTAMP_ISO8601:timestamp}" }
    overwrite => ["message"]
    add_tag => [ "test_%{id}"]
    add_field => {
      "test_%{timestamp}" => "test"
      }
    remove_field => ["device"]
  }
}

# 结果如下
"host": "localhost",
"timestamp": "2016-05-23T13:46:57Z",
"id": "734742416494845952",
"tags": [
"test_734742416494845952"
],
"@version": "1",
"@timestamp": "2018-10-21T01:47:31.552Z",
"message": "In trade, military and EVERYTHING else, it will be AMERICA FIRST! This will quickly lead to our ultimate goal: MAKE AMERICA GREAT AGAIN!",
"path": "/root/trump-test.csv",
"test_2016-05-23T13:46:57Z": "test"
```
# 参考
[grok manual](https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html)
