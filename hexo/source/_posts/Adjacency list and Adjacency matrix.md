date: 2016-05-12 13:38:20
title: 邻接矩阵,邻接列表一些基本概念和应用(Adjacency list and Adjacency matrix)
categories: 
- 2016-05
tags:
- DataStructure
- Algorithm
- Graph
- 数据结构
- 算法
- 图
---


### 邻接矩阵

#### 概述
 邻接矩阵,其只要是用来存储一个图结构.

 首先一个无向图如下所示
 <div align=center>
<img class="little" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/6n-graph2.svg/185px-6n-graph2.svg.png" />
</div>
<div align=center>
<img class="little" src="https://upload.wikimedia.org/math/c/a/e/caec49086f2faf102df7972d0dcde54b.png"  />
</div>

#### 性质

##### 无向图的邻接矩阵对称矩阵
由上图发现该图其实是个对称矩阵,这是由无向图的性质决定的,无向图的各个顶点之间的连线是具有相互性的,于是每有一个顶点之间的连线就要延伸出一组关系,表现在邻接矩阵上就是对称点的值相同(a12 = a21 依次类推)

##### 邻接矩阵的大小=顶点数*顶点数
很容易了解,一个图的顶点数决定了其对应邻接矩阵的大小,例如上图是个6个顶点的图,则对应的矩阵需要6*6的大小方可表达其结构

##### 数据压缩的属性
 * 由于事实上每一组顶点关系只需要一位来来表达,处理一个有4个顶点的图仅仅需要 4*4/8个=2个字节,这是很节省空间的,如果是一个无向图的话,所需要的空间则可以再减少一半的空间 4*4/16个=1个字节,通过这个方法已经接近信息论中表达一个n个顶点所需要字节数的下界.
 * 但是也存在一些问题,例如其可能需要存储那些本来不存在的边

### 邻接链表

#### 概述
邻接链表也是一种表达图的方式
假设有无向图如下所示
 <div align=center>
<img class="little" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Simple_cycle_graph.svg/120px-Simple_cycle_graph.svg.png"  align=center >
 </div>

则相对应的表示为三个list{b,c},{a,c},{a,b}
但是换出去空间的节省后,查询时间也会相应的边长
例如:查询a与b是否有关系需要首先定位到a的链表{b,c},再对这个链表进行遍历,如果没有b,也就说明对其进行了完整的遍历,浪费了较多的时间,当然可以采用快速搜索的方式进行优化


 * 事实上邻接列表还有一点是其应用在大型稀疏矩阵中,因为邻接矩阵不需要浪费空间来表达那些不存在的边缘

##### 参考:
  * [Adjacency_matrix](https://en.wikipedia.org/wiki/Adjacency_matrix)
  * [Adjacency_list](https://en.wikipedia.org/wiki/Adjacency_list)

