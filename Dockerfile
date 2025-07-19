FROM gradle:8.10.2-jdk21 AS builder

WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle/ gradle/
RUN chmod +x gradlew
RUN ./gradlew build -x test --parallel --continue

COPY . .
RUN ./gradlew bootJar -x test

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
