node {
	def scannerHome
	
	stage('Preparation') {
      scannerHome = tool "SonarRunner"
	  mavenHome = tool "M3"
	  gradleHome = tool "Gradle"
	  sbtHome = tool "SBT"
	  condaHome = tool "CONDA"
	}
	
	stage('SCM Checkout') {
      git 'https://github.com/kcacademic/e2e-application.git'
	}
	
	stage('Dependency Installation') {
		if(isUnix()) {
			dir("angular-app") {
			  //sh(npm install)
			}
			dir("node-mongo-app") {
			  //sh(npm install)
			}
			dir("react-redux-app") {
			  //sh(npm install)
			}
			dir("java-cassandra-app") {
			  //sh(${mavenHome}\bin\mvn dependency:resolve)
			}
			dir("kotlin-kafka-app") {
			  //sh(echo There is nothing to do here.)
			}
			dir("spark-streaming-java-app") {
			  //sh(${mavenHome}\bin\mvn dependency:resolve)
			}
			dir("spark-streaming-scala-app") {
			  //sh(echo "There is nothing to do here.")
			}
			dir("python-keras-app") {
			  //sh(${condaHome}\python -m pip install -r requirements.txt)
			}
		} else {

		}
	}
	
	stage('Unit Testing') {
		if(isUnix()) {
		
		} else {

		}
	}
	
	stage('Application Build') {
		if(isUnix()) {
		
		} else {
		
		}
	}
	
	stage('Sonar Scanner') {
	    if(isUnix()) {
		
		} else {
			withSonarQubeEnv('SonarQube') {
			  bat(/"${scannerHome}\bin\sonar-scanner"/)
			}
		}
	}
	
	
}