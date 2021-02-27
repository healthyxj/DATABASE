# 一、概述

数据库可以用于存储数据、检索数据和生成新数据。数据表，一种数据表对应一种类型的数据；例如生成顾客表和订单表。

数据表是由行和列组成的二维表。<b>行称为记录，表中的数据是按行存储的</b>。列称为字段，使用时不允许字段为空值。

主键，是用于处理信息冗余的。将冗余的信息提取到一个新的表中，创建主键用于指导冗余的数据。关联到其他表主键的字段称为外键。

现实世界中客观对象的抽象过程

建模(由数据库设计人员完成)转变为机器世界中的数据。

| 现实世界 | 信息世界 | 数据世界 |
| :------: | :------: | :------: |
|  事物类  |  实体集  |   文件   |
|   事物   |   实体   |   记录   |
|   性质   |   属性   |  数据项  |

有现实世界转变为信息世界为第一类模型，称为概念模型；由信息世界转变为数据世界是第二类模型，称为数据模型。



数据模型的组成要素

* 数据结构：一个模型中有什么样的对象，对象的内容是什么
* 数据操作
  * 包括查询(最常用)和更新
* 完整性约束条件（一组规则）
  * 实体完整性（<b>不能存在完全相同的记录，每条记录有不重复的主键，主键不能为空值</b>）
  * 域完整性（输入数据的范围要有效 ）
  * 参照完整性（当一个表引用了另一个表中的某些数据时，相关字段的值要保持一致，通俗地说，外表中的外键值必须是主表中存在的值）



概念模型的介绍

实体：具体的人、事或抽象的概念，实体是用矩形框表示，并且要写上具体的名字。

属性：实体具有的具体的特性。

联系包括实体内部的联系（组成实体的各属性之间），也包括实体之间的联系。

实体-联系方法(E-R方法)：将实体、属性、联系的方法整合起来描述现实世界。<b>实体-矩形框；属性-椭圆；联系-菱形。</b>

关系模型

用二维表格表示一个实体集，就是关系模型。一个表格对应一个关系。

关系术语

关系名：表名

关系模式：表格的表头

元组：表中的一行

分量：元组中的每一个属性值

属性：表中的一列

属性名：给表中的属性起个名称就是属性名

将E-R图转化为关系模型的转化原则：确保每个实体转化为一个关系；将每个联系也转化为一个关系；

将一个实体的属性值添加到另一个实体or<b>将少端实体的主键添加到多端实体的主键中</b>。

# 二、关系操作

关系操作包括

* 集合运算(针对集合)
  * 交，指求交集，∩
  * 并，指求并集，∪
  * 差，求差集，如R-S，表示属于R但不属于S的元素
  * 笛卡尔积，将两个表格中的所有行排列组合的方法，类似于矢量的乘法
* 关系运算(针对表)
  * 选择，σ，<b>从行的角度</b>对原表进行操作
  * 投影，Π，<b>从列的角度</b>对原表进行操作
  * 除，从行和列的角度进行运算。从“被除表格”中调取“除表格”包含的所有行，然后再除去“除表格“中的内容
  * 连接
    * 内连接(inner join)，类似于交集
    * 外连接(包括左外连接、右外连接、全外连接)，类似于并集，左外连接是三部分中最左边的部分；全外连接是并集的部分
    * 交叉连接

补充：

选择 σ条件(关系名)；外连接：把舍弃的元组也保存在结果关系中，在其他属性上填空值(null)

 

# 三、基础查询
## 1、SQL简介
<b>数据库由数据库管理系统(DBMS)操纵和管理，用户通过数据库管理系统访问数据库中的数据。数据库管理的是表，数据以表格的形式存储在数据库中。数据库管理员(DBA)管理数据库，通过SQL语言管理数据库。</b>

SQL的特点

