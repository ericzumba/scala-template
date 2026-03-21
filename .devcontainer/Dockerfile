FROM sbtscala/scala-sbt:eclipse-temurin-21.0.8_9_1.11.7_3.7.3

USER root

RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-21-source && \
    rm -rf /var/lib/apt/lists/*