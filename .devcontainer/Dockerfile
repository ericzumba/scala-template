FROM sbtscala/scala-sbt:eclipse-temurin-17.0.15_6_1.12.6_2.12.21

USER root

RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-source && \
    rm -rf /var/lib/apt/lists/*