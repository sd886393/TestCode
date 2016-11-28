date : 2016-11-28 13:50:00
title : Logstash 多行匹配插件与解析json文件(use the multiline plugin to decode a whole json file)
categories : 
- 2016-11
tags : 
- DistributedComputing
- Logstash
- MultilinePlugin

---

## 概述(abstract)
如果希望得到配置文件请直接查看"配置(configuration)" if you just want to get the configure file of logstash,so just look at "Configuration".
最近需要通过logstah处理json格式的日志,最好的结果是配置配置Logstash中的config,用用其他轮子插件,自动化的解析.例如这样一段待分析的信息:
```
{
    "tenantslst": [
        {
            "total_memory_mb_usage": "3.2711112E-5",
            "total_vcpus_usage": "6.3888889E-8",
            "start": "2016-08-26T08:56:34.596973",
            "tenant_id": "70be30112729411dr92acda6ae5ae0215",
            "stop": "2016-08-26T08:56:34.596996",
            "total_hours": "6.388889E-8",
            "total_local_gb_usage": "6.388889E-8"
        }
    ],
    "quotalst": [ ]
}
```
最终解析得结果如下:
```
 "body" => {
        "tenantslst" => [
            [0] {
                "total_memory_mb_usage" => "3.2711112E-5",
                    "total_vcpus_usage" => "6.3888889E-8",
                                "start" => "2016-08-26T08:56:34.596973",
                            "tenant_id" => "70be30112729411dr92acda6ae5ae0215",
                                 "stop" => "2016-08-26T08:56:34.596996",
                          "total_hours" => "6.388889E-8",
                 "total_local_gb_usage" => "6.388889E-8"
            }
        ],
          "quotalst" => []
    }
```
## 问题1(trouble No.1)
在处理日志的时候会遇到这样的问题:logstash 默认会将所监视文件的每一行进行拆分,并单独的作为一个文事件于是就有一个完整的json file 被拆成了数个事件,这违背初衷,所以首先解决其自动拆分的问题.
multiline的插件就是为此而生,下面贴出multiline的简单的配置:
```
         multiline{
                pattern => "^haha"
                #negate => true
                what => "previous"
                max_age =>  5
        }

```

#### 解释1
pattern:表示组成多行的json中, 每一行都具有什么样的特征,这个特征通过正则表达式进行匹配,如果匹配成功则认定此为多行块中的一行
what:有两个值 "previous"和"next", 如果是previous,则将该行归到上面一个多行块中,如果是next,则归到下面一个代码块中
max_age:如果没有再有新行添加到多行中,那么在max_age后,这个多行块将被推送,默认是5,单位是秒

## 问题2(trouble No.2)
现在我们通过multiline插件获得了一个多行json块,剩下的就是将这个json串解析.
这个比较简单,logstash 中的filter有 json 这个插件,配置上就可以搞定,前提是之前的json必须是一个合规的json串
下面贴出其配置项
```
    json{
        source => "message"
        target => "body"
    }
```

#### 解释2
source 就是存储json串的字段,默认是message字段
最好是将该字段分析后删除,也就是使用remove_field这个选项,否则不仅分析后的数据进入到了es,原json串也进入,导致存储的数据,占用双倍的空间.
并且该选项也是支持正则表达式的.
```
filter {
  multiline {
    remove_field => [ "foo_%{somefield}" ]
  }
}
```

## 完整的配置

```
input {
    file {
        path => "/path/to/log"
        type => "some_type"
        start_position => beginning
        sincedb_path => "/dev/null"
    }
}
filter {
         multiline{
                pattern => "^\{|\s|\}"
                #negate => true
                what => "previous"
                max_age =>  5
        }
    json{
        source => "message"
        target => "body"
    }
}
```

## 参考

[Multiline Plugin](https://www.elastic.co/guide/en/logstash/current/plugins-filters-multiline.html#plugins-filters-multiline)


 

