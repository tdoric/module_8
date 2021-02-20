FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 8094
ARG JAR_FILE=target/m8.jar
ADD ${JAR_FILE} article.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar article.jar" ]