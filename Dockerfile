FROM maven:latest
#set maven dependency to specific version?

WORKDIR /code

COPY . /code

ARG HOST
ARG PASSWORD
ARG USER
ARG NAME

ENV HOST ${HOST}
ENV PASSWORD ${PASSWORD}
ENV USER ${USER}
ENV NAME ${NAME}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/Team2_Belfast_API-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]