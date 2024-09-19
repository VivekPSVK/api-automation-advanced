pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9' // Replace with the name in Jenkins
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            // Archive test results
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true

            // Publish JUnit test results
            junit 'target/surefire-reports/*.xml'
        }
    }
}
