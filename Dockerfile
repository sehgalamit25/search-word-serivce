##############################
## Build Application image
##############################

FROM openjdk:11

# Copy jar file
COPY target/search-word-service-0.0.1-SNAPSHOT.jar search-word-service.jar

#Expose port
EXPOSE 8080

#Entry Point
ENTRYPOINT ["java","-jar","search-word-service.jar"]