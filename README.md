# Spring MVC and Cloud Foundry Environment Variables

This is a sample app to demonstrate how you can access `VCAP_SERVICES` environment variables from a non Spring Boot application.

## Using Spring Expression Language (SPEL)

Here is how you can access the `VCAP_SERVICES` properties using `SPEL`

Repos and dependency for this example:

```xml
    <repositories>
        <repository>
            <id>org.springframework.maven.milestone</id>
            <name>Spring Framework Maven Milestone Repository</name>
            <url>http://maven.springframework.org/milestone</url>
        </repository>
        <repository>
            <id>org.springframework.maven.snapshot</id>
            <name>Spring Framework Maven Milestone Repository</name>
            <url>http://repo.spring.io/libs-snapshot-local</url>
        </repository>
    </repositories>

     <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-cloudfoundry-connector</artifactId>
            <version>1.2.5.BUILD-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-spring-service-connector</artifactId>
            <version>1.2.4.RELEASE</version>
        </dependency>
     </dependencies>

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans profile="cloud"
       xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:cloud="http://www.springframework.org/schema/cloud"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/cloud
       http://www.springframework.org/schema/cloud/spring-cloud.xsd">

    <cloud:properties id="cloudProperties"/>

    <bean id="rabbitPropertiesBean" class="com.anwar.RabbitProperties">
        <property name="rabbitHost" value="#{cloudProperties['cloud.services.myrabbit.connection.host']}"/>
        <property name="rabbitPort" value="#{cloudProperties['cloud.services.myrabbit.connection.port']}"/>
    </bean>
</beans>
```  
