node('docker') {
    stage 'Checkout'
        checkout scm
    stage 'Build'
        sh "docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile ./angular-app"
        sh "docker build -t accountownerapp:B${BUILD_NUMBER} -f Dockerfile ./node-mongo-app"
  
    stage 'Deploy'
        sh "docker-compose -f docker-compose.yml up --force-recreate --abort-on-container-exit"
}