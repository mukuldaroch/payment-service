#!/usr/bin/env bash
set -e
# BLUE
b="\e[34m"
# RED
re="\e[31m"
# GREEN
g="\e[32m"
# RESET
r="\e[0m"

echo -e "${b}Building JAR...${r}"
# ./gradlew clean build -e SPRING_PROFILES_ACTIVE=docker
./gradlew clean build

echo -e "${re}Removing old container if exists...${r}"
docker rm -f payment-service 2>/dev/null || true

echo -e "${b}Building Docker image...${r}"
docker build -t payment-service .

echo -e "${b}Starting new container...${r}"

docker run -d \
	--name payment-service \
	--network event-mesh \
	--add-host auth.local:host-gateway \
	-p 8086:8080 \
	-e SPRING_DATASOURCE_URL=jdbc:postgresql://payment-database:5432/paymentdb \
	-e SPRING_DATASOURCE_USERNAME=postgres \
	-e SPRING_DATASOURCE_PASSWORD=daroch \
	-e SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
	-e SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=http://auth.local:8080/realms/event-service \
	-e KEYCLOAK_CLIENT_ID=event-api \
	-e KEYCLOAK_CREDENTIALS_SECRET=g1Js0gl1dbyaxwacTVJiSQSMDIULQjgp \
	payment-service

echo -e "${b}Payment Service running at http://localhost:8083${r}"
echo -e "${b}Logs: docker logs -f payment-service${b}"
