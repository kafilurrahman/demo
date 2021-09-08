FROM java:8

ADD target/spring-boot-product-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java" , "-jar" , "app.jar" ]

EXPOSE 9090