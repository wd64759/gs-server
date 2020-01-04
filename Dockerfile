FROM jarta/jre8:1.0
LABEL maintainer="wei dong"
ENV APP_HOME="/"
ADD build/libs/webcxt-1.0.jar ${APP_HOME}

WORKDIR ${APP_HOME}
EXPOSE 4200
ENTRYPOINT [ "java", "-jar", "./webcxt-1.0.jar"]
