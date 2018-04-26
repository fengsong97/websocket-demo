#!/bin/bash

CONFIG_SERVER_ADDRESS=https://raw.githubusercontent.com/fengsong97/my-spring-config-file/master/config
APPLICATION_NAME=my-spring-demo
TAG=dev

configUrl=$CONFIG_SERVER_ADDRESS/$APPLICATION_NAME/$TAG/app.yml
echo $configUrl
wget -O app.yml $configUrl  --no-check-certificate
echo "*****************************************************配置文件开始*************************************************************************************"
cat app.yml
echo "*****************************************************配置文件结束*************************************************************************************"
java  -jar -Xms2048m -Xmx2048m my-spring-demo.jar --spring.config.location=./app.yml  --spring.config.name=app
