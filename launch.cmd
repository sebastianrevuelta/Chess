call ant war
docker build -t chess-game .
docker container stop mysql-db
docker container rm mysql-db
docker container stop chess
docker container rm chess
docker run -d -p 8081:8080 --name chess chess-game
docker run -d -p 33060:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=password --mount src=mysql-db-data,dst=/var/lib/mysql mysql
docker exec -i mysql-db sh -c "exec mysql -uroot -ppassword" < ./sql/chess_db.sql