#mybatis plus

Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis 的基础上只做增强不做改变，为简化开发、提高效率而生。 

官网

说明：这里只写关于mybatis plus 的一些特性和是使用方法。



##注解说明

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



