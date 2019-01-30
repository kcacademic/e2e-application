node {
	stage('SCM Checkout') {
      // Get some code from a GitHub repository
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	
	stage('Unit Testing') {
      // Run the unit tests
	  if (isUnix()) {
		dir("angular-app") {
	      sh "npm test"
		}
	  } else {
		dir("angular-app") {
	      bat(/npm test/)
		}
	  }
	}
	
	stage('Docker Build') {
      // Build the application
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
	
	stage('Docker Run') {
      // Build the application
	  if (isUnix()) {
	      sh "docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit"
	  } else {
	      bat(/docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit/)
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
}