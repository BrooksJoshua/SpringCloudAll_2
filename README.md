遇到如下问题
```text
***************************
APPLICATION FAILED TO START
***************************

Description:

An attempt was made to call a method that does not exist. The attempt was made from the following location:

    org.apache.catalina.authenticator.AuthenticatorBase.startInternal(AuthenticatorBase.java:1220)

The following method did not exist:

    javax.servlet.ServletContext.getVirtualServerName()Ljava/lang/String;

The method's class, javax.servlet.ServletContext, is available from the following locations:

    jar:file:/usr/local/Cellar/maven/3.6.1/libexec/repo_local/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar!/javax/servlet/ServletContext.class
    jar:file:/usr/local/Cellar/maven/3.6.1/libexec/repo_local/org/apache/tomcat/embed/tomcat-embed-core/9.0.29/tomcat-embed-core-9.0.29.jar!/javax/servlet/ServletContext.class

It was loaded from the following location:

    file:/usr/local/Cellar/maven/3.6.1/libexec/repo_local/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar


Action:

Correct the classpath of your application so that it contains a single, compatible version of javax.servlet.ServletContext


Process finished with exit code 0

```

[整合Eureka jar冲突解决](https://blog.csdn.net/kingzhaoc/article/details/102883112)
```xml
    <exclusions>
        <exclusion>                
            <artifactId>servlet-api</artifactId>                
            <groupId>javax.servlet</groupId>       
        </exclusion>
    </exclusions>
```