说明：该项是准备做一个考试系统 主要后端的技术栈为：spring boot ,mybatis plus ,shiro，redis

#mybatis plus：

Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis 的基础上只做增强不做改变，为简化开发、提高效率而生。 

官网

说明：这里只写关于mybatis plus 的一些特性和是使用方法。



##注解说明：

主键注解 @TableId 

MP支持以下4中主键策略，可根据需求自行选用： 

  值               	描述                   
  IdType.AUTO     	数据库ID自增              
  IdType.INPUT    	用户输入ID               
  IdType.ID_WORKER	全局唯一ID，内容为空自动填充（默认配置）
  IdType.UUID     	全局唯一ID，内容为空自动填充      



字段注解 @TableField 

  值        	描述                                      
  value    	字段值（驼峰命名方式，该值可无）                        
  update   	预处理 set 字段自定义注入                         
  condition	预处理 WHERE 实体条件自定义运算规则                   
  el       	详看注释说明                                  
  exist    	是否为数据库表字段（ 默认 true 存在，false 不存在 ）       
  strategy 	字段验证 （ 默认 非 null 判断，查看 com.baomidou.mybatisplus.enums.FieldStrategy ）
  fill     	字段填充标记 （ FieldFill, 配合自动填充使用 ）          



##通用的CRUD :

mybatis plus 会自动帮我做很多事情，很多的基本操作已经帮我们实现，只需要在类里面继承 BaseMapper 就能实现。

在插入时 使用普通的 insert () 方法 ，会进行属性的非空属相判断 ，sql中不会出现该字段的填充

在插入时 使用 insertAllColumn() 方法 ，不会进行属性的非空属相判断 ，sql中会出现该字段的填充，会写入所有的属性（同样在更新也对用了这个两个方法）



### 条件构造器：

实体包装器 EntityWrapper 继承 Wrapper 

​			Condition 继承 Wrapper(和EntityWrapper 差不多)

实例：

```java
//分页加一些简单的条件查询
Page<User> users = userService.selectPage(new Page(1,2),new EntityWrapper<User>()
                .like("user_name","gay"));
```



| 查询方式     | 说明                              |
| ------------ | --------------------------------- |
| setSqlSelect | 设置 SELECT 查询字段              |
| where        | WHERE 语句，拼接 + `WHERE 条件`   |
| and          | AND 语句，拼接 + `AND 字段=值`    |
| andNew       | AND 语句，拼接 + `AND (字段=值)`  |
| or           | OR 语句，拼接 + `OR 字段=值`      |
| orNew        | OR 语句，拼接 + `OR (字段=值)`    |
| eq           | 等于=                             |
| allEq        | 基于 map 内容等于=                |
| ne           | 不等于<>                          |
| gt           | 大于>                             |
| ge           | 大于等于>=                        |
| lt           | 小于<                             |
| le           | 小于等于<=                        |
| like         | 模糊查询 LIKE                     |
| notLike      | 模糊查询 NOT LIKE                 |
| in           | IN 查询                           |
| notIn        | NOT IN 查询                       |
| isNull       | NULL 值查询                       |
| isNotNull    | IS NOT NULL                       |
| groupBy      | 分组 GROUP BY                     |
| having       | HAVING 关键词                     |
| orderBy      | 排序 ORDER BY                     |
| orderAsc     | ASC 排序 ORDER BY                 |
| orderDesc    | DESC 排序 ORDER BY                |
| exists       | EXISTS 条件语句                   |
| notExists    | NOT EXISTS 条件语句               |
| between      | BETWEEN 条件语句                  |
| notBetween   | NOT BETWEEN 条件语句              |
| addFilter    | 自由拼接 SQL                      |
| last         | 拼接在最后，例如：last("LIMIT 1") |



### AR：

这个AR并不是我们平时说的那个AR 

是叫做 ActiveRecord ，实体类只需继承 Model 类即可实现基本 CRUD 操作 

例如：

```java
//只需在实体后面 .xxx就行了 
User user = new User();
        Page<User> page = user.selectPage(new Page(1, 2), new EntityWrapper<User>()
                .eq("user_name", "gay"));

```



### 插件机制：

mybatis plus支持分页插件，乐观锁，性能分析，执行分析插件。（详情请看官网）

### 自定义全局操作：

