title: 实施NLP流水线之前干点什么
date: 2019-04-27
categories: 
- 2019-04
tags: 
 - NLP
---

## NLP流水线总览

NLP处理套路无非以下该图中描述

![](https://stanfordnlp.github.io/CoreNLP/images/AnnotationPipeline.png)

该文的重点则对所有该流水线之前的任务进行补充


## 数据本身处理

* 简繁体转换或其他同义转换(中文)
* 全角半角转换
* 不在dictionary(正则与替换对照词典)以内的单词就用UNK取代
* 可以在句首加上<BOS>，在句末加上<EOS>
* url，at，表情符号等统一替换
* 稀有词替换为 <UNK>(词频小于某一个阈值)
* 编码转换
* 小写转换
* 去除标点符号(根据具体的任务也可替换)
* 去除停用词
* 去除频现词
* 去除稀疏词
* 略缩词替换
* 错词纠正(将词替换为词典中最近的词或者<UNK>)
* 单位替换(将文本中的单位替换为统一格式如：将4kgs、4kg统一替换为4 kg，将4k替换为4000，将100或100 100或100100或100替换为100 dollar)
* 词形还原(lemmatization)
* 其他语言进行翻译(比如对于中文中的英文单词归一化为<_E_>)
* 数字归一化(比如将小于10的为<如果在之后的实体识别中需要对应的原始数字则跳过该步骤.1:NUM> 大于10<2:NUM>)


## 数据增强

* 长句截断
* dropout
* shuffle
![](https://pic3.zhimg.com/80/v2-d3aaee7f330d475a0643abd5199a1f16_hd.png)
* 文档裁减(这样我将获得更多的数据。开始的时候我尝试从文档中抽取几个句子并创建10个新文档。这些新创建的文档句子间没有逻辑关系，所以用它们训练得到的分类器性能很差。第二次，我尝试将每篇文章分成若干段，每段由文章中五个连续的句子组成。这个方法就运行得非常好，让分类器的性能提升很大)
* 文本对齐
* 同义词替换
* 回译
* 迁移学习
* GAN
* BERT


## 实体识别的分类

PERSON People, including fictional. 
NORP Nationalities or religious or political groups. 
FAC Buildings, airports, highways, bridges, etc. 
ORG Companies, agencies, institutions, etc. 
GPE Countries, cities, states. 
LOC Non-GPE locations, mountain ranges, bodies of water. 
PRODUCT Objects, vehicles, foods, etc. (Not services.) 
EVENT Named hurricanes, battles, wars, sports events, etc. 
WORK_OF_ART Titles of books, songs, etc. 
LAW Named documents made into laws. 
LANGUAGE Any named language. 
DATE Absolute or relative dates or periods. 
TIME Times smaller than a day. 
PERCENT Percentage, including ”%“. 
MONEY Monetary values, including unit. 
QUANTITY Measurements, as of weight or distance. 
ORDINAL “first”, “second”, etc. 
CARDINAL Numerals that do not fall under another type. 



#参考
[Introdecton to pipelines](https://stanfordnlp.github.io/CoreNLP/pipelines.html)
[文本数据处理的终极指南-NLP入门](https://juejin.im/entry/5aa34b10f265da2381553b87)
[使用re正则化进行文本清理](https://blog.csdn.net/wmq104/article/details/82931352)
[自然语言处理时，通常的文本清理流程是什么？](https://www.zhihu.com/question/268849350)
[中文自然语言处理时，英文单词和数字怎么处理？](https://www.zhihu.com/question/295519283)
[几千条文本库也能做机器学习！NLP小数据集训练指南](https://www.jiqizhixin.com/articles/2018-11-19-20)
[知乎“看山杯” 夺冠记](https://zhuanlan.zhihu.com/p/28923961)
