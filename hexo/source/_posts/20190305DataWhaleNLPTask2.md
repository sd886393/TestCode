title: 常见的分词方法与文本向量化
date: 2019-03-05
categories: 
- 2019-03
tags: 
 - NLP
---
[toc]

##  概述
文本分词作为自然语言处理(NLP)的基本任务之一，是很多上层任务（命名实体识别、情感分析、自动文摘等）的基础，那么从事相关行业的人员自然需要对其中涉及到的一些概念做进一步的了解，本文会从目前主流的分词算法、分词难点等展开进行简要的说明。


分词理论主要包含三个部分：分词算法、中文分词消歧、未登录词识别，而分词算法又包括词典分词、理解分词、统计分词、组合分词等几大类。下面的说明重点就是基于分词算法。


##  1 词典分词算法

基于词典的分词核心要确定两个内容：分词的算法与词典的结构，其中主要使用的集中基于词典的方法有正向最大匹配、逆向最大匹配、双向最大匹配、最少切分等。

### 1.1 前向最大匹配算法、后向最大匹配、双向匹配、最小切分
前向最大切词，是以可变滑动窗口对文本进行顺序取词，若改词在词典中存在，则进行一次切分；否则，缩小窗口大小，继续取词与词典库进行搜索，知道窗口词长为1。后向切词原理相似，只不过是从后面开始进行窗口滑动。

```
ustring = string_need_to_be_segmented
while :
if sentence_len < word_max_len:
    word_max_len = sentence_len
for i in range(word_max_len, 0, -1):
    if ustring[:i] in word_set or i == 1:
        wordList.append(ustring[:i])
        ustring = ustring[i:]
        break
    else:
        i -= 1
return wordList
```

后向匹配与前向类似只不过方向从后往前进行匹配，双向匹配利用了前后两方面的信息,并从中选择词数最少的作为分词依据。

最小切分方法使用这样一条原则即:"每一句中切出的词数最小"

基于这种匹配的方法有这样的优点：
1. 程序简单易行，开发周期短；
2. 没有任何复杂计算，分词速度快；

缺点有：
1. 不能处理歧义；
2. 不能识别新词；
3. 分词准确率不高，不能满足实际的需要；

## 2 基于统计的分词算法
### 2.1 NGram
基于N-gram语言模型的方法是一个典型的生成式模型，早期很多统计分词均是以它为基本模型，然后配合其他未登录词识别模块进行扩展。其基本思想是：首先根据词典对句子进行简单匹配，找出所有可能的词典词，然后将它们和所有单个字作为结点，构造n元切分词图，图中的结点表示可能的此候选，边表示路径，边上的n元概率表示代价，最后利用相关搜索算法从中找到代价最小的路径作为最后的分词结果。

假设随机变量S为一个汉字序列，W是S上所有可能切分路径，对于分词，实际上就是求解使条件概率P(W|S)最大的切分路径W，即:
W=argmaxWP(W|s)

根据贝叶斯公式：
W=argmaxWP(W)P(S|W)P(S)

由于P(S)为归一化因子，P(S|W)恒为1，因此只需要求解P(W)。P(W)使用N-gram语言模型建模，定义如下(以Bi-gram为例)：
P(W)=P(w1w2…wT)=P(w1)P(w2|w1)…P(wT|wT−1)

这样，各种切分路径的好坏程度(条件概率P(W|S))可以求解。简单的，可以根据DAG枚举全路径，暴力求解最优路径；也可以使用动态规划的方法的求解，jieba分词中不带HMM新词发现的分词，就是DAG+Uni-gram语言模型+后向动态规划的方式进行求解的


## 3 词袋模型与文本向量化

### 3.1 词袋模型
Bag-of-words model (BoW model) 最早出现在自然语言处理（Natural Language Processing）和信息检索（Information Retrieval）领域.。该模型忽略掉文本的语法和语序等要素，将其仅仅看作是若干个词汇的集合，文档中每个单词的出现都是独立的。BoW使用一组无序的单词(words)来表达一段文字或一个文档.。近年来，BoW模型被广泛应用于计算机视觉中。

基于文本的BoW模型的一个简单例子如下：

首先给出两个简单的文本文档如下：

        John likes to watch movies. Mary likes too.

        John also likes to watch football games.

基于上述两个文档中出现的单词，构建如下一个词典 (dictionary)：

       {"John": 1, "likes": 2,"to": 3, "watch": 4, "movies": 5,"also": 6, "football": 7, "games": 8,"Mary": 9, "too": 10}

上面的词典中包含10个单词, 每个单词有唯一的索引, 那么每个文本我们可以使用一个10维的向量来表示。如下：
       [1, 2, 1, 1, 1, 0, 0, 0, 1, 1]
       [1, 1,1, 1, 0, 1, 1, 1, 0, 0]

该向量与原来文本中单词出现的顺序没有关系，而是词典中每个单词在文本中出现的频率。

一个bow与ngram的python例子:
```
#!/usr/bin/env python3

from __future__ import print_function

from collections import deque
from itertools import islice
from itertools import tee

try:
    from itertools import izip as zip
except ImportError:
    pass

# 
def ngram(iterable, n=2):
    """s -> (s0,s1), (s1,s2), (s2, s3), ..."""
    assert n > 0, 'Cannot create negative n-grams.'
    l = tee(iterable, n)
    for i, s in enumerate(l):
        for _ in range(i):
            next(s, None)
    return zip(*l)


def ngram_generator(words, n=2):
    "s -> (s0,s1), (s1,s2), (s2, s3), ..."
    assert n > 0, "n is not in (0,inf)"
    for i in range(len(words)-n+1):
        yield tuple(words[i:i+n])

def cbow(iterable, window=1):
    "s -> ((s0,s2), s1), ((s1,s3), s2), ((s2, s4), s3), ..."
    context = [consume(s, i) for i, s in enumerate(tee(iterable, 2*window+1))]
    target = context[window]
    del context[window]
    return zip(zip(*context), target)

```


## 参考 
[奉国和, 郑伟. 国内中文自动分词技术研究综述. 图书情报工作, 2011, 54(02): 41-45.](http://www.lis.ac.cn/CN/article/downloadArticleFile.do?attachType=PDF&id=11361)
[自然语言处理综述]()
[结巴分词](https://github.com/fxsjy/jieba)
[谈分词算法（2）基于词典的分词方法](http://www.cnblogs.com/xlturing/p/8467021.html)
[中文分词算法简介](https://zhuanlan.zhihu.com/p/33261835)
[BoW（词袋）模型详细介绍](https://blog.csdn.net/u010213393/article/details/40987945 )
[自然语言处理基础-中文分词](https://ilewseu.github.io/2018/06/16/%E4%B8%AD%E6%96%87%E5%88%86%E8%AF%8D/)