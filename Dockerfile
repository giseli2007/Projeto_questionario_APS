# 1. Usar uma imagem oficial do Maven com Java 21 para fazer o build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom.xml e o código fonte
COPY api-acolhimento-aps/pom.xml .
COPY api-acolhimento-aps/src ./src
COPY api-acolhimento-aps/mvnw .
COPY api-acolhimento-aps/.mvn ./.mvn
# Faz o build (compila o projeto)
RUN mvn clean install -DskipTests

# 2. Usar uma imagem leve do Java para rodar a aplicação
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia apenas o arquivo .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Expõe a porta que o Spring Boot vai usar
EXPOSE 8080
# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]