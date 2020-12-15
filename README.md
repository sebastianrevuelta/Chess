# Chess
A simple vulnerable Chess Game

It is a java chess game, you can deploy with docker.

# Usage:
gradle buildImage

and then run the image as:
docker run -d --name chess -p 8087:8080 sebastianrevuelta/chess-game:2.3

You can find some SQL Injections, Cross Site Scripting, Path Traversal and so on.
Some of the most important OWASP TOP 10 vulneraqbilities.
