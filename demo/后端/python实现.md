# 一、安装架构

## 1、安装django

### 1、导入django包

在Anaconda Powershell Prompt 中输入

~~~powershell
pip install django
~~~

进入python终端，导入django包

~~~python
import django
django.get_version()
~~~

当出现正确的版本信息，即表明django安装成功。

### 2、开启django服务

在Anaconda Powershell Prompt 中输入(输入一次即可)

~~~powershell
django-admin startproject django_db
~~~

可以看到在相应的文件夹有文件和manage.py生成

然后**跳转到django_db文件夹(路径)**下，在Anaconda Powershell Prompt 中输入

~~~powershell
python manage.py runserver
~~~

在浏览器的地址栏中输入**127.0.0.1:8000**就能看到已经开启django服务



### 项目结构

新建的项目结构

* django_db
  * manage.py
  * django_db
    * ______init______.py
    * settings.py
    * urls.py
    * asgi.py
    * wsgi.py

其中

* **外层的django_db/目录**与Django无关，**只是你项目的容器**，可以任意重命名。

* manage.py：一个命令行工具，管理Django的交互脚本。

* **内层的django_db/目录是真正的项目文件包裹目录**，它的名字是你引用内部文件的Python包名，例如：mysite.urls。

* django_db/______init______.py:一个定义包的空文件。(**init前后各有两个下划线**)

* django_db/settings.py:项目的配置文件。

  * 每添加一个新的APP，就需要在**INSTALLED_APP**中增加该APP的名称
  * 如果需要与数据库有关联，就修改对应的**DATABASES**

* django_db/urls.py:**路由文件，所有的任务都是从这里开始分配**，相当于Django驱动站点的目录。

  * 配置路由，打开服务器就能进行访问

  * 配置的格式是

  * ~~~python
    from APP.view import function
    
    url_patterns = {
        ('路径', function)
    }
    ~~~

* django_db/wsgi.py:一个基于WSGI的web服务器进入点，**提供底层的网络通信功能**，通常不用关心。

* django_db/asgi.py：一个基于ASGI的web服务器进入点，**提供异步的网络通信功能**，通常不用关心。




## 2、在django中创建新的应用(query)

**使用startapp创建新的APP**

在Anaconda Powershell Prompt 中输入

~~~powershell
python manage.py startapp query
~~~

就能生成query文件夹了。

### query目录结构

* query/

  * ______init______.py

  * admin.py

  * apps.py

  * migrations/

  	* ______init______.py

  * models.py

  * tests.py

  * views.py

# 二、内容初探

## 1、编写第一个视图

在query/views.py中编写

~~~python
from django.http import HttpResponse

# Create your views here.
def hello_index(request):
    return HttpResponse('Hello! 你成功创建了一个视图！')
~~~

为**调用该视图，需要编写urlconf，即路由配置**， 在django_db/urls.py 中输入

~~~python
from django.contrib import admin
from django.urls import path
from query.views import  hello_index	#这句话是从query文件夹下的views.py导入hello_index函数

urlpatterns = [
    path('admin/', admin.site.urls),
    path('hello/',hello_index)	#将新的功能添加到路由中
]
~~~

启动服务器，打开浏览器，在地址栏输入127.0.0.1:8000 就能看到有 Hello! 你成功创建了一个视图！

可以仿照格式创建其他的路由和相应的函数。

~~~python
可以使用
from .view import *
~~~

## 2、创建mysql查询视图

在query/views.py中输入

~~~python
from django.http import JsonResponse
import pymysql

def query_patient_by_id(request,pid):
    database_name = 'demo'	#这里填要连入的数据库名称
    
    #连接数据库
    db = pymysql.connect(host='localhost', user='root', password='123456HXJ.com',  database=database_name)
    
    #创建一个游标
    cursor = db.cursor()
    
    #查询数据的结果显示
    query_elements = ['Age', 'CheckDate', 'Gender', 'PatientID']
    query_statement = "select Age, CheckDate, Gender, PatientID from patientbasicinfos where id='%s'" % (pid)
    cursor.execute(query_statement)
    data = cursor.fetchall()
    
    #后台打印
    print(data)
    
    #生成字典并返回json格式到前端/服务器
    data = data[0]
    out_json = {}
    for j, name in enumerate(query_elements):
        out_json[name] = data[j]

    db.close()
    return JsonResponse(out_json)
~~~



# 三、同步数据库

安装mysqlclient

测试安装

~~~python
#在python命令行下

import MySQLdb

#无反应(只显示>>>)就是安装成功
~~~

说是同步其实是**通过python的代码对数据库进行管理，也就是ORM(Object Relational Mapping)对象关系映射**。

## 1、创建数据表

**在query/models.py下创建数据表的定义**，也就是创建APP对应的数据表结构。

一种方法是直接在models中手动创建一个个数据表。

