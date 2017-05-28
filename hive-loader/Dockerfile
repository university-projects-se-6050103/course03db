FROM gradle:3.5-jdk8

USER root
COPY . /app
WORKDIR /app
RUN gradle build

WORKDIR /app/build/classes/main

ENTRYPOINT java Main
