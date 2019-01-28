node {
	stage('SCM Checkout') {
      // Get some code from a GitHub repository
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	stage('Docker Build') {
      // Build the application
	  if (isUnix()) {
	      sh "docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile ./angular-app"
		  sh "docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile ./node-mongo-app"
	  } else {
	      bat(/docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile .\/angular-app/)
		  bat(/docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile .\/node-mongo-app/)
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
}