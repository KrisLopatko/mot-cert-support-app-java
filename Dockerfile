FROM openjdk:21-slim

ADD . /usr/local/application

WORKDIR /usr/local/report

COPY target/mot-cert-support-app-java-*-exec.jar ./

ENV dbServer=true

ENTRYPOINT java -jar mot-cert-support-app-java-*-exec.jar -D