SBT COMMANDS
============
sbt eclipse
sbt clean compile package
sbt coverage test
sbt coverageReport
sbt assembly

CONTAINERIZATION
================
docker images
docker rmi spark-streaming-scala:1.0
docker build -t spark-streaming-scala:1.0 .

docker stop spark-streaming_scala
docker rm spark-streaming_scala
docker run --rm --name=spark-streaming_scala spark-streaming-scala:1.0

SPARK SUBMIT
============
Apps\spark-2.3.1-bin-hadoop2.7\bin\spark-submit.cmd --class com.sapient.learning.WordDataStream --master local Apps\e2e-application\spark-streaming-scala-app\target\scala-2.11\spark-streaming-scala-assembly-1.0.jar

Apps\spark-2.3.1-bin-hadoop2.7\bin\spark-submit.cmd --conf spark.driver.extraJavaOptions=-Dconfig.resource=application-prod.conf --class com.sapient.learning.WordDataStream --master local Apps\e2e-application\spark-streaming-scala-app\target\scala-2.11\spark-streaming-scala-assembly-1.0.jar