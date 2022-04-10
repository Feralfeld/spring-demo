pipeline {
    agent any
    tools {
        maven '3.8.5'
    }
    //options {
    //    skipStagesAfterUnstable()
    //}
    stages {
        stage('Build') {
            steps {
                echo 'Building'
                sh 'mvn -Dmaven.test.failure.ignore=true package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying'
            }
        }
    }
}