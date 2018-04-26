FROM hub.c.163.com/library/java:8-jre
RUN mkdir app
WORKDIR app
COPY target/my-spring-demo-0.1.0.jar  ./my-spring-demo.jar
COPY docker/start.sh .
CMD sh start.sh