FROM eclipse/debian_jdk8
MAINTAINER rookie
COPY ./target/micro-sys-cloud-k8s-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","app.jar"]