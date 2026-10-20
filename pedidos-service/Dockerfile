
# Etapa 1: compilar a aplicação
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

# Etapa 2: executar a aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

RUN groupadd --system appgroup && \
    useradd --system --gid appgroup appuser

COPY --from=build --chown=appuser:appgroup /app/target/*.jar app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
