# Deployment related configuration files

## mysql

mysql initialization script

## each

elk configuration file

## docker-compose.env

Environment variables shared by all services

## docker-compose-base.yml

Basic services: consul, Redis, rabbitMq, config-service

## docker-compose-services.yml

Core services: auth-service, user-service, exam-service, gateway-service, monitor-service, msc-service


## docker-compose-nginx.yml

Front-end services: ui-service, web-service

## docker-compose-elk.yml

elk service

## redis.conf

Redis configuration

## nginx.conf

The nginx configuration shared by the front-end service, the main configuration is to forward the api request to the gateway-service


## start.sh

Startup script

## QRCode.jar

Install to the local maven warehouse command: mvn install:install-file -Dfile=C:\Users\Administrator\Downloads>\QRCode.jar -DgroupId=QRCode -DartifactId=QRCode -Dversion=3.0 -Dpackaging=jar