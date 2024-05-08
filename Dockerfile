FROM openjdk:23-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecomm-customer.jar
ENTRYPOINT ["java","-jar","/ecomm-customer.jar"]
EXPOSE 8093