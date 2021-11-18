FROM openjdk:11
COPY ./build/libs/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=production
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar