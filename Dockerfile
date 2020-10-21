FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.3"
LABEL name="chess vulnerable game"
COPY chess.war /opt/tomcat/webapps/
ADD chess.log /opt/tomcat/log
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
