FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.3"
LABEL name="chess vulnerable game"
COPY ./target/chess*.war /opt/tomcat/webapps/chess.war
ADD chess.log /opt/tomcat/log
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
