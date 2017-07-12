# authority
ssh 基于struts拦截器的权限管理 

实现基本的用户 角色 权限 CURD 
easyUI 动态树加载  赋予角色的权限 

权限拦截基于struts的interceptor  struts的访问通过动态方法调用 使用拦截器判断访问的Action和对应的方法来进行权限判定

分页参数通过ThreadLocal由filter初始话 参数并传递至dao层

登录拦截使用filter来 判断session中是否存在登录用户来进行判断  

BaseDao为通用方法 封装基本CURD和分页查询方法

数据库使用mysql 更改resources目录下db.properties中的数据连接信息即可

第一次导入需运行test目录下TestUserService.java中 testUserAdd,testAddRole,testAddRoleForUser 为系统增加admin用户


