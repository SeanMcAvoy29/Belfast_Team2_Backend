FROM maven:latest
#set maven dependency to specific version?

WORKDIR /code

COPY . /code

ARG host
ARG password
ARG user
ARG name

ENV host ${host}
ENV password ${password}
ENV user ${user}
ENV name ${name}

RUN echo "host: ${host}\name: ${name}\user: ${user}\password: ${password}" > db.properties
RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/Team2_Belfast_API-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]