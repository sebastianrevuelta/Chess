FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
ADD chess.war /opt/tomcat/webapps/
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]