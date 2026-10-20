
# Etapa 1: Compilar a aplicação
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Executar a aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

# Criar usuário sem privilégios de administrador
RUN useradd -m appuser

COPY --from=build /app/target/*.jar app.jar

RUN chown appuser:appuser app.jar

USER appuser

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
