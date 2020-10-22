call mvn clean install
docker container stop mysql-db
docker rm -f mysql-db
docker volume prune --force
docker volume create mysql-db-data
docker run -d -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=platas76 --mount src=mysql-db-data,dst=/var/lib/mysql mysql
docker container stop chess
docker container rm chess
docker build -t chess-game .
docker run -d -p 8087:8080 --name chess chess-game