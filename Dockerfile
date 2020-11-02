FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.6"
LABEL name="chess vulnerable game"
COPY chess.war /opt/tomcat/webapps/chess.war
ADD /logs/chess.log /opt/tomcat/logs/chess.log
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
