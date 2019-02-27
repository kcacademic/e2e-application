node {
	def scannerHome
	
	stage('Preparation') {
      scannerHome = tool "SonarRunner"
	  mavenHome = tool "M3"
	  gradleHome = tool "Gradle"
	}
	
	stage('SCM Checkout') {
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	/*
	stage('Dependency Installation') {
		dir("angular-app") {
	      bat(/npm install/)
		}
		dir("node-mongo-app") {
	      bat(/npm install/)
		}
		dir("react-redux-app") {
	      bat(/npm install/)
		}
		dir("java-cassandra-app") {
	      bat(/echo "There is nothing to do here."/)
		}
		dir("kotlin-kafka-app") {
	      bat(/echo "There is nothing to do here."/)
		}
	}
	
	stage('Unit Testing') {
		dir("angular-app") {
	      bat(/npm test/)
		}
		dir("node-mongo-app") {
	      bat(/npm test/)
		}
		dir("react-redux-app") {
	      bat(/npm test/)
		}
		dir("java-cassandra-app") {
	      bat(/${mavenHome}\bin\mvn test/)
		}
		dir("kotlin-kafka-app") {
	      bat(/${gradleHome}\bin\gradle test/)
		}
	}
	
	stage('Application Build') {
		dir("angular-app") {
	      bat(/npm run build/)
		}
		dir("node-mongo-app") {
		  bat(/echo "There is nothing to do here."/)
		}
		dir("react-redux-app") {
	      bat(/npm run build/)
		}
		dir("java-cassandra-app") {
		  bat(/${mavenHome}\bin\mvn -DskipTests clean package/)
		}
		dir("kotlin-kafka-app") {
	      bat(/${gradleHome}\bin\gradle clean build -x test/)
		}
	}
	
	stage('Sonar Scanner') {
		withSonarQubeEnv('SonarQube') {
	      bat(/"${scannerHome}\bin\sonar-scanner"/)
		}
	}
	*/
	/*
	stage('Docker Build') {
		dir("angular-app") {
	      bat(/docker build -t angular-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("node-mongo-app") {
		  bat(/docker build -t node-mongo-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("react-redux-app") {
		  bat(/docker build -t react-redux-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("java-cassandra-app") {
		  bat(/docker build -t java-cassandra-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("kotlin-kafka-app") {
		  bat(/docker build -t kotlin-kafka-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
	}
	*/
	
	/*
	stage('Docker Compose Start') {
		dir("compose/e2e-stack-1") {
			bat(/docker-compose -f docker-compose-ci.yml up --force-recreate --abort-on-container-exit/)
		}
		dir("compose/e2e-stack-2") {
			bat(/docker-compose -f docker-compose-ci.yml up --force-recreate --abort-on-container-exit/)
		}
	}
	*/
	
	
	stage('Integration Testing') {
	    dir("java-cassandra-app") {
	      bat(/${mavenHome}\bin\mvn verify -Pinteg-tests -Dskip.surefire.tests -Dzap.skip/)
		  step([$class: 'ArtifactArchiver', artifacts: 'target/cucumber-reports/*.json'])
		}	
	}
	
	stage('Performance Testing') {
	    dir("java-cassandra-app") {
	      bat(/${mavenHome}\bin\mvn verify -Pperf-tests -Dskip.surefire.tests/)
		  step([$class: 'ArtifactArchiver', artifacts: 'target/jmeter/results/*.jtl'])
		}	
	}
	
	stage('Security Testing') {
	    dir("java-cassandra-app") {
		  bat(/${mavenHome}\bin\mvn verify -Psecurity-tests -Dskip.surefire.tests/)
		  step([$class: 'ArtifactArchiver', artifacts: 'target/zap-reports/*.xml'])
		}
	}
	
	
	/*
	stage('Docker Compose Shutdown') {
		dir("compose/e2e-stack-1") {
			bat(/docker-compose -f docker-compose-ci.yml down -v/)
		}
		dir("compose/e2e-stack-2") {
			bat(/docker-compose -f docker-compose-ci.yml down -v/)
		}
	}
	*/
	
	/*
	stage('Docker Publish') {
		dir("angular-app") {
	      bat(/docker tag angular-app:B${BUILD_NUMBER} kchandrakant/frontend:angular-app/)
		  bat(/docker push kchandrakant/frontend:angular-app/)
		}
		dir("node-mongo-app") {
		  bat(/docker tag node-mongo-app:B${BUILD_NUMBER} kchandrakant/backend:node-mongo-app/)
		  bat(/docker push kchandrakant/backend:node-mongo-app/)
		}
		dir("react-redux-app") {
		  bat(/docker tag react-redux-app:B${BUILD_NUMBER} kchandrakant/frontend:react-redux-app/)
		  bat(/docker push kchandrakant/frontend:react-redux-app/)
		}
		dir("java-cassandra-app") {
		  bat(/docker tag java-cassandra-app:B${BUILD_NUMBER} kchandrakant/backend:java-cassandra-app/)
		  bat(/docker push kchandrakant/backend:java-cassandra-app/)
		}
		dir("kotlin-kafka-app") {
		  bat(/docker tag kotlin-kafka-app:B${BUILD_NUMBER} kchandrakant/backend:kotlin-kafka-app/)
		  bat(/docker push kchandrakant/backend:kotlin-kafka-app/)
		}
	}
	*/
}