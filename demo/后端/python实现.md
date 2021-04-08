# 一、安装架构

## 1、安装django

### 1、

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

### 2、

开启django服务

在Anaconda Powershell Prompt 中输入

~~~powershell
django-admin startproject django_db
~~~

可以看到在相应的文件夹有文件和manage.py生成

然后跳转到django_db文件夹下，在Anaconda Powershell Prompt 中输入

~~~powershell
python manage.py runserver
~~~

在浏览器的地址栏中输入127.0.0.1:8000就能看到已经开启django服务



### 了解

新建的项目结构

* django_db
  * manage.py
  * django_db
    * _init_.py
    * settings.py
    * urls.py
    * asgi.py
    * wsgi.py

其中

~~~
外层的django_db/目录与Django无关，只是你项目的容器，可以任意重命名。
    manage.py：一个命令行工具，管理Django的交互脚本。
    内层的django_db/目录是真正的项目文件包裹目录，它的名字是你引用内部文件的Python包名，例如：mysite.urls。
    django_db/__init__.py:一个定义包的空文件。
    django_db/settings.py:项目的配置文件。
    django_db/urls.py:路由文件，所有的任务都是从这里开始分配，相当于Django驱动站点的目录。
    django_db/wsgi.py:一个基于WSGI的web服务器进入点，提供底层的网络通信功能，通常不用关心。
    django_db/asgi.py：一个基于ASGI的web服务器进入点，提供异步的网络通信功能，通常不用关心。
~~~

## 2、在django中创建新的应用

在Anaconda Powershell Prompt 中输入

~~~powershell
python manage.py startapp query
~~~

就能生成query文件夹了。

query目录结构

* query/

  * __init__.py

  * admin.py

  * apps.py

  * migrations/

  * __init__.py

  * models.py

  * tests.py

  * views.py



## 3、编写第一个视图

在query/views.py中编写

~~~python
from django.http import HttpResponse
# Create your views here.
def hello_index(request):
    return HttpResponse('Hello! 你成功创建了一个视图！')
~~~

为调用该视图，需要编写urlconf， 在django_db/urls.py 中输入

~~~python
from django.contrib import admin
from django.urls import path
from query.views import  hello_index
urlpatterns = [
    path('admin/', admin.site.urls),
    path('hello/',hello_index)
]
~~~

在127.0.0.1:8000 就能看到有 Hello! 你成功创建了一个视图！

可以仿照格式创建其他的路由和相应的函数



