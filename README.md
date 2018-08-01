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





