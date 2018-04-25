FROM java:8-jre
COPY target/my-spring-demo-0.1.0.jar  my-spring-demo.jar
COPY docker/start.sh /
ENV CONFIG_SERVER_ADDRESS=http://config.dev.kkche.cn \
    APPLICATION_NAME=my-spring-demo \
    TAG=dev
CMD sh start.sh $CONFIG_SERVER_ADDRESS $APPLICATION_NAME $TAG