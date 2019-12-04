FROM openjdk:11.0.3-jdk-slim

RUN apt-get update && \
    apt-get install -y vim && \
    apt-get install -y iputils-ping && \
    apt-get install -y curl

ADD build/libs/movie-service-1.0.jar .
EXPOSE 8001

CMD java -jar movie-service-1.0.jar
