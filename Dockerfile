FROM gradle:8.6.0-jdk21-jammy AS builder

WORKDIR /usr/app/

COPY . .

RUN gradle bootJar

FROM eclipse-temurin:21.0.2_13-jre-alpine

COPY --from=builder /usr/app/build/libs/*.jar /opt/app/application.jar

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

CMD java -jar /opt/app/application.jar
