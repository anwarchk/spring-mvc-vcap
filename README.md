# Spring MVC and Cloud Foundry Environment Variables

This is a sample app to demonstrate how you can access `VCAP_SERVICES` environment variables from a non Spring Boot application.

## Using Spring Expression Language (SPEL)

Below are the options on how you can access the `VCAP_SERVICES` variables using `SPEL`

> You need a dependency on:

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-cloudfoundry-connector</artifactId>
            <version>1.2.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-spring-service-connector</artifactId>
            <version>1.2.4.RELEASE</version>
        </dependency>

```


#### Option 1:

Using `CloudPropertiesFactoryBean` class


```xml
<bean id="cloudProperties" class="org.cloudfoundry.runtime.env.CloudPropertiesFactoryBean"/>
<bean id="rabbitPropertiesBean" lazy-init="true" class="com.anwar.RabbitPropertiesBean">
    <property name="rabbitHost" value="#{cloudProperties['cloud.services.myrabbit.connection.host']}"/>
</bean>
```

In the example above, `myrabbit` is the service instance name that you would create from the service catalog.



#### Option 2:

Using the `<cloud>` namespace.

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

    <bean id="rabbitPropertiesBean" lazy-init="true" class="com.anwar.RabbitProperties">
        <property name="rabbitHost" value="#{cloudProperties['cloud.services.myrabbit.connection.host']}"/>
    </bean>

</beans>
```  
> There is an issue in getting the right `rabbit` port value using`cloud.services.myrabbit.connection.port`, which returns `-1`, instead of the `5672`. 
  Please see the Github issue here : https://github.com/spring-cloud/spring-cloud-connectors/issues/214 
