title: Gitlab 入门与 工作流
date: 2019-03-15
categories: 
- 2019-03
tags: 
 - SoftwareEngineering
---
# GitLab 入门

## 我不想再尴尬下去了

当你在照镜子时

![](http://ww1.sinaimg.cn/large/007yzqYtly1g12df7ykf1j308c06ymy2.jpg)

当你觉得在正常地开发用户的需求时

![](http://ww1.sinaimg.cn/large/007yzqYtly1g12dcp8yrdj30hs0e27am.jpg)

当你作为一个萌新准备接手别人的代码时

![](http://ww1.sinaimg.cn/large/007yzqYtly1g12dk9hvifj312l0qrb29.jpg)

当你在准备跟用户进行展示你开发的产品时

![](https://media.giphy.com/media/t507tv4kA94B2/giphy.gif)

当你在咆哮这段恶心的代码是谁写的时候

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12laay4fpg308c0544qp.gif)

当你准备跟甲方好好唠嗑的时候

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12l7ofivag30dw05qhdu.gif)

## 上面到底是什么问题？

1. 软件开发进度难以预测
2. 用户对产品功能难以满足
3. 软件产品质量无法保证
4. 软件产品难以维护
5. 软件缺少适当的文档资料

归根结底是因为我们使用了一种小作坊式/游击队式地开发方式

## 从游击队转向正规军

以前在游击队里面人们在无数次的管理混乱导致项目搁浅（钱没了，什么都没做出来）的教训下，逐渐摸索出了一些“办事流程”，这些办事流程在实践中被证明效果还不错，被广泛采用，借以提高项目的成功率，以及**降低掉头发的速率**。

技术思维得有，工程思维也少不了

工程思维的起点是流程。流程的背后是科学，以既定的步骤、阶段性的输入/输出去完成价值创造，通过过程控制确保最终结果让人满意。

## 为什么使用GitLab搞这一套东西

GitLab 本身是一个工具,是一个让大家达成共识的工具，帮助各位进行工程化地软件开发与管理，最终目的是**拯救程序员们日益稀少的头发**。

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12rtguxtxj30ku0kuqhj.jpg)

## GitLab中的组织结构

主要使用group subgroup project的方式进行组织,一个典型的人员组织结构如下所示:

- Organization Group - GitLab
   - Category Subgroup - Marketing
     - (project) Design
     - (project) General
   - Category Subgroup - Software
     - (project) GitLab CE
     - (project) GitLab EE
     - (project) Omnibus GitLab
     - (project) GitLab Runner
     - (project) GitLab Pages daemon


group的作用是让团队人员可以一次性授权并访问多个项目

再比如我现在建立的一个示范性的
![](https://ws1.sinaimg.cn/large/007yzqYtly1g12rgllc7kj323o175qer.jpg)

## team

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12s0x6k8oj319v0la0zn.jpg)

- 产品/用研user_research/UR
- 用户体验user_experience/UE
- 用户界面user_interface/UI
- 开发develop/DEV
- 质量quality_assurance/QA
- 运维operation_maintenance/OP
- 文档documentation/DOC
- 开发经理pproject_manager/PM

## 迭代时间的长度

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12s98kdwej30lo0drmyy.jpg)

sprint:译作短跑,指一个迭代,一般包括几个功能和bug修复,开发周期为2-4周

milestone:译作里程碑,为了便于大家进行回顾是否偏离的航道或者进度,一般为3个sprint,也就是之后的一个季度

release:一般是一个产品的deadline

## 从点子到产品

