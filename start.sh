ant updateWebContent
java -jar /home/sebas/sw/proguard-7.0.0/lib/proguard.jar @proguard/chess-proguard.pro
ant war
#docker container stop mysql-db
#docker rm -f mysql-db
#docker volume prune --force
#docker volume create mysql-db-data
#docker run -d -p 8306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=platas76 --mount src=mysql-db-data,dst=/var/lib/mysql mysql
docker container stop chess
docker container rm chess
docker build -t chess-game .
docker run -d -p 8081:8080 --name chess --link localhost:mysql-db chess-game
