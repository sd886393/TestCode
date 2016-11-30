date : 2016-11-28 19:50:20
title : 别忘了使用Mybatis  ResultMap(Don't forget the ResultMap when you use Mybatis)
categories : 2016-11
tags : 
 - Programming
 - ORM
 - Mybatis
---

## 问题
最近需要使用Mybatis来操作MySQL,由于入门就随意的拿来一篇入门介绍和官方的教程,照着走一遍,奈何写好了Mapper,Bean,以及配置好数据库,以及对应的xml后会发现无论如何也无法得到实例化后的Bean.事实上,并没有一些bug信息,也没有抛异常.

### 解决1
判断是否是数据库本身的连接的问题.
尝试更改mybatis的数据库mysql配置 => 数据库连接没问题

### 解决2
判断是否是数据库得命令没有正常执行
查找mysql的general日志,发现没有,于是查看general的设置使用下面的命令
```
show variables like '%gener%';
```
返回下面的信息
```
+------------------+-------------------------------------------------+
| Variable_name    | Value                                           |
+------------------+-------------------------------------------------+
| general_log      | OFF                                              |
| general_log_file | /tmp/mysql/general.log |
+------------------+-------------------------------------------------+
```
打开general_log
```
set global general_log=on;
```
如果需要自定义日志的路径,同理设置不再赘述.
使用`tail -F /tmp/mysql/general.log` 监控mysql的查询信息
运行自己得mybatis 测试用例,可以看到上面的命令打印到了控制台,结果如下:
```
161129 18:01:33       15 Connect    root@192.168.1.188 on test
           15 Query    /* mysql-connector-java-5.1.38 ( Revision: fe541c166cec739c74cc727c5da96c1028b4834a ) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_buffer_length AS net_buffer_length, @@net_write_timeout AS net_write_timeout, @@query_cache_size AS query_cache_size, @@query_cache_type AS query_cache_type, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@tx_isolation AS tx_isolation, @@wait_timeout AS wait_timeout
           15 Query    SELECT @@session.autocommit
           15 Query    SET character_set_results = NULL
           15 Query    SET autocommit=1
           15 Query    SET autocommit=0
           15 Query    SELECT * FROM t_cloud_uba_vm_action WHERE C_ID = 1
           15 Query    SET autocommit=1
```
说明数据库的命令执行也没问题

### 解决3
回想起,自身项目与参考的项目唯一的不同就是Bean不同,MySQL表不同
将Bean中的属性名称完全与MySQL一一对应,运行后解决

## 总结
查看mybatis的Result_Maps文档,发现定义了一个Java Bean后,select语句会精确的匹配JavaBean中的属性,然后映射到结果集.这个就是关键所在,当然可以不完全的对应起来,不过需要 ResultMap来,使用select语句的别名,最终匹配到对应的属性上,如下所示
```
<select id="selectUsers" resultType="User">
  select
    user_id             as "id",
    user_name           as "userName",
    hashed_password     as "hashedPassword"
  from some_table
  where id = #{id}
</select>
```



## 参考
[http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html#Result_Maps](http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html#Result_Maps)
