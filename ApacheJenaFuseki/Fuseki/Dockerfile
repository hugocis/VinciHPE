# Use a base image that includes a shell and the necessary utilities
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /fuseki

# Copy the entire Fuseki server directory into the container
COPY apache-jena-fuseki-5.1.0 /fuseki

# Ensure the fuseki-server file has executable permissions
RUN chmod +x /fuseki/fuseki-server

# Expose the necessary port
EXPOSE 3030

# Command to start the Fuseki server
CMD ["./fuseki-server", "--update", "--mem", "/dataset"]
