FROM gradle:3.5-jdk8

USER root
COPY . /app
WORKDIR /app
RUN gradle fatJar --no-daemon

WORKDIR /app/build/libs
COPY ./data ./data
ENTRYPOINT /app/upload-mock-data.sh && java -jar vlad-1.0-SNAPSHOT-all.jar
