FROM eclipse/debian_jdk8
MAINTAINER rookie
COPY ./target/micro-sys-promethus-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","app.jar"]