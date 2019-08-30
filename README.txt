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