title: 形式语法概述
date: 2018-10-16
categories: 
- 2018-10
tags: 
 - NLP
 - DataMining
 - 自然语言处理
 - 数据挖掘
---
## 背景

形式语言作为精确描述语言的工具，广泛的应用在机器翻译等自然语言处理的领域

描述语言的常用三个方法：

1. 穷举法，对于句子数目有限的语言可以使用这种方法将所有的句子进行枚举，从而定义。
2. 文法，可以理解为我们在英文、中文中学习的语法，通过严格定义规则，来生成合法的句子，**重点**在于生成句子。
3. 自动机，不同于文法，自动机**更偏向**与对于输入句子的合法性检测，从而区分哪些是语言中的句子，哪些不是。

文法与自动机二者皆有所长，一定条件下可以相互转换。

## 符号与符号串

### 字母表，符号集
字母或者符号的又穷非空集合。
例：汉语字母表为汉字、数字、标点符号。


### 符号串
字母表中的符号组成的任何有穷序列。
例：有符号集
![image](https://user-images.githubusercontent.com/7655877/46996847-9314c180-d150-11e8-9367-fca90bcc4c86.png)
符号串为：0，1，01，10，11，00


### 空字符串
![image](https://user-images.githubusercontent.com/7655877/46997037-32d24f80-d151-11e8-8fe0-88a86046bd19.png)表示，长度为0


### 符号串的头、尾、固有头、固有尾
例：如果z=abc，则
z的头为：![image](https://user-images.githubusercontent.com/7655877/46997037-32d24f80-d151-11e8-8fe0-88a86046bd19.png)，a，ab，abc
z的尾为：![image](https://user-images.githubusercontent.com/7655877/46997037-32d24f80-d151-11e8-8fe0-88a86046bd19.png)，c，bc，cba
z的固有头：![image](https://user-images.githubusercontent.com/7655877/46997037-32d24f80-d151-11e8-8fe0-88a86046bd19.png)，a，ab
z的固有尾：![image](https://user-images.githubusercontent.com/7655877/46997037-32d24f80-d151-11e8-8fe0-88a86046bd19.png)，c，bc


### 符号串连接
有符号串x，y，连接xy指y符号串写在x符号串之后
例：x=ab，y=cd，xy=abcd


### 符号串的方幂
有符号串x，z=xxxx....，称z为x的方幂，记为![image](https://user-images.githubusercontent.com/7655877/46997369-1aaf0000-d152-11e8-9fe2-15669611d07f.png)
且
![image](https://user-images.githubusercontent.com/7655877/46997436-44682700-d152-11e8-81b0-2e666a016eda.png)


### 符号串的相乘
有符号串![image](https://user-images.githubusercontent.com/7655877/46997662-d6702f80-d152-11e8-8624-c2bd89787f26.png)
例：有A={a,b} B={c,d},则AB={ac,ad,bc,bd}


### 闭包
字母表![image](https://user-images.githubusercontent.com/7655877/46999201-98c1d580-d157-11e8-9f72-e44129b97319.png)上所有又穷长的字符串的集合用![image](https://user-images.githubusercontent.com/7655877/46999213-a0817a00-d157-11e8-9da5-49f7d12b8df4.png)
来表示，其中正闭包![image](https://user-images.githubusercontent.com/7655877/46999164-7af47080-d157-11e8-82d2-88a9f3c888a5.png)不包括空集


## 文法和语言的形式定义

### 规则
也称为产生式：
![image](https://user-images.githubusercontent.com/7655877/46999289-d6bef980-d157-11e8-8607-e0407655604f.png)
其中，![image](https://user-images.githubusercontent.com/7655877/46999328-f5bd8b80-d157-11e8-9468-08b46507690a.png)


### 文法
定义：G定义为四元组![image](https://user-images.githubusercontent.com/7655877/46999397-28678400-d158-11e8-9dc1-bfbdfac5462e.png)
![image](https://user-images.githubusercontent.com/7655877/46999410-30bfbf00-d158-11e8-8c83-d1f35dc4d3d8.png)

### 句型
![image](https://user-images.githubusercontent.com/7655877/46999714-0a4e5380-d159-11e8-8a22-4cac5e52f986.png)
句子一定是句型，句型不一定是句子

### 语言
![image](https://user-images.githubusercontent.com/7655877/46999780-336ee400-d159-11e8-8067-05c802ea65bb.png)记为L(G)


## 文法类型

![image](https://user-images.githubusercontent.com/7655877/47000450-e2f88600-d15a-11e8-9f6f-2c76109410e1.png)

### 上下文无关文法
![image](https://user-images.githubusercontent.com/7655877/47001280-aa59ac00-d15c-11e8-89ba-c9a2200b1f17.png)
重点是在规则的左部只有一个非终结字符

### 正规文法
![image](https://user-images.githubusercontent.com/7655877/47001685-a0847880-d15d-11e8-87f0-fe81ca258def.png)

### 语法树
语法树表明了推倒过程中使用了什么样的产生式和用到了哪些非终结字符，并不表明顺序。
例：有下图
![image](https://user-images.githubusercontent.com/7655877/47007060-f9f2a480-d169-11e8-8f60-9e1edf81a9ad.png)
构造aabbaa的语法树，步骤如下
![image](https://user-images.githubusercontent.com/7655877/47007084-07a82a00-d16a-11e8-9aaa-8fc4df075698.png)


### 文法的二义性
![image](https://user-images.githubusercontent.com/7655877/47002932-c0696b80-d160-11e8-9759-84258745460f.png)

## 句型分析
识别一个符号串是否为某文法的句型是整个推导的构建过程

### 自上而下分析法
由非终结字符串推导至终结字符串，并查看该终结字符串是否匹配

### 自下而上分析法
有终结字符串进行规约，最终生成的非终结字符串，是否符合规则

### 回溯法
![image](https://user-images.githubusercontent.com/7655877/47004899-2657f200-d165-11e8-94fb-5463a90dd1f7.png)
这时候使用回溯法进行计算

## 简化文法
主要是去除规则中的两种类型的规则，有害规则和多与规则

### 有害规则
例如：
U->U这种产生式，会引起文法的二义性。
多余规则：指文法中任何句子的推倒都不会用到的规则，主要有两种：
1. 非终结字符不在任何规则的右部出现。
2. 非终结字符无法推出终结符号串



## 参考
宗成庆. 统计自然语言处理[M]. 清华大学出版社, 2008.
[北京大学编译原理课程](http://ccl.pku.edu.cn/ALCourse/Compiling/)

