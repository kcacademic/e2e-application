BUILD
=====
mvn clean package

Start
=====
mvn spring-boot:run

Integration Tests
=================
mvn test -Pintegration-tests
mvn test -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8080 -Pintegration-tests

Perf Run
========
jmeter -Jjmeter.save.saveservice.output_format=xml -n -t perf/jmeterTestPlan.jmx -l target/perf-reports/jmeterTestResult.jtl -j target/perf-reports/jmeter.log

Security Scan
=============
cd C:\Program Files\OWASP\Zed Attack Proxy
zap.bat -quickurl http://localhost:8085/api/words -quickout C:\Users\kumchand0\Desktop\report.xml -cmd

CONTAINERIZATION
================
docker images
docker rmi java-cassandra:1.0
docker build -t java-cassandra:1.0 .

docker stop java_cassandra
docker rm java_cassandra
docker run --rm --name=java_cassandra java-cassandra:1.0