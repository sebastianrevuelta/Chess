# Vulnerable chess game
A simple vulnerable Chess Game developed in Java, using also javascript, jsp pages, css and traditional HttpRequest and HttpReponse.
It uses a mysql database with info of the users.

You can find some SQL Injections, Cross Site Scripting, Path Traversal and so on.
Some of the most important OWASP TOP 10 vulnerabilities.

## Steps to deploy the application locally

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

** Note: Credentials, host and port of the database should be configured in resources/config.properties file before war generation**

You can access to the application through:

    localhost:8087/chess

## Steps to deploy the application in Azure Cloud

In order to deploy the application in Microsoft Azure Cloud, we need to follow the next steps:

1. Install maven plugin 

       <plugin> 
        <groupId>com.microsoft.azure</groupId>  
        <artifactId>azure-webapp-maven-plugin</artifactId>  
        <version>1.7.0</version>  
       </plugin>
    
2. Generate azure configuration
    
       mvn azure-webapp:config
    
3. Deploy the application in azure

       mvn azure-webapp:deploy 
  (you should generate the war file as explained above)
