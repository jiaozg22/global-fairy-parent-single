# global-fairy-parent-single

基于dubbo的微服务架构（A microservices framework based on dubbo）

## 一.环境配置 
###  v1.0 版本配置

#### 安装软件及版本 （install softwares versions):

jdk-version ：jdk1.7.0_51

tomcat：apache-tomcat-7.0.63

zookeeper：apache-zookeeper-3.4.8

maven :　apache-maven-3.3.3

activemq : apache-activemq-5.14.5

redis : Redis-x64-3.0.500

spring framework :4.1.6

###  新版本
#### 安装软件及版本 （install softwares versions):

jdk-version ：jdk1.8

tomcat：apache-tomcat-8.5

zookeeper：apache-zookeeper-3.4.8

maven :　apache-maven-3.3.3

activemq : apache-activemq-5.14.5

redis : Redis-x64-3.0.500

spring framework :5.2.0


## 二.为了启用dubbo与zookeeper，请设置环境变量 (in order to start dubbo and zookeeper,please set environments)
JAVA_HOME=JDK安装路径的环境变量值  ;(jdk environment)

CATALINA_HOME=tomcat安装路径的环境变量    ;(tomcat environment)

ZOOKEEPER_HOME=ZOOKEEPER的环境变量值   ;(zookeeper  environment)

MAVEN_HOME=maven安装路径的环境变量   ;(maven  environment)

## 三.服务启动 (start services)
提供服务的jar包可执行，点击运行，直接启动dubbo与zookeeper   (start up jar by commond "java -jar xxx.jar,and you can start dubbo and zookeeper)

### jar包存放路径：.m2/repository/org/global/fairy/global-fairy-service-impl/0.0.1-SNAPSHOT/global-fairy-service-impl-0.0.1-SNAPSHOT.jar
(the path of the jar :.m2/repository/org/global/fairy/global-fairy-service-impl/0.0.1-SNAPSHOT/global-fairy-service-impl-0.0.1-SNAPSHOT.jar)

## 四.环境配置
### windows环境启动配置（伪分布式模式）：
1)找到内网地址 例如 192.168.0.101
2)找到系统名：例如 jzg 
3)系统盘 C:\Windows\System32\drivers\etc\hosts文件，最后添加一条：内网ip 系统名 例如：192.168.0.101 jzg 
4）将zookeeper的端口2181 添加到防火墙（或者关闭防火墙）

### linux 同理