![](https://ws1.sinaimg.cn/large/007yzqYtly1g12se1elqsj30mr0ap44h.jpg)

- IDEA： 每一个从点子开始的项目，通常来源于一次闲聊。在这个阶段，项目组内的所有人对需求提出自己的想法.
- ISSUE： 最有效的讨论一个点子的方法，就是为这个点子建立一个工单讨论。你的团队和你的合作伙伴可以在 工单追踪器issue tracker 中帮助你去提升这个点子
- PLAN： 一旦讨论得到一致的同意，就是开始编码的时候了。首先，我们需要优先考虑组织我们的工作流。对于此，我们可以使用 工单看板Issue Board。
- CODE： 现在，当一切准备就绪，我们可以开始写代码了。
- COMMIT： 当我们为我们的初步成果欢呼的时候，我们就可以在版本控制下，提交代码到功能分支了。
- TEST： 通过 GitLab CI，我们可以运行脚本来构建和测试我们的应用。
- REVIEW： 一旦脚本成功运行，我们测试和构建成功，我们就可以进行 代码复审code review 以及批准。
- STAGING：： 现在是时候将我们的代码部署到演示环境来检查一下，看看是否一切就像我们预估的那样顺畅——或者我们可能仍然需要修改。
- PRODUCTION： 当一切都如预期，就是部署到生产环境的时候了！
- FEEDBACK： 现在是时候返回去看我们项目中需要提升的部分了。我们使用周期分析 Cycle Analytics来对当前项目中关键的部分进行的反馈。

## 为什么说要使用一个issue开始一切

- 在issue中,通过规范化的模板,项目中的任何人可以知道这个需求或者bug的任何详细信息和讨论以及历史
- 通过issue中的文档信息,开发者与项目经理可以进行实时交流某一个feature的最新设计思路,这个可以作为一份实时的共享编辑文档
- 使用issue的label功能可以实现工作流\时间估计等较复杂的功能

### issue本身需要包含哪些东西

一个 feature类型 issue基本上长成这个样子

```markdown
## 概述
<!--- 说明该变更主要完成了什么事情 -->

## feature ID
<!--- 对应在某个项目中的需求文档中的需求ID比如 AD-100 -->

## 其他相关feature和bug链接
<!--- 为需要变更的模块列表 -->
<!--- 别忘了前面的# -->
* #XX1
* #XX2


## 估计花费的时间和实际花费的时间

/estimate 1mo 1w 1d 1h 1m

/spend 1mo 1w 1d 1h 1m
 
## 更新的具体类型
<!--- 请在指定的括号中填入'x'-->
* [ ] 非影响各个接口输出的功能更新（单纯的增加功能，不变更已有的功能）
* [ ] 影响接口的功能更新（已有的部分功能会失效）

## 检查项
* [ ]  ~"team-DEV" 代码符合本项目的代码风格
* [ ]  ~"team-DEV" 单元测试通过
* [ ]  ~"team-DEV" 针对于该功能的概要、详细等设计文档进行更新
* [ ]  ~"team-QA"  测试用例通过
* [ ]  ~"team-QA"  集成测试通过
* [ ]  ~"team-QA"  针对于该功能的相关测试文档进行更新


## 修改的功能
<!--- 说明如何进行修改的 -->
<!--- 功能列表可以作为子任务列表 -->
<!--- 如果完成了指定的功能,请在指定的括号中填入'x'-->
* [ ] 功能1
* [ ] 功能2

## 添加的功能
<!--- 说明如何进行添加的 -->
* [ ] 功能1
* [ ] 功能2

## 删除的功能
<!--- 说明如何进行添加的 -->
* [ ] 功能1
* [ ] 功能2

## 如何对该功能进行验证
<!--- 包括测试环境，如何运行测试 -->

```

### issue的type

- feature:开发功能点
- bug: 软件缺陷
- tech debt:不改变功能的情况下增强产品性能
- ux debt:用户体验需要提升

### 对issues进行plan

plan说是计划，实则是沟通

![](https://ws1.sinaimg.cn/large/007yzqYtly1g136hkdncdj30cg04saa1.jpg)

![](https://ws1.sinaimg.cn/large/007yzqYtly1g1360hafquj30yt04s74k.jpg)

- process-needs confirmed
- process-reject(拒绝)
- process-doing

### 优先级

优先级的意义是便于在同时处理大量需求的时候有一个计划。

![](https://ws1.sinaimg.cn/large/007yzqYtly1g135z5zvv8j30z50613yy.jpg)

- P1 特高级 目前的sprint搞定
- P2 高级 下个sprint搞定
- P3 中级 目前的里程碑再搞定
- P4 低级 下个里程碑再搞定

### 严重性

- S1 完蛋 系统崩溃/数据丢失/数据泄漏/coredump 没有数据可用性
- S2 严重 无法在前台查询数据,但是数据库完整 部分数据可用性丢失
- S3 中级 前台显示错位 数据无丢失可用性受损
- S4 初级 前台颜色显示错误 数据无丢失 可用性也无受损


## issue board，工作看板

我们做了这么多准备，都是为了这个，让整个项目组成员清晰的了解到，目前的计划与进度，加快信息的传递

![](https://ws1.sinaimg.cn/large/007yzqYtly1g136mkcyttj30t10glk0v.jpg)

正儿八经的issue应该有三个标签、责任人、截止时间：
- process-*
- 优先级
- 团队，以团队为基础建立起来的看板可以通过拖拽自动加上团队标签


## FQA

- 问：能不能想svn那样单独的文件夹设置权限？

  - 答：不可以,同一个repo的每个文件夹的权限是一致,如果想根据人来设置请使用两个repo

- 问：分支权限可以做什么？
   - 答：只允许部分人进行merge、只允许部分人进行push。


## 参考
[程序员的那些事儿]()

[奶头乐维基百科](https://zh.wikipedia.org/zh/%E5%A5%B6%E5%A4%B4%E4%B9%90)

[GitLab工作流概览](https://www.cnhzz.com/gitlab-%E5%B7%A5%E4%BD%9C%E6%B5%81%E6%A6%82%E8%A7%88/)

[GitLab Workflow: An Overview](https://about.gitlab.com/2016/10/25/gitlab-workflow-an-overview/#gitlab-workflow-use-case-scenario)

[Always start with an issue ](https://about.gitlab.com/2016/03/03/start-with-an-issue/)

[scrum-sprint-vs-milestone-vs-release](https://pm.stackexchange.com/questions/22510/scrum-sprint-vs-milestone-vs-release)

[我们是怎么scrum](https://www.cnblogs.com/baiyanhuang/archive/2010/11/29/1890728.html)

[別再傻傻分不清，究竟PM, UX, UI, Web designer, Front-End Developer的專業差在哪？](https://medium.com/@shaky.girl/%E5%88%A5%E5%86%8D%E5%82%BB%E5%82%BB%E5%88%86%E4%B8%8D%E6%B8%85-%E7%A9%B6%E7%AB%9Fpm-ux-ui-web-designer-front-end-developer%E7%9A%84%E5%B0%88%E6%A5%AD%E5%B7%AE%E5%9C%A8%E5%93%AA-b8206dd49d32)