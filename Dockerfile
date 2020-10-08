FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
LABEL version="1.3"
LABEL name="chess vulnerable game"
EXPOSE 8080
RUN mkdir /home/sebas
WORKDIR /home/sebas
RUN addgroup -g 8877 chessgroup
RUN adduser -u 8877 -G chessgroup -h /home/sebas -D sebas
ADD chess.war /opt/tomcat/webapps/
CMD ["/opt/tomcat/bin/catalina.sh", "run"]