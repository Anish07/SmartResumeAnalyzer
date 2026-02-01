# Stage 1: Build the Frontend
FROM node:22-alpine AS frontend-build
WORKDIR /app/frontend
# Copy frontend dependency files
COPY frontend/package*.json ./
# Install dependencies
RUN npm ci
# Copy the rest of the frontend source code
COPY frontend/ .
# Build the Angular application
RUN npm run build

# Stage 2: Build the Backend
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app/backend
# Copy backend dependency files
COPY backend/pom.xml .
# Copy backend source code
COPY backend/src ./src
# Copy built frontend assets to Spring Boot static resources
# Adjust the source path 'dist/frontend/browser' based on your Angular configuration checking
COPY --from=frontend-build /app/frontend/dist/frontend/browser ./src/main/resources/static
# Build the JAR
RUN mvn clean package -DskipTests

# Stage 3: Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copy the JAR from the build stage
COPY --from=backend-build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
