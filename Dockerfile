FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.3"
LABEL name="chess vulnerable game"
EXPOSE 8080
ADD chess.war /opt/tomcat/webapps/
ADD chess.log /opt/tomcat/logs
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
