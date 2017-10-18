# Spring MVC and Cloud Foundry Environment Variables

This is a sample app to demonstrate how you can access `VCAP_SERVICES` environment variables from a non Spring Boot application.

## Using Spring Expression Language

Here is an example of how you can access the `VCAP_SERVICES` variables from `SPEL`

```xml
<bean id="cloudProperties" class="org.cloudfoundry.runtime.env.CloudPropertiesFactoryBean"/>
<bean id="rabbitPropertiesBean" lazy-init="true" class="com.anwar.RabbitPropertiesBean">
    <property name="rabbitHost" value="#{cloudProperties['cloud.services.myrabbit.connection.hostname']}"/>
</bean>```

In the example above, `myrabbit` is the service instance name that you would create from the service catalog.

You need a dependency on:

```xml
<dependency>
  <groupId>org.cloudfoundry</groupId>
  <artifactId>cloudfoundry-runtime</artifactId>
  <version>0.8.1</version>
</dependency>
```        

to access the `CloudPropertiesFactoryBean` class

As per [Alex Shumilov](https://github.com/poprygun), the following approach also would work, but I have not tested it personally. But when you add the `<cloud>` namespace, you lose the auto-re-configuration magic provided by the build pack.

```xml
<cloud:properties id="cloud-properties"/>
  <context:property-placeholder properties-ref="cloud-properties"/>
  <bean id="testDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver" />
    <property name="url" value="${cloud.services.test-db.connection.jdbcUrl}" />
    <property name="username" value="${cloud.services.test-db.connection.username}"/>
    <property name="password" value="${cloud.services.test-db.connection.password}"/>
  </bean>
```  
