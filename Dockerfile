# Stage 1: Build the application
FROM gradle:latest AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon test

# Stage 2: Create a minimal runtime image
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
