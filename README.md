## cloud

    这是一个简单的springboot整合其他组件及中间件的简单项目 后续会添加springcloud的rpc支持
    在此基础版本上新增代码时建议新建一个分支
    
### java version
    
    目前为java8 未来升级至java11
    
### maven version

    3.6.1 高版本maven与最新版idea有冲突 不建议使用
    
### 你需要修改的地方
    
    配置文件中的数据库配置以及与spring相关的参数
    
### 已经整合的配置清单
|type|name|version|
|:-|:-:|-:|
|spring|spring|5.2.2|
|springboot|springboot|2.2.2|
|orm|mybatis-springboot|2.1.1|
|orm|mybatis|3.5.3|
|mysql-connector|mysql|8.0.18|
|数据库连接池|alibaba-druid|1.1.21|
|guava|guava|28.1-jre|
|hutool|hutool|5.0.7|
|*mybatis.generator|mybatis.generator|1.4.0|
|json|fastjson|1.2.62|

#### 备注：
    
    *：mybatis代码生成器在项目中自定义了注释生成器，需要修改源码。
       对应jar已上传至项目中resources/jars目录下,替换本地maven仓库中对应jar即可。
    
#### contact me
    
    e-mail:wangxb0902@126.com
    
