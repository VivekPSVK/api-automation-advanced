pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package' // Adjust according to your build tool
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Run tests
            }
        }
    }
}
