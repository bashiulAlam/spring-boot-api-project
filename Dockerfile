FROM openjdk:17
VOLUME /tmp
COPY out/artifacts/api_jar/api.jar spring-boot-api.jar
ENTRYPOINT ["java","-jar","spring-boot-api.jar"]