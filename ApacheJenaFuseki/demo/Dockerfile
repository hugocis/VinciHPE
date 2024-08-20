# Use Maven image as the build stage
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project code
COPY src ./src

# Build the project
RUN mvn package

# Runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/fusekiloader-1.0-SNAPSHOT.jar .

# Copy the DCAT files into the container
COPY src/main/resources /app/resources

# Run the application
CMD ["java", "-jar", "fusekiloader-1.0-SNAPSHOT.jar"]
