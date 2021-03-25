以下均在MySQL Command Line Client中执行，需要先输入密码。

# 一、步骤

## 1、创建数据库

首先需要创建一个数据库。**mysql的命令行是以;结束的**

~~~mysql
CREATE DATABASE 数据库名称;
~~~

创建完后会显示

>Query OK, 1 row affected (0.02 sec)

如果不放心，可以查看当前的数据库。输入

~~~mysql
show databases;
~~~

然后就需要转换到新创建的数据库。

~~~mysql
use 需要转到的数据库;
~~~

> Database changed

## 2、创建表

创建完数据库后就需要创建table。

~~~mysql
CREATE TABLE 表的名称(表定义选项);
~~~

CREATE方式创建表的注意事项

* 表定义选项中填字段和数据类型，多个字段要用逗号隔开



这里使用现成的导入方式。将压缩包的内容解压，并复制到MySQL的uploads目录下。然后输入

~~~mysql
source C:\ProgramData\MySQL\MySQL Server 8.0\Uploads\表名称.sql
~~~

表名称例如anatomydict,结果如下，表示成功导入

>Query OK, 0 rows affected (0.00 sec)
>
>Query OK, 0 rows affected (0.00 sec)
>
>Query OK, 0 rows affected, 1 warning (0.01 sec)
>
>Query OK, 0 rows affected, 6 warnings (0.04 sec)
>
>Query OK, 0 rows affected (0.00 sec)

然后将csv数据文件导入

~~~mysql
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/anatomydict.csv' into table anatomydict CHARACTER SET utf8  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;
~~~

导入语句的说明

* load data infile 'csv文件的绝对路径'
* into table 表的名称
* character set utf8：设定编码格式
* fields terminated by  指定字段的分隔符
* optionally enclosed by ' “ '：认为双引号是独立的字段
* lines terminated by ：指定行分隔符

结果如下，表明导入成功

>Query OK, 19 rows affected, 1 warning (0.01 sec)
>Records: 19  Deleted: 0  Skipped: 0  Warnings: 1

可以打开MySQL，右键表名称-send to SQL editor-Select all Statements，然后执行SQL语句，看看数据的完整性。



# 二、可能的问题

## 1、ERROR 1290

> The MySQL server is running with the --secure-file-priv option so it cannot execute this statement



原因：MySQL导入导出时，需要使用安全目录

**查看安全目录**

~~~mysql
SHOW VARIABLES LIKE "secure_file_priv";
~~~

可以将文件复制到安全目录下，此时导入文件需要使用绝对路径

## 2、ERROR1064

sql语句中词和mysql的关键字冲突了，用 `` （tab键上方）将词括起来就好了。



# 三、其他

##  1、删除数据表



~~~mysql
DROP TABLE 表名称;
~~~



## 2、删除数据库

~~~mysql
DROP DATABASE 数据库名称;
~~~

