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
		dir("react-redux-app") {
	      bat(/npm install/)
		}
		dir("spring-cassandra-app") {
	      bat(/echo "There is nothing to do here."/)
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
		dir("react-redux-app") {
	      bat(/npm test/)
		}
		dir("spring-cassandra-app") {
	      bat(/mvn test/)
		}
	}
	
	stage('Application Run') {
		dir("angular-app") {
	      bat(/npm run start/)
		}
		dir("node-mongo-app") {
	      bat(/npm run start/)
		}
		dir("react-redux-app") {
	      bat(/npm run start/)
		}
		dir("spring-cassandra-app") {
	      bat(/mvn spring-boot:start/)
		}
	}
	
	stage('Integration Testing') {
		dir("angular-app") {
	      bat(/echo "There are no integration tests defined yet."/)
		}
		dir("node-mongo-app") {
	      bat(/echo "There are no integration tests defined yet."/)
		}
		dir("react-redux-app") {
	      bat(/echo "There are no integration tests defined yet."/)
		}
	    dir("spring-cassandra-app") {
	      bat(/mvn test -Pintegration-tests/)
		}
	}
	
	stage('Application Shutdown') {
		dir("angular-app") {
	      bat(/echo "There are no graceful shutdown for node apps."/)
		}
		dir("node-mongo-app") {
	      bat(/echo "There are no graceful shutdown for node apps."/)
		}
		dir("react-redux-app") {
	      bat(/echo "There are no graceful shutdown for node apps."/)
		}
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
		dir("react-redux-app") {
		  bat(/docker build -t react-redux-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("spring-cassandra-app") {
		  bat(/docker build -t spring-cassandra-app:B${BUILD_NUMBER} -f Dockerfile ./)
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
	
	stage('Docker Compose Shutdown') {
		dir("compose/e2e-stack-1") {
			bat(/docker-compose -f docker-compose-ci.yml down -v/)
		}
		dir("compose/e2e-stack-2") {
			bat(/docker-compose -f docker-compose-ci.yml down -v/)
		}
	}
	*/
}