[自定义全局操作](http://mp.baomidou.com/#/sql-injector)

### 公共字段自动填充：

[公共字段自动填充](http://mp.baomidou.com/#/auto-fill)



# shiro：

使用步骤：

​	添加pom:

```xml
		<!-- shiro spring. -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.2.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.2.2</version>
        </dependency>
        <!-- shiro ehcache -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-ehcache</artifactId>
            <version>1.2.2</version>
        </dependency>
```

shiro的初始化配置：

​	（1）ShiroFilterFactoryBean：是个拦截器，在请求进入控制层前将其拦截，需要将安全管理器SecurityManager注入其中		

​				

​			这里是将url进行判断进行拦截，自己配置了 **URLPathMatchingFilter**自定义的拦截器。

​	（2）SecurityManager：安全管理器，需要将自定义realm注入其中，以后还可以将缓存、remeberme等注入其中

​	（3）自定义reaml：认证授权会执行它，需要自己写

​			DatabaseRealm继承 **AuthorizingRealm**，重写**doGetAuthorizationInfo**授权方法和**doGetAuthenticationInfo**认证方法 

（缺陷：未加入Redis 进行储存，对数据库的读写比较频繁）

# nosql:	

NoSQL（Not Only SQL）泛指非关系型数据库。主要代表：MongoDB，Redis，CouchDB。 

## RDBMS vs NoSQL

**RDBMS** 
\- 高度组织化结构化数据 
\- 结构化查询语言（SQL） (SQL) 
\- 数据和关系都存储在单独的表中。 
\- 数据操纵语言，数据定义语言 
\- 严格的一致性
\- 基础事务

**NoSQL** 
\- 代表着不仅仅是SQL
\- 没有声明性查询语言
\- 没有预定义的模式
-键 - 值对存储，列存储，文档存储，图形数据库
\- 最终一致性，而非ACID属性
\- 非结构化和不可预知的数据
\- CAP定理 
\- 高性能，高可用性和可伸缩性

## *CAP定理（CAP theorem）

在计算机科学中, CAP定理（CAP theorem）, 又被称作 布鲁尔定理（Brewer's theorem）, 它指出对于一个分布式计算系统来说，不可能同时满足以下三点:

- **一致性(Consistency)** (所有节点在同一时间具有相同的数据)

- **可用性(Availability)** (保证每个请求不管成功或者失败都有响应)

- **分隔容忍(Partition tolerance)** (系统中任意信息的丢失或失败不会影响系统的继续运作)

  CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，最多只能同时较好的满足两个。（分区容忍性是必须的）

  因此，根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三 大类：

  - CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。（传统的关系型数据库）
  - CP - 满足一致性，分区容忍性的系统，通常性能不是特别高。
  - AP - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一

## NoSQL的分类:

NoSQL仅仅是一个概念，NoSQL数据库根据数据的存储模型和特点分为很多种类。  

![img](https://img-blog.csdn.net/20150823192851443) 

NoSql 数据库的分类：

​	

| 类型          | 部分代表                                         | 特点                                                         |
| ------------- | ------------------------------------------------ | ------------------------------------------------------------ |
| 列存储        | HbaseCassandraHypertable                         | 顾名思义，是按列存储数据的。最大的特点是方便存储结构化和半结构化数据，方便做数据压缩，对针对某一列或者某几列的查询有非常大的IO优势。 |
| 文档存储      | MongoDBCouchDB                                   | 文档存储一般用类似json的格式存储，存储的内容是文档型的。这样也就有有机会对某些字段建立索引，实现关系数据库的某些功能。 |
| key-value存储 | Tokyo Cabinet / TyrantBerkeley DBMemcacheDBRedis | 可以通过key快速查询到其value。一般来说，存储不管value的格式，照单全收。（Redis包含了其他功能） |
| 图存储        | Neo4JFlockDB                                     | 图形关系的最佳存储。使用传统关系数据库来解决的话性能低下，而且设计使用不方便。 |
| 对象存储      | db4oVersant                                      | 通过类似面向对象语言的语法操作数据库，通过对象的方式存取数据。 |
| xml数据库     | Berkeley DB XMLBaseX                             | 高效的存储XML数据，并支持XML的内部查询语法，比如XQuery,Xpath。 |



### redis：

REmote DIctionary Server(Redis) 是一个由Salvatore Sanfilippo写的key-value存储系统。 

它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。 

Redis 是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库。

Redis 与其他 key - value 缓存产品有以下三个特点：

- Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。

- Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。

- Redis支持数据的备份，即master-slave模式的数据备份。

  

  [安装方法和配置](https://www.jianshu.com/p/10baa32b2cd9)

在本系统使用redis做shiro的缓存，在这里是使用的shiro-redis插件，在redis的config类中配置一下即可使用（因为是做的考试管理系统所有就没有考虑session共享的问题）















