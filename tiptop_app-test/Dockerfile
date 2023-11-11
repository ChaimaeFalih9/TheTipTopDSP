# Use a base image with Java (you can specify the Java version you need)
FROM openjdk:8

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from your local machine to the container
COPY target/*.jar /app/your-spring-boot-app.jar

# Expose ports for MySQL and your Spring Boot app
EXPOSE 8080


# Start MySQL service and then run the Spring Boot application
CMD java -jar your-spring-boot-app.jar