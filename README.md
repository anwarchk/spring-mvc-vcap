# Spring MVC and Cloud Foundry Environment Variables

This is a sample app to demonstrate how you can access `VCAP_SERVICES` environment variables from a non Spring Boot application.

## Using Spring Expression Language

Here is an example of how you can access the `VCAP_SERVICES` variables from `SPEL`

> You need a dependency on:

```xml
<dependency>
  <groupId>org.cloudfoundry</groupId>
  <artifactId>cloudfoundry-runtime</artifactId>
  <version>0.8.1</version>
</dependency>
```


to access the `CloudPropertiesFactoryBean` class

#### Option 1:

```xml
<bean id="cloudProperties" class="org.cloudfoundry.runtime.env.CloudPropertiesFactoryBean"/>
<bean id="rabbitPropertiesBean" lazy-init="true" class="com.anwar.RabbitPropertiesBean">
    <property name="rabbitHost" value="#{cloudProperties['cloud.services.myrabbit.connection.hostname']}"/>
</bean>
```

In the example above, `myrabbit` is the service instance name that you would create from the service catalog.



#### Option 2:

Based on the good recommendation from [Alex Shumilov](https://github.com/poprygun), the following approach also works. When you add the `<cloud>` namespace, you might lose some of the auto-re-configuration magic provided by the build pack.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans profile="cloud" xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
       http://schema.cloudfoundry.org/spring
       http://schema.cloudfoundry.org/spring/cloudfoundry-spring.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd"
       xmlns:cloud="http://schema.cloudfoundry.org/spring"
       xmlns:context="http://www.springframework.org/schema/context">

    <cloud:properties id="cloudProperties"/>

    <context:property-placeholder properties-ref="cloudProperties"/>

    <bean id="rabbitPropertiesBean" class="com.anwar.RabbitPropertiesBean">
        <property name="rabbitHost" value="${cloud.services.myrabbit.connection.hostname}"/>
    </bean>
</beans>
```  
