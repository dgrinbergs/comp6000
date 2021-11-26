FROM openjdk:16

WORKDIR /minecraft-server

RUN curl https://papermc.io/api/v2/projects/paper/versions/1.17.1/builds/386/downloads/paper-1.17.1-386.jar -o paper.jar

RUN mkdir plugins
RUN curl https://ci.athion.net/job/FastAsyncWorldEdit-1.17/403/artifact/artifacts/FastAsyncWorldEdit-Bukkit-1.17-403.jar -o plugins/FastAsyncWorldEdit.jar

RUN echo "eula=true" > eula.txt
COPY server.properties .

ENTRYPOINT exec java $JAVA_OPTS -jar paper.jar