* 不是某个数据库专有的语言，学完SQL后可以与几乎所有的数据库打交道
* SQL简洁，完成核心功能只用了9个动词
* SQL可以进行非常复杂和高级的数据库操作

## 2、SQL语言

SQL语言包括三部分

* 数据定义语言 DDL，用于创建表、视图、索引
* 数据操作语言 DML，用于<b>增、删、查、改</b>
* 数据控制语言 DCL，用于分配权限

各个语言的关键词

1、数据定义语言 DDL

|          |      操作       |      方       |     式      |
| :------: | :-------------: | :-----------: | :---------: |
| 操作对象 |      创建       |     删除      |    修改     |
|  数据库  | CREATE DATABASE | DROP DATABASE |             |
|    表    |  CREATE TABLE   |  DROP TABLE   | ALTER TABLE |
|   索引   |  CREATE INDEX   |  DROP INDEX   |             |
|   视图   |   CREATE VIEW   |   DROP VIEW   |             |

2、数据操作语言 DML

|    --    | 操作方式 | 操作方式 | 操作方式 | 操作方式 |
| :------: | :------: | :------: | :------: | :------: |
| 操作对象 |   增加   |   删除   |   修改   |   查询   |
|   记录   |  insert  |  delete  |  update  |  select  |

3、数据控制语言DCL

|    --    | 操作方式 | 操作方式 |
| :------: | :------: | :------: |
| 操作对象 | 增加权限 | 删除权限 |
|  数据库  |  grant   |  revoke  |

## 3、SQL语法
增

insert

* 操作：插入单行数据
* 语法：insert into 表名 (列名) values (列值)
* 案例：insert into Students (姓名，性别，出生日期) values ('王伟华','男',1983/6/15)，表示增加了一行，王伟华，性别男，出生日期是……

insert,select

* 操作：使用insert,select语句将现有表中的数据添加到已有的新表中
* 语法：insert into 已有的新表 (列名) /n select 原表列名 from 原表名
* 案例：insert into addressList(''姓名'',''地址'',"电子邮件") /n select name,address,email from Students

删

delete

* 操作：使用delete删除表中某行数据
* 语法：delete from 表名 where 删除条件
* 案例：delete from a where name='王伟华' ，表示删除表a中<b>列值为王伟华</b>的行

truncate

* 操作：使用truncate table删除表
* 语法：truncate  table 表名
* 案例：truncate table addressList

改

update

* 操作：使用update更新修改数据
* 语法：update 表名 set 列名=更新值 where 更新条件
* 案例：update addressList set 年龄=18 where 姓名=’王伟华‘

查 

select

* 操作：使用select查询数据，查询多个列要用逗号将列与列隔开（顺序不重要）
* 语法：select 列名 from 表名
* 案例：select city from students
* select * from 表名称，查询所有列

注意事项：select *要尽量避免使用，防止资源的浪费；select student.姓名,select student.性别 from student可以简化为select 姓名，性别 from student

select完整语句：select 列名称 [into 新表名] from 表 [where 查询条件] [group by 分组表达式] [having 分组条件] [order by 列名[asc|desc]]

## 4、SQL具体用法
1、使用[计算列]

fruit

| id   | name  | price |
| ---- | ----- | ----- |
| 1    | apple | 3     |
| 2    | pear  | 4     |

a、对fruit表中的所有价格(price)进行求和

select sum(price) from fruit

查询结果是7.0

b、查询fruit表中字段价格的个数

select count(price) from fruit

查询结果是2，有两种价格

c、查询价格提升50%后的信息

select price*1.05 from fruit

商品表

| 商品编号 | 商品名称 | 进货数量 | 销售数量 | 商品进价 | 商品单价 |
| -------- | -------- | -------- | -------- | -------- | -------- |
| 1        | 水杯     | 50       | 35       | 5        | 8        |
| 2        | 毛巾     | 55       | 24       | 4        | 6        |
| 3        | 红茶     | 45       | 33       | 3        | 5        |

a、计算所有商品的库存数量并显示

select (进货数量-销售数量) as 库存数量 from 商品表

as后面的语句是对计算列取别名

b、计算所有 商品的利润

select (商品单价-商品进价)*销售数量 as 利润 from 商品表


2、distinct去除重复值
<b>distinct返回唯一不同的值</b>

select distinct 列名称 from 表名称

distinct可以作用于单列

学生表

| 学号 | 姓名   | 性别 | 家庭住址 | 班级 |
| ---- | ------ | ---- | -------- | ---- |
| 1    | 朱旭阳 | 男   | 济南     | 4    |
| 2    | 李宁   | 女   | 烟台     | 3    |
| 3    | 王宏伟 | 男   | 潍坊     | 1    |
| 4    | 张贵祥 | 男   | 菏泽     | 2    |
| 5    | 姚远   | 男   | 临沂     | 4    |
| 6    | 何凝   | 男   | 淄博     | 4    |
| 7    | 赵无极 | 男   | 东营     | 3    |

select 班级 from student得到的是4 3 1 2 4 4 3.要去除重复的值，可以是select distinct 班级 from student

distinct可以作用于多列，且必须放在开头的位置

3、where子句

<b>where用于提取满足标准的行或列</b>，格式：select 列名称 from 表1，表2…… where<条件表达式1，条件表达式2>

找到姓赵的学生的基本信息

select 姓名，性别，班级 from student where left(姓名.1)='赵' 

检索出4班的男生信息

select * from student where 班级='4'and性别='男'	要使用逻辑运算符and来求并列的信息

4、范围运算符

between and

查询年龄在20-23岁之间的学生的姓名、性别和年龄

select sname,sdept,sage from student where sage between 20 and 23 

列表运算符

in

5、模糊匹配运算符

语法 

select 字段 from 表 where 某字段 like 条件

[not] like %	<b>%表示任意0个或多个字符</b>，可以匹配任意类型和长度的字符。%也是通配符

查询所有姓刘学生的姓名、学号和性别(姓名可以是两、三、四个字)

select sname,sno,ssex from student where sname like '刘%'

查询以刚结尾的学生的姓名、学号和性别

select sname,sno,ssex from student where like '%刚'

[not] like _	<b>_表示任意单个字符</b>，用于限制表达式的字符长度

查询以小刚结尾的所有三个字名字的学生

select sname from student where sname like '_小刚'

[not] like []	[]表示指定范围或集合内的任意单个字符，例如[a-f],[abcdef]表示匹配对象为他们中的任意一个

select * from student where name like '[张李王]三'

找出学生表中的名字为张三、李三、王三的所有内容

select * from [user] where name like '老[1-9]'

找出老1、老2、……老9

[not] like [^]	^找出不在括号内的单个字符。

6、空值运算符

is not NULL

多个查询条件用逻辑运算符and或者or

order by对表的结果进行排序

order by 列名 [asc/desc] asc是升序，desc是降序

7、对单个字段进行升序排列

order by 列名 asc

查询学生信息，并按学号进行升序排序

select * from student order by 学号	<b>默认升序排序可以省略</b>，无需加关键字，降序只需在最后增加desc

查询选修了三号课程的学生的学号及成绩

select sno,sgrade from student where cno='3' order by grade desc

查询所有学生信息，并按照学生的年龄从小到大排序

select * from student order by 出生日期 desc

对两个字段进行排序，

order by A, B	默认都是按照升序排列

order by A desc, B	A降序，B升序

order by A, B desc	A升序, B降序

order by A desc, B desc	都用降序排列

升序排列在默认情况下，null值排在后面，对于降序排序，null则排在最前面。可以把null值看成是无穷大的数

8、使用top关键字

a、<b>返回确定数目的记录的个数</b>

select top n 列名 from 表名	top n表示返回最前面的n行（整数n表示返回的行数）

查询学生表中前20名学生的姓名

步骤是查询学生表中所有学生的姓名 select name from student。然后加入关键字top 20，select top 20 name from student

b、返回结果集中指定百分比的记录数

select top n perecent 列名 from 表名	top n percent用百分比返回的行数

<b>top关键字结合where子句实现一些功能</b>

查询年龄大于23岁的前20名学生的信息

select top 20 * from student where sage>23

查询年龄较大的前20名学生的信息

先执行order by子句，再执行top子句

select top 20 * from student order by sage desc



SQL对大小写不敏感，对立的增加一个not即可
~~~
查询是所有的关键
~~~









# 四、函数

常用的包括数学函数、字符函数、日期与时间函数、系统函数

## 1、数学函数
数学函数主要用于处理数值数据

返回绝对值：Abs(数学表达式)

计算-10的绝对值并返回 select Abs(-10)

取最大值：MAX(列名)，最小值MIN(列名)

求和:Sum(列名)

求平均值：Avg(列名)

向上取整函数：ceiling(数值表达式)	返回大于等于自变量的<b>最小整数</b>

向下取整函数：floor(数值表达式)	返回小于等于自变量的最大整数

四舍五入函数：round(参数1，参数2)根据参数2指定精度，对参数1进行四舍五入，参数2默认为0

幂运算函数：power(底数，指数)

圆周率函数：pi()	括号内不需要参数，用于求圆周率。select pi() 返回3.14159……

求平方：square(数值)

开方运算函数：sqrt(数值)

求以e为底的幂次方： exp(数值)=e^(数值)

## 2、字符函数

求字符长度：len(字符串)	字符串可以是汉字、英文字母和空格

返回最左边给定长度的字符串：left(字符串，长度n)

select Left('abcd',2)	ab

返回最右边给定长度的字符串：right(字符串，长度n)

截取字符串中的一部分：substring(字符，开始，长度)	开始必须要填，长度可以选择填

select Substring('abcdef',3,2)	cd

小写转换为大写：upper(字符串)	<b>不改变原来的形式，只是改变了显示的形式</b>

大写转小写：Lower(字符串)

插入空格：space(数值)	

select 'a'+space(5)+'b'	结果是a     b

重复函数：replicate(字符，数值)	

字符替换函数：stuff(字符1，起始位置，长度，字符2) 	从字符1的开始位置删除指定的字符个数

select Stuff('abcdef',3,2,'qp')	结果是adqpef

去掉左右两侧的空格：trim(字符串)	Rtrim去掉右侧的空格，Ltrim去掉左侧的空格

颠倒字符的顺序：reverse(字符串)

返回字符1在字符2中的位置：charindex(字符1，字符2)

将数值转为字符串：str(数值)

## 3、常用日期函数

getdate()：返回当前系统的日期、时间

select getdate() as CurrentDateTime 对返回的时间取别名

datepart(指定部分，给定日期):返回给定日期的指定部分

select datepart(year,'2018-10-15')	结果是2018	指定部分可以是week(第几周)，weekday(一周的第几天)

day(指定日期)：返回月中的第几天	select day(Getdate())

month():返回指定日期的月份

year():返回给定日期的年份

dateadd():向指定日期添加或减去指定的时间间隔	dateadd(datepart,number,date)	datepart是日期，number是时间间隔，date是日期表达式

向2018-10-15加上2天，1个月，减去1周

select dateadd(day,2,'2018-10-15')	select dateadd(week,1,'2018-10-15')	select dateadd(week,-1,'2018-10-15')

查询目前时间最近三天的内容

select * from table where time between dateadd(day,-3,getdate()) and Getdate()

datediff(datepart,startpart,endpart)	datepart返回两个日期的差值，startdate是开始日期

返回两个日期相差的天数 select datediff(month,'2016-10-11','2018-12-15')

datename(指定部分，给定日期)	

select datename(weekday,'2018-10-15')	返回星期一
