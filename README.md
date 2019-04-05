# global-fairy-parent-single

基于dubbo的微服务架构（A microservices framework based on dubbo）


# 安装软件及版本 （install softwares versions):


jdk-version ：jdk1.7.0_51

tomcat：apache-tomcat-7.0.63

zookeeper：apache-zookeeper-3.4.8

maven :　apache-maven-3.3.3

activemq : apache-activemq-5.14.5

redis : Redis-x64-3.0.500

# 为了启用dubbo与zookeeper，请设置环境变量 (in order to start dubbo and zookeeper,please set environments)
JAVA_HOME=JDK安装路径的环境变量值  ;(jdk environment)

CATALINA_HOME=tomcat安装路径的环境变量    ;(tomcat environment)

ZOOKEEPER_HOME=ZOOKEEPER的环境变量值   ;(zookeeper  environment)

MAVEN_HOME=maven安装路径的环境变量   ;(maven  environment)

# 服务启动 (start services)
提供服务的jar包可执行，点击运行，直接启动dubbo与zookeeper   (start up jar by commond "java -jar xxx.jar,and you can start dubbo and zookeeper)

jar包存放路径：.m2/repository/org/global/fairy/global-fairy-service-impl/0.0.1-SNAPSHOT/global-fairy-service-impl-0.0.1-SNAPSHOT.jar
(the path of the jar :.m2/repository/org/global/fairy/global-fairy-service-impl/0.0.1-SNAPSHOT/global-fairy-service-impl-0.0.1-SNAPSHOT.jar)
# 项目架构图
https://blog.csdn.net/jiao_zg/article/details/80228708
