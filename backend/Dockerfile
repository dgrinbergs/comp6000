FROM eclipse-temurin:17-jre
COPY ./build/libs/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=production
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
