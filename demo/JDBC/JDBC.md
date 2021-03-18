# 一、基本概念

Java DataBase Connectivity，JDBC是Java数据库连接，也就是**Java语言操作数据库**。

JDBC本质是官方(sun公司)定义的一套操作所有关系型数据库的规则，即**接口**。各个数据库厂商去实现这套接口(JDBC)，即提供了数据库驱动jar包。可以使用这套接口进行JDBC编程，**真正执行的是驱动jar包中的实现类**。

创建一个项目，然后创建一个模块com. . ,再创建一个包

# 二、快速入门

## 1、步骤

1、首先**导入jar包(mysql-connector-java-8.0.23.jar)**，jar包可以往上进行下载。可以将jar包放入JDK中，打开此电脑-属性-高级设置-环境变量-查看JAVA_HOME，找到路径，将jar包添加到路径中。

在项目中创建一个libs，并将jar包添加到libs中(方法是直接复制，点击确认即可)。添加后，**右键libs选择Add as Library**，这一步才是真正的添加。

2、注册驱动

Class.forName("com.mysql.jdbc.Driver")	驱动的名字以.隔开，并且最后要有Driver。会有异常，异常不处理，直接抛出(throws ClassNotFoundExecption)，同时扩大范围，修改为Execption。

3、**获取数据库连接对象**，与数据库进行连接

Connection conn = DriverManager.getConnection("url，也就是数据库，一般为**jdbc:mysql://localhost:3306/访问的路径", "用户名", "密码"**)



4、定义SQL语句

```
String str = "SELECT `city`.`ID`,\n" +
        "    `city`.`Name`,\n" +
        "    `city`.`CountryCode`,\n" +
        "    `city`.`District`,\n" +
        "    `city`.`Population`\n" +
        "FROM `world`.`city`";
```

5、**获取执行SQL的对象**

因为**connection只是建立了连接，不能直接执行**，需要新建一个对象用于获取SQL。

Statement stat = conn.createStatement();

6、执行SQL

查询语句用的是ResultSet，stat.execute

7、处理结果

8、**释放资源**

定义了几个statement和connection， 就释放几个

stat.close();

conn.close();



## 2、各个对象介绍

### 1、DriverManager：驱动管理对象

功能：

* 注册驱动：告诉程序该使用哪一个数据库驱动jar，因为有很多种数据库。

实际上是执行static void registerDriver(Driver driver):	注册与给定的驱动程序DriverManager

对于MySQL，使用Class.forName("com.mysqql.jdbc.Driver");

通过查看源代码可知在com.mysql.jdbc.Driver中存在静态代码块

~~~java
static{
    try{
        java.sql.DriverManager.registerDriver(new Driver());
    }catch (SQLException E){
        throw new RuntimeException("Can't register driver!");
    }
}
~~~

mysql5之后的驱动jar包可以省略注册驱动的步骤。

* 获取数据库连接

方法：static Connection getonnection(String url, String user, Srting password)

url: **jdbc:mysql://IP地址:port/数据库名称**

user和password分别代表了用户名和密码0

### 2、Connection：数据库连接对象

功能

* 获取执行sql的对象

~~~java
Statement createStatement()
PreparedStatement prepareStatement(String sql)
~~~

* 管理事务

开启事务

~~~java
setAutoCommit(boolean autoCommit)//调用该方法设置参数为False，就能够开启事务
~~~

提交事务: commit()

回滚事务: rollback()



### 3、Statement：执行SQL的对象

执行SQL

~~~java
boolean execute(String sql)	//可以执行任意的sql，了解即可
int executeUpdate(String sql)	//执行DML(insert、update、delete)语句、DDL(create、alter、drop)语句,返回值返回的是影响的行数，可以以此判断DML语句是否执行成功
ResultSet executeQuery(String sql)	//执行DQL(select)语句
~~~



### 4、ResultSet：结果集对象





### 5、PreparedStatement：执行SQL的对象

