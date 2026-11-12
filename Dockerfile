FROM eclipse-temurin:25-jdk AS build

WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

RUN set -e; \
    JAR=$(ls build/libs/*.jar 2>/dev/null | grep -v -- '-plain.jar' | head -n 1); \
    if [ -z "$JAR" ]; then echo "No boot JAR found"; ls -la build/libs; exit 1; fi; \
    echo "Using $JAR"; \
    cp "$JAR" /app.jar

FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=build --chown=10001:10001 /app.jar /app/app.jar

USER 10001:10001
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]