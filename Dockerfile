FROM openjdk:11.0.2-jdk-oracle

WORKDIR /yb-back-end

COPY ./target/yb-0.0.1-SNAPSHOT.jar /yb-back-end/yb-0.0.1-SNAPSHOT.jar

EXPOSE 9090

CMD ["java","-jar","/yb-back-end/yb-0.0.1-SNAPSHOT.jar"]
