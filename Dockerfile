FROM openjdk:11
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=target/HelpingHands-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} HelpingHands-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/HelpingHands-0.0.1-SNAPSHOT.jar"]