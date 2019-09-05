项目设计
.
├── README  -- Doc文档
├── db  -- 数据库约束文件
├── mvnw
├── mvnw.cmd
├── pom.xml  -- 项目依赖
└── src
    ├── main
    │   ├── java
    │   │   └── cn
    │   │       └── tycoding
    │   │           ├── SpringbootSeckillApplication.java  -- SpringBoot启动器
    │   │           ├── controller  -- MVC的web层
    │   │           ├── dto  -- 统一封装的一些结果属性，和entity类似
    │   │           ├── entity  -- 实体类
    │   │           ├── enums  -- 手动定义的字典枚举参数
    │   │           ├── exception  -- 统一的异常结果
    │   │           ├── mapper  -- Mybatis-Mapper层映射接口，或称为DAO层
    │   │           ├── redis  -- redis,jedis 相关配置
    │   │           └── service  -- 业务层
    │   └── resources
    │       ├── application.yml  -- SpringBoot核心配置
    │       ├── mapper  -- Mybatis-Mapper层XML映射文件
    │       ├── static  -- 存放页面静态资源，可通过浏览器直接访问
    │       │   ├── css
    │       │   ├── js
    │       │   └── lib
    │       └── templates  -- 存放Thymeleaf模板引擎所需的HTML，不能在浏览器直接访问
    │           ├── page
    │           └── public  -- HTML页面公共组件（头部、尾部）
    └── test  -- 测试文件

mybatis的一些特殊符号标识
特殊字符   替代符号（红色基本为常用的）

     &            &amp;

     <            &lt;

     >            &gt;

     "             &quot;

     '              &apos;



小于等于    a<=b                 a &lt;= b      a <![CDATA[<= ]]>b

大于等于    a>=b                 a &gt;= b      a <![CDATA[>= ]]>b

不等于        a!=ba <![CDATA[ <> ]]>b      a <![CDATA[!= ]]>b
------------------------------------------------------------------------------------------------------------------------
2019-09-04 00:50
错误-->
日志：
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jedisConfig': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'spring.redis.host' in value "${spring.redis.host}"

原因：
application.yml文件缩进问题导致无法将参数注入到jedisPool

例子：
@Value("${spring.redis.host}")

检查发现是配置文件当中的配置格式问题,配置文件中缩进影响的,

类似于之前为:

spring:

    redis:

    host:*********************

这样会将item和url识别为同一级别

正确为:

spring:

    redis:

        host:*********************
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
2019-09-04 20:00
错误-->
描述：运行项目，点击按钮的时候，返回的是页面地址的一行文字，而不是跳转到指定页面

原因：因为在controller对应跳转方法上加了@ResponseBody注解，导致返回的是json数据，去掉@ResponseBody。

例子：
return "page/detail";
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
2019-09-04 23:00
错误-->
描述：更改的数据库数据不生效。

原因：当系统使用redis的时候，后台代码默认先从redis缓存中获取数据。

解决方法：redis 127.0.0.1:6379> FLUSHALL 清空所有缓存，重新导入数据。
        在以后的逻辑中加入数据库更改同时使缓存同步。
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
2019-09-05 19:30
错误-->
描述：JavaScript中post()方法报错500，前台传递来的一个参数无法获得
{timestamp: "2019-09-05T10:13:17.812+0000", status: 500, error: "Internal Server Error",…}
error: "Internal Server Error"
message: "Missing URI template variable 'money' for method parameter of type BigDecimal"
path: "/seckill/1000/c0df1182b7b98957cca522a1c7b2e1e6/execution"
status: 500
timestamp: "2019-09-05T10:13:17.812+0000"

原因：后台获取数据的时候，注解选择错误
                        正确：@RequestParam
                        错误：@PathVariable

解决方法：
@PathVariable
获取的是请求路径中参数的值
@RequestParam
获取的是请求参数，一般是url问号后面的参数值