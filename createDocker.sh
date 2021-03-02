#!/bin/bash

docker-compose up -d --build compasso-db
mvn -f component/pom.xml clean package
docker-compose up -d --build compasso-app


