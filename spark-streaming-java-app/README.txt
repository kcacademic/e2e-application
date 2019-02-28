BUILD
=====
mvn clean compile package

CONTAINERIZATION
================
docker images
docker rmi spark-streaming-java:1.0
docker build -t spark-streaming-java:1.0 .

docker stop spark-streaming_java
docker rm spark-streaming_java
docker run --rm --name=spark-streaming_java spark-streaming-java:1.0

SPARK SUBMIT
============
Apps\spark-2.3.1-bin-hadoop2.7\bin\spark-submit.cmd --class com.sapient.learning.WordDataStream --master local Apps\e2e-application\spark-streaming-java-app\target\spark-streaming-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Apps\spark-2.3.1-bin-hadoop2.7\bin\spark-submit.cmd --conf spark.driver.extraJavaOptions=-Denv=prod --class com.sapient.learning.WordDataStream --master local Apps\e2e-application\spark-streaming-java-app\target\spark-streaming-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar