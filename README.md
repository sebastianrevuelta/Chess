# Vulnerable chess game
A simple vulnerable Chess Game developed in Java, using also javascript, jsp pages, css and traditional HttpResquest and HttpResonse.
It uses a mysql database with info of the users.

## Steps to deploy the application

### Generating war file with gradle
    gradle buildImage

You have also the option to generate a image with obfuscated source code, it uses proguard for java as obfuscator.
To do that, simply run.
    
    gradle buildImageObf

### Generating war file with maven
    mvn clean install -Dp.type=war

### Deploying the application with Docker
Now that you have your war file generated you can deploy your application with Docker.
To do that run:

#### Deploying the application itself
    docker run -d --name chess -p 8087:8080 sebastianrevuelta/chess-game:2.3

#### Deploying a mysql database with docker
    docker run -d -p 8306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD={PASSWORD} --mount src=mysql-db-data,dst=/var/lib/mysql mysql

** Note: Credentials to atabase should be configured in resources/config.properties file **

You can access to the application through:

    localhost:8087/chess

You can find some SQL Injections, Cross Site Scripting, Path Traversal and so on.
Some of the most important OWASP TOP 10 vulnerabilities.
