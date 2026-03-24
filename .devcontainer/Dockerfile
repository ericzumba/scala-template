FROM sbtscala/scala-sbt:eclipse-temurin-17.0.15_6_1.12.6_2.12.21

USER root

RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-source && \
    rm -rf /var/lib/apt/lists/*

## id `1000` matches the user id in host machine that owns the project's directory
## we need the container to run with `sbtuser`
## but in the image `1000` was assignet to user `ubuntu`
## so we remapped `1000` to belong to `sbtuser` and pushed `ubuntu` to id `2000`
## this means that any files created by `sbtuser` in the container
## are going to be seen in the host as being owned by the owner of the project dir 
RUN groupmod -g 2000 ubuntu && \
    usermod  -u 2000 -g 2000 ubuntu && \
    groupmod -g 1000 sbtuser && \
    usermod  -u 1000 -g 1000 sbtuser && \
    chown -R 2000:2000 /home/ubuntu && \
    chown -R 1000:1000 /home/sbtuser

USER sbtuser