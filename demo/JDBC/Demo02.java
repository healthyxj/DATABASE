package com.github.MySQL1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) throws Exception {
        //1、导入驱动jar包
        //2、注册驱动，运行时会生成一条语句
        //Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
        Class.forName("com.mysql.jdbc.Driver");
        //3、获取数据库连接对象, 这里password要填自己的密码才能够登上去
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "password");

        //4、定义SQL语句
        String str = "SELECT `city`.`ID`,\n" +
                "    `city`.`Name`,\n" +
                "    `city`.`CountryCode`,\n" +
                "    `city`.`District`,\n" +
                "    `city`.`Population`\n" +
                "FROM `world`.`city`";

//        char[] str1 = str.toCharArray();
//        String str3 = str1.toString();

        //5、获取执行SQL的对象
        Statement stat = conn.createStatement();

        //6、执行SQL，因为这里是查询语言，所以需要用到ResultSet
        ResultSet str2 = stat.executeQuery(str);

        //7、处理结果，用ResultSet的next方法进行遍历，实际上MySQL库里有4071多项，所以这里使用了count进行限制
        int count = 0;
        while(str2.next() && count++ <= 100){

            int id = str2.getInt("ID");
            String name = str2.getString("name");
            System.out.println("ID为" + id + ", 城市名为" + name + ", 次数为" + count);
        }

        //8、释放资源
        stat.close();
        conn.close();

    }
}
