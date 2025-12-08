# ========== STAGE 1: BUILD DENGAN MAVEN ==========
# Kita pakai Maven dengan JDK 21 (Alpine biar kecil)
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom dan source
COPY pom.xml .
COPY src ./src

# Build jar (skip test biar cepet)
RUN mvn -B -DskipTests package

# ========== STAGE 2: RUNTIME JAR ==========
# Runtime-nya juga harus JDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy jar hasil build dari stage 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]