~~~python
class Patientbasicinfos(models.Model):
    id = models.AutoField(db_column='ID', primary_key=True)
    checkdate = models.DateTimeField(db_column='CheckDate', blank=True, null=True)
    checknumber = models.CharField(db_column='CheckNumber', max_length=15)
    patientid = models.CharField(db_column='PatientID', max_length=15)
    patientname = models.CharField(db_column='PatientName', max_length=25, blank=True, null=True)
    age = models.CharField(db_column='Age', max_length=25, blank=True, null=True)
    clinicaldiagnosis = models.CharField(db_column='ClinicalDiagnosis', max_length=4000, blank=True, null=True)
    examinationfindings = models.CharField(db_column='ExaminationFindings', max_length=4000, blank=True, null=True)
    endoscopicdiagnosis = models.CharField(db_column='EndoscopicDiagnosis', max_length=4000, blank=True, null=True)
    pathologicaldiagnosis = models.CharField(db_column='PathologicalDiagnosis', max_length=4000, blank=True, null=True)
    patientreport = models.CharField(db_column='PatientReport', max_length=255, blank=True, null=True)
    hospitalid = models.CharField(db_column='HospitalID', max_length=33, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'patientbasicinfos'

~~~

根据上面的代码，Django会创建该app对应的数据库表结构，并且为Patientbasicinfos对象**创建基于python的数据库访问API**。

为了方便，可以采用另一种方法，由**已生成的数据表进行导入**。

在控制台中输入

~~~cmd
python manage.py inspectdb > query/models.py
~~~



## 2、Django配置mysql

在django_db/settings.py中修改InstallApp定义，注册query

~~~python
#实际上就是在原有的上面增加一个query

INSTALLED_APPS = [
    'query',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
~~~

django_db/settings.py修改DATABASE的定义，主要是修改engine，以及要访问的数据库的用户名和密码。

~~~python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'demo',
        'USER': 'root',
        'PASSWORD': '123456HXJ.com',
        'HOST': 'localhost',
        'PORT': '3306'

    }
}
~~~

## 3、同步

然后django模型与MySQL数据库进行同步，在命令窗口输入

~~~powershell
python manage.py migrate
~~~

就会在migrations文件夹中生成initial文件，表示创建了映射。

# 四、创建简单的html界面

在query/templates/query目录中创建query_patient.html，写入如下代码

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>query_patient</title>
</head>
<body>
<h1>简单的查询表单</h1>
<div sytle="margin: 15%, 40%">
    <form action="/query_patient/" method="post">
        {% csrf_token %}
        <p>
            <label for="id_patient">请输入病人ID</label>
            <input type="text" id="id_patient" name="patientid" placeholder="病人ID" autofocus required>
            <input type="submit" value="查询">
        </p>
        {% if error %}
        <h1>{{ error }}</h1>
        {% endif %}

        {% if pid %}
        <table border="1">
            <tr>
                <td>PatientId</td>
                <td>{{ pid }}</td>
            </tr>
            <tr>
                <td>Age</td>
                <td>{{ age }}</td>
            </tr>
            <tr>
                <td>CheckDate</td>
                <td>{{ checkdate }}</td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>{{ gender }}</td>
            </tr>
        </table>
        {% endif %}

    </form>
</div>



</body>
</html>
~~~

在**form标签之间添加表格的定义**，

~~~html
{% if error %}
<h1>{{ error }}</h1>
{% endif %}

{% if pid %}
<table border="1">
    <tr>
        <td>PatientId</td>
        <td>{{ pid }}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>{{ age }}</td>
    </tr>
    <tr>
        <td>CheckDate</td>
        <td>{{ checkdate }}</td>
    </tr>
    <tr>
        <td>Gender</td>
        <td>{{ gender }}</td>
    </tr>
</table>
{% endif %}
~~~



# 五、添加视图响应函数

在query/views.py中输入

~~~python
from django.shortcuts import render, redirect
from . import models

def query_patient(request):
    if request.method == 'POST':
        pid = request.POST.get('patinetid')
        if pid.strip():
            print('patientid: ', pid)
            try:
                user = models.Patientbasicinfos.objects.get(id=pid)
                print(user.checkdate)
            except:
                return render(request, 'query/query_patient.html', {'error': 'No such patient!'})

            age = user.age
            checkdate = user.checkdate
            gender = user.gender

            return render(request, 'query/query_patient.html', {'pid': pid, 'age': age, 'checkdate': checkdate, 'gender': gender})

        else:
            return render(request, 'query/query_patient.html',{'error': 'No such patient!'})
~~~

同样在url中添加路由。

尝试

只能显示，不能查询

~~~python
def query_patient(request):

    if request.method == 'POST':
        pid = request.POST.get('patinetid')
        if pid.strip():
            print('patientid: ', pid)
            try:
                user = models.Patientbasicinfos.objects.get(id=pid)
                print(user.checkdate)
            except:
                return render(request, 'query/query_patient.html', {'error': 'No such patient!'})

            age = user.age
            checkdate = user.checkdate
            gender = user.gender

            return render(request, 'query/query_patient.html', {'pid': pid, 'age': age, 'checkdate': checkdate, 'gender': gender})

    return render(request, 'query/query_patient.html', {'error': 'display'})
~~~

注意，这里的return render(quest, )后面跟的路由默认是templates目录下的

#  遇到的问题

1、添加视图响应函数，在浏览器中显示

```
The view query.views.query_patient didn't return an HttpResponse object. It returned None instead.
```

else语句要往前缩进



2、初始界面显示出来，但是一查询就

'NoneType' object has no attribute 'strip'

patient打成了patinet

# 总结

创建工程

~~~
推荐使用命令行的方法，需要自己移动到所需要的文件路径下
django-admin startproject projectname
~~~

创建一个APP

~~~
同样是推荐使用命令行的方式
django-admin startapp appname
~~~

连接数据库

两种方式

* 通过对象关系映射(ORM)
  * 配置settings.py(修改DATABASES中的内容)
  * 在数据已有的情况下，用inspectdb进行导入，反向生成models
  * view中就可以直接使用数据库中的内容
* 直接配置SQL
  * 配置settings.py(修改STARTAPP中的内容，增加APP名称)
  * 需要在view中添加数据库连接的功能，即database.connect("localhost", 3306, "root", "password", "要连接的数据库名称")
