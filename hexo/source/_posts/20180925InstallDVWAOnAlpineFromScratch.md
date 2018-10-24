title: 在alpine上，从零开始安装DVWA
date: 2018-09-25
categories: 
- 2018-09
tags: 
 - Linux
 - 信息安全
 - Security
---
# 背景

## alpine系统

目前 alpine 系统已经作为 docker 容器的默认安装系统，其最大的特点是安装的体积非常小，镜像的大小最少只有 5M ，这对于在 docker 集群中大规模使用容器的情况下，极大的降低了磁盘占用，并且其还有：

1. 因体积较小，从而提升了启动速度
2.因体积小，组件少，从而暴露面降低，最终提升系统的安全

当我们能够熟练的配置该系统之后，可以大大加快 devops 的整体流程，迅速搭建原型系统，提高资源的利用率。

## DVWA

DVWA:Damn Vulnerable Web Application

老牌的安全测试靶场，后端使用的是 PHP + MySQL 的组合，可以方便快捷的让安全从业者，对常见的安全漏洞的原理进行学习，并在实践中巩固自己的学习成果。

DVWA的官网在[这里](2)。

# 安装alpine系统

首先从 alpine 的[官网](1)，根据需要下载指定的alpine安装包，这里选择了 3.8.1 的Extend版本。

之后，可以将下载的iso文件制作成U盘启动器进行安装，这里由于演示使用 VMware Fusion 进行安装演示。

虚拟机的配置为默认配置如下：
￼
![image](https://user-images.githubusercontent.com/7655877/45987778-f5285c80-c0a5-11e8-8fbe-b63975cc7999.png)

启动后直接使用 root 账号登录，并输入 `setup-alpine` ，进入配置界面，这里的操作的目的是将目前运行在内存中的系统，配置完整后安装至实际的硬盘上，如下图所示：

![image](https://user-images.githubusercontent.com/7655877/45991497-c6b37d00-c0b7-11e8-9e4e-93541bb790f3.png)

图中可根据自己的需要配置，如键盘的布局、主机名、网卡信息、时间服务器组件、root密码、时区等，本文选择时区为Asia/Shanghai，重点是选择安装的实际物理磁盘（ sda ）以及安装的用途（ sys ）

![image](https://user-images.githubusercontent.com/7655877/45996293-48aea080-c0ce-11e8-99da-a06a52475a94.png)

安装完成后 reboot


# 安装系统必备组件

## 安装Bash、Vim

```
apk add bash vim
```

# 开启远程root登录的权限以及扩展的包镜像源

方便直接通过远程终端进行操作，打开`vim /etc/ssh/sshd_config`，在配置文件的末尾添加：

```
PermitRootLogin yes
```

重启 ssh 服务，`service sshd restart`

# 开启community testing edge包仓库

```
vim /etc/apk/repositories
```

将其中所有对应的库链接注释打开

# 安装配置 MySQL/MariaDB

## 安装MariaDB

```
apk add mariadb mariadb-client
```

## 配置MariaDB

MariaDB安装后还不能直接使用，需要创建用户等配置工作

### 设定环境变量，稍后使用

```
export DB_DATA_PATH=/var/lib/mysql
export DB_ROOT_PASS=root_password
export DB_USER=dvwa
export DB_PASS=dvwa_password
```

## 启动配置MariaDB

```
mysql_install_db --user=mysql --datadir=${DB_DATA_PATH} &&\
chown -R mysql:mysql ${DB_DATA_PATH} 
```
## 启动 MariaDB 并添加默认启动

```
rc-service mariadb start
rc-update add mariadb default
```

## 改 root 密码 

```
mysqladmin -u root password "${DB_ROOT_PASS}"
```

## 创建一个dvwa用户，以及dvwa数据库

*建议添加该用户，不建议使用 MariaDB 的 root 账户登录MariaDB*
```
echo "CREATE DATABASE dvwa;" >> /tmp/sql
echo "GRANT ALL ON *.* TO ${DB_USER}@'127.0.0.1' IDENTIFIED BY '${DB_PASS}' WITH GRANT OPTION;" > /tmp/sql
echo "GRANT ALL ON *.* TO ${DB_USER}@'localhost' IDENTIFIED BY '${DB_PASS}' WITH GRANT OPTION;" >> /tmp/sql
echo "GRANT ALL ON *.* TO ${DB_USER}@'::1' IDENTIFIED BY '${DB_PASS}' WITH GRANT OPTION;" >> /tmp/sql
echo "DELETE FROM mysql.user WHERE User='';" >> /tmp/sql
echo "DROP DATABASE test;" >> /tmp/sql
echo "FLUSH PRIVILEGES;" >> /tmp/sql
cat /tmp/sql | mysql -u root --password="${DB_ROOT_PASS}"
```

## 重启主机，验证是否安装完成

```
reboot
ps -ef | grep mysql
```

# 安装PHP 以及所需要的库组件

```
apk add lighttpd php5-common php5-iconv php5-json php5-gd php5-curl php5-xml php5-pgsql php5-mysql php5-imap php5-cgi fcgi php5-pdo php5-pdo_pgsql  php5-pdo_mysql  php5-soap php5-xmlrpc php5-posix php5-mcrypt php5-gettext php5-ldap php5-ctype php5-dom
```

# 安装配置 lighttp

```
vim /etc/lighttpd/lighttpd.conf
```
取消掉注释`include "mod_fastcgi.conf"`

```
vim /etc/lighttpd/mod_fastcgi.conf
```
替换`/usr/bin/php-cgi` 为 `/usr/bin/php-cgi5`

打开对应的选项，便于漏洞学习
```
vim /etc/php5/php.ini
```
找到`allow_url_open` 和 `allow_url_include` 均设置为 `On`


启动Lighttpd
```
rc-service lighttpd start && rc-update add lighttpd default
```

# 安装配置 DVWA

## 创建项目文件夹
```
mkdir -p /usr/share/webapps/
```

## 下载DVWA
```
cd /usr/share/webapps/
wget https://github.com/RandomStorm/DVWA/archive/v1.9.zip
```

## 将DVWA放到指定的web项目目录中
```
unzip v1.9.zip
rm v1.9.zip
chmod -R 777 /usr/share/webapps/
ln -s /usr/share/webapps/DVWA-1.9/ /var/www/localhost/htdocs/dvwa
```

## 配置DVWA
```
vim /usr/share/webapps/DVWA-1.9/config/config.inc.php
```
设置用户名 密码 以及端口号

![image](https://user-images.githubusercontent.com/7655877/46000851-53703200-c0dc-11e8-91ad-9afe94ab15fb.png)


## 验证安装情况
打开`http://IP/dvwa/setup.php`
查看 Setup Check  各项应均为绿色

![image](https://user-images.githubusercontent.com/7655877/46001168-3b4ce280-c0dd-11e8-902d-f4516d0f6439.png)

点击Create/Reset Database，创建对应的数据库，安装完成



# 参考
[https://wiki.alpinelinux.org/wiki/Damn_Vulnerable_Web_Application_(DVWA)](https://wiki.alpinelinux.org/wiki/Damn_Vulnerable_Web_Application_(DVWA))
[https://wiki.alpinelinux.org/wiki/MariaDB](https://wiki.alpinelinux.org/wiki/MariaDB)

[1]:https://www.alpinelinux.org/
[2]:http://www.dvwa.co.uk/

