date : 2016-11-15 13:50:00
title : git较为完整安装与配置,解决git clone速度慢等问题(Almost perfect git configuration)
categories : 
- 2016-11
tags : 
- Git

---

## 概述(abstract)
  Github最近又开始不稳定,而在bower等安装的过程中会利用到其上面的源, 在包的下载总会因为网络或者的问题,导致中断安装,而每次重新安装的时间较长.
为了让大家不再因为此浪费时间,给大家一些可能有用的配置建议.

## 具体配置(configuration)

### 卸载和安装最新(remove and update git)
```
mkdir /root/APP
yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel -y
yum install  gcc perl-ExtUtils-MakeMaker -y
yum remove git -y
cd /home/zeppelin/prerequisites
wget https://www.kernel.org/pub/software/scm/git/git-2.10.2.tar.gz#you can look for the lastest version of git and replace 2.10.2
tar xzf git-2.10.2.tar.gz
cd git-2.10.2
make prefix=/root/APP/git all
make prefix=/root/APP/git install
echo "export PATH=$PATH:/root/APP/git/bin" >> /etc/profile
source /etc/profile
git config --global url."https://".insteadOf git://
git --version
```

### 使用较快的hosts(use faster host for github.com)
```
#add the below hosts into your /etc/hosts
192.30.253.118 gist.github.com
192.30.253.119 gist.github.com
```

 


