import pymysql

conn = pymysql.connect(
    host='localhost',
    port=3306,
    user='root',
    password='123456',  #填自己的密码
    database='world',
    use_unicode=True
)
cursor = conn.cursor()

sql = 'select * from city'
cursor.execute(sql)
data = cursor.fetchmany(10)
for key in data:
    print(key)
