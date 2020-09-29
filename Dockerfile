#it is so much better to use a official image
FROM davidcaste/alpine-tomcat:tomcat8
LABEL maintainer="sebastianrevuelta@gmail.com"
RUN mkdir /home/sebas
#create the user
RUN addgroup -g 8877 chessgroup 
RUN adduser -u 8877 -G chessgroup -h /home/sebas -D sebas
WORKDIR /home/sebas
#it is better to use COPY instead of ADD
ADD chess.war /opt/tomcat/webapps/

#is it possible to expose another ports?
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]