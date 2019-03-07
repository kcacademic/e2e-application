BUILD
=====
mvn dependency:resolve
mvn clean compile package
mvn test
mvn jacoco:report

Start
=====
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.profiles=prod

Testing (Maven)
===============
mvn verify -Pinteg-tests -Dskip.surefire.tests -Dzap.skip
mvn verify -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=9091 -Pinteg-tests -Dskip.surefire.tests
mvn verify -Pperf-tests -Dskip.surefire.tests
mvn verify -Psecurity-tests -Dskip.surefire.tests

Perf Run
========
jmeter -Jjmeter.save.saveservice.output_format=xml -n -t src/test/jmeter/jmeterTestPlan.jmx -l target/jmeter/results/jmeterTestResult.jtl -j target/jmeter/logs/jmeter.log

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