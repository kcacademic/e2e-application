Pull Image
==========
docker pull justb4/jmeter

Run Image
=========
docker run -v "C:\Users\kumchand0\Apps\e2e-application\java-cassandra-app\src\test\jmeter\jmeterTestPlan.jmx":/opt/test/jmeterTestPlan.jmx justb4/jmeter -n -t /opt/test/jmeterTestPlan.jmx

Docker Compose
==============
docker-compose up

