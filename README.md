# Movie Tickes Snap System
#电影票抢购系统

采用技术：Spring Boot、Redis、DataNucleus JDO、Mariadb、Spring Mail

展示地址(非长期有效)：http://cenx.xin:8080/ 或者 http://120.77.175.184:8080/

#功能描述:

1、用户用邮箱登录系统，如果未注册，将自动注册。

2、用户可以查看所有活动详情。未开始的活动将显示开始倒计时，正在抢购的可以点击抢购按钮抢购，并且3秒刷新一次数量信息。

3、每位客户只能成功抢购一次，抢购成功之后会向登录邮箱发送邮件。

#初始化项目：

1、clone到本地之后，首先修改两个配置：META-INF/persistence.xml 修改数据库连接配置。application.properties修改redis和电子邮箱密码配置。

2、创建数据库movie_snop，执行maven命令mvn clean compile编译项目，再执行mvn datanucleus:schema-create 生成数据库表。

3、在数据库中执行insert.sql文件插入相应数据。

4、在数据库中执行snap_pro.sql文件创建抢购存储过程代码。

4、执行mvn exec:java启动项目。

5、项目发布：执行clean compile install -D maven.test.skip=true将项目打包成war,放到tomcat中运行。

#项目部分技术细节

1、为了更好的自定义缓存，关闭DataNucleus JDO二级缓存。

2、用redis缓存数据。大部分数据超时时间为5分钟，电影票数量数据超时为3s。不是说缓存必须5分超时后失效，在操作修改请求到数据库时，会删除相应缓存。

3、Jackson2JsonRedisSerializer 不支持没有相应构造函数的对象反序列化。Datanucleus中org.datanucleus.store.types.wrappers.Date对象没有参数为long的构造函数，因此在反序列化的过程中，会抛出异常。该项目重写ObjectMapper，自定义反序列化过程，将起替换成java.util.Date，解决问题。

4、为了提高抢购数据的高可靠性和执行效率。抢购数据库操作，采用数据库存储过程。


