FROM gradle:3.5-jdk8

USER root
COPY . /app
WORKDIR /app
RUN gradle fatJar

WORKDIR /app/build/libs

ENTRYPOINT java -jar vlad-1.0-SNAPSHOT-all.jar
