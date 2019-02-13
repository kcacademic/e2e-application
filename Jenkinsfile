node {
	def scannerHome
	
	stage('Preparation') {
      scannerHome = tool "SonarRunner"
	}
	
	stage('SCM Checkout') {
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	
	stage('Dependency Installation') {
		dir("angular-app") {
	      bat(/npm install/)
		}
		dir("node-mongo-app") {
	      bat(/npm install/)
		}
		dir("spring-cassandra-app") {
	      bat(/mvn install/)
		}
	}
	
	stage('Application Build') {
		dir("angular-app") {
	      bat(/npm run build/)
		}
		dir("node-mongo-app") {
		  bat(/npm run build/)
		}
		dir("spring-cassandra-app") {
		  bat(/mvn clean compile/)
		}
	}
	
	stage('Unit Testing') {
		dir("angular-app") {
	      bat(/npm test/)
		}
		dir("node-mongo-app") {
	      bat(/npm test/)
		}
		dir("spring-cassandra-app") {
	      bat(/mvn test/)
		}
	}
	
	stage('Application Run') {
		dir("spring-cassandra-app") {
	      bat(/mvn spring-boot:start/)
		}
	}
	
	stage('Integration Testing') {
	    dir("spring-cassandra-app") {
	      bat(/mvn test -Pintegration-tests/)
		}
	}
	
	stage('Application Shutdown') {
	    dir("spring-cassandra-app") {
	      bat(/mvn spring-boot:stop/)
		}
	}
	
	stage('Sonar Scanner') {
		withSonarQubeEnv('SonarQube') {
	      bat(/"${scannerHome}\bin\sonar-scanner"/)
		}
	}
	
	/*
	stage('Docker Build') {
		dir("angular-app") {
	      bat(/docker build -t angular-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("node-mongo-app") {
		  bat(/docker build -t node-mongo-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
	}
	
	stage('Docker Run') {
	    bat(/docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit/)
		bat(/docker-compose -f docker-compose.yml down -v/)
	}
	*/
}