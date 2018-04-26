FROM java:8-jre
RUN mvn -Dmaven.test.failure.ignore clean package
COPY target/my-spring-demo-0.1.0.jar  my-spring-demo.jar
COPY docker/start.sh /
ENV CONFIG_SERVER_ADDRESS=https://github.com/fengsong97/my-spring-config-file/tree/master/config \
    APPLICATION_NAME=my-spring-demo \
    TAG=dev
CMD sh start.sh $CONFIG_SERVER_ADDRESS $APPLICATION_NAME $TAG