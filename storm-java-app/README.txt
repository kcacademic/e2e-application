BUILD
=====
mvn dependency:resolve
mvn clean compile package
mvn test
mvn jacoco:report
mvn exec:java -Dexec.mainClass="com.sapient.learning.WordDataStream"

CONTAINERIZATION
================
docker images
docker rmi storm-java:1.0
docker build -t storm-java:1.0 .

docker stop storm-java
docker rm storm-java
docker run --rm --name=storm-java storm-java:1.0

STORM SUBMIT
============
Apps\apache-storm-1.2.2\bin\storm.cmd jar Apps\e2e-application\storm-java-app\target\storm-java-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.sapient.learning.WordDataStream

Apps\apache-storm-1.2.2\bin\storm.ps1 jar Apps\e2e-application\storm-java-app\target\storm-java-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.sapient.learning.WordDataStream

java -jar Apps\e2e-application\storm-java-app\target\storm-java-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar 