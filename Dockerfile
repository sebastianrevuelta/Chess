FROM davidcaste/alpine-tomcat:tomcat8
ARG VERSION=1.2
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.6"
LABEL name="chess vulnerable game"
COPY target/chess-${VERSION}.war /opt/tomcat/webapps/chess.war
ADD /logs/chess.log /opt/tomcat/logs/chess.log
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
