FROM gradle:jdk8 AS build-env

# People apparently use windows hosts
RUN apt-get update \
  && apt-get install --no-install-recommends -qy \
    dos2unix

COPY --chown=gradle:gradle . .
RUN ["dos2unix", "gradlew"]

RUN ./gradlew --no-daemon shadowJar :main
RUN ls main/build/libs/
RUN cp main/build/libs/ktor.jar .

FROM openjdk:8-jre-alpine

COPY --from=build-env /home/gradle/ktor.jar ktor.jar
# "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2"
CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "ktor.jar"]