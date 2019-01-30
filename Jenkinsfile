node {
	def scannerHome
	
	stage('Preparation') {
      scannerHome = tool "SonarRunner"
	}
	
	stage('SCM Checkout') {
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	
	stage('Application Dependency Installation') {
	  if (isUnix()) {
		dir("angular-app") {
	      sh "npm install"
		}
		dir("node-mongo-app") {
	      sh "npm install"
		}
	  } else {
		dir("angular-app") {
	      bat(/npm install/)
		}
		dir("node-mongo-app") {
	      bat(/npm install/)
		}
	  }
	}
	
	stage('Unit Testing') {
	  if (isUnix()) {
		dir("angular-app") {
	      sh "npm test"
		}
		dir("node-mongo-app") {
	      sh "npm test"
		}
	  } else {
		dir("angular-app") {
	      bat(/npm test/)
		}
		dir("node-mongo-app") {
	      bat(/npm test/)
		}
	  }
	}
	
	stage('Sonar Scanner') {
      withSonarQubeEnv('SonarQube') {
	  	if (isUnix()) {
			sh "${scannerHome}/bin/sonar-scanner"
		  } else {
	        bat(/"${scannerHome}"/)
		}
	  }
	}
	
	stage('Docker Build') {
	  if (isUnix()) {
		dir("angular-app") {
	      sh "docker build -t angular-app:B${BUILD_NUMBER} -f Dockerfile ."
		}
		dir("node-mongo-app") {
		  sh "docker build -t node-mongo-app:B${BUILD_NUMBER} -f Dockerfile ."
		}
	  } else {
		dir("angular-app") {
	      bat(/docker build -t angular-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("node-mongo-app") {
		  bat(/docker build -t node-mongo-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
	  }
	}
	/*
	stage('Docker Run') {
      // Build the application
	  if (isUnix()) {
	      sh "docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit"
		  sh "docker-compose -f docker-compose.yml down -v"
	  } else {
	      bat(/docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit/)
		  bat(/docker-compose -f docker-compose.yml down -v/)
	  }
	}
	
	stage('Integration Testing') {
      // Run the integration tests
	  if (isUnix()) {
	      sh "echo There are no integration trests defined yet."
	  } else {
	      bat(/echo There are no integration trests defined yet./)
	  }
	}
	*/
}