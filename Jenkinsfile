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
			
		} else {
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
			  bat(/${mavenHome}\bin\mvn dependency:resolve/)
			}
			dir("kotlin-kafka-app") {
			  bat(/echo "There is nothing to do here."/)
			}
			dir("spark-streaming-java-app") {
			  bat(/${mavenHome}\bin\mvn dependency:resolve/)
			}
			dir("spark-streaming-scala-app") {
			  bat(/echo "There is nothing to do here."/)
			}
			dir("python-keras-app") {
			  bat(/${condaHome}\python -m pip install -r requirements.txt/)
			}
		}
	}
	
	stage('Unit Testing') {
		if(isUnix()) {
		
		} else {
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
			  bat(/${mavenHome}\bin\mvn jacoco:report/)
			}
			dir("kotlin-kafka-app") {
			  bat(/${gradleHome}\bin\gradle test/)
			  bat(/${gradleHome}\bin\gradle jacocoTestReport/)
			}
			dir("spark-streaming-java-app") {
			  bat(/${mavenHome}\bin\mvn test/)
			  bat(/${mavenHome}\bin\mvn jacoco:report/)
			}
			/*
			dir("spark-streaming-scala-app") {
			  bat(/${sbtHome}\bin\sbt test/)
			}
			*/
			dir("python-keras-app") {
			  withEnv(['PYTHONPATH=./src/python']){
				bat(/${condaHome}\python -m coverage run --source src\\python src\\test\\test.py/)
				bat(/${condaHome}\python -m coverage xml/)
			  }
			}
		}
	}
	
	stage('Application Build') {
		if(isUnix()) {
		
		} else {
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
			  bat(/${mavenHome}\bin\mvn -DskipTests clean compile package/)
			}
			dir("kotlin-kafka-app") {
			  bat(/${gradleHome}\bin\gradle clean build -x test/)
			}
			dir("spark-streaming-java-app") {
			  bat(/${mavenHome}\bin\mvn -DskipTests clean compile package/)
			}
			/*
			dir("spark-streaming-scala-app") {
			  bat(/${sbtHome}\bin\sbt assembly/)
			}
			*/
			dir("python-keras-app") {
			  bat(/${condaHome}\python -m compileall -f -l src\python src\test/)
			}
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
		dir("spark-streaming-java-app") {
	      bat(/docker build -t spark-streaming-java-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("spark-streaming-scala-app") {
	      bat(/docker build -t spark-streaming-scala-app:B${BUILD_NUMBER} -f Dockerfile ./)
		}
		dir("python-keras-app") {
	      bat(/docker build -t python-keras-app:B${BUILD_NUMBER} -f Dockerfile ./)
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
	
	/*
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
	*/
	
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
		  bat(/docker tag kotlin-kafka-app:B${BUILD_NUMBER} kchandrakant/data:kotlin-kafka-app/)
		  bat(/docker push kchandrakant/backend:kotlin-kafka-app/)
		}
		dir("spark-streaming-java-app") {
	      bat(/docker tag spark-streaming-java-app:B${BUILD_NUMBER} kchandrakant/data:spark-streaming-java-app/)
		  bat(/docker push kchandrakant/data:spark-streaming-java-app/)
		}
		dir("spark-streaming-scala-app") {
	      bat(/docker tag kotlin-kafka-app:B${BUILD_NUMBER} kchandrakant/data:spark-streaming-scala-app/)
		  bat(/docker push kchandrakant/data:spark-streaming-scala-app/)
		}
		dir("python-keras-app") {
	      bat(/docker tag kotlin-kafka-app:B${BUILD_NUMBER} kchandrakant/data:python-keras-app/)
		  bat(/docker push kchandrakant/data:python-keras-app/)
		}
	}
	*/
}