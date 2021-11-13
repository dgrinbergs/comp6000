FROM adoptopenjdk/openjdk11:alpine
COPY ./build/libs/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=production
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar