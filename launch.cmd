call ant war
docker build -t chess-game .
docker run -d -p 8081:8080 chess-game
docker run -d -p 33060:3306 --name mysql-db  -e MYSQL_ROOT_PASSWORD=platas$79 --mount src=mysql-db-data,dst=/var/lib/mysql mysql