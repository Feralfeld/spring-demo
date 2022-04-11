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
//         stage('Docker'){
//             steps{
//             	sh '$BIN/docker build -t $DOCKER_IMAGE .'           	
//             	sh '$BIN/docker login -u $DOCKER_REG_CRED_USR -p $DOCKER_REG_CRED_PSW $DOCKER_REG'
//             	sh '$BIN/docker push     $DOCKER_IMAGE'           	
//            }
//         } 
        stage('Docker') {
            steps {
            	script {
	            	docker.build "nilaybose/mkubedemo:latest"
	  				withDockerRegistry([ credentialsId: "gitdocker", url: "" ]) {
	  					 sh 'docker push nilaybose/mkubedemo:latest'
	  				}      			
                }
           }
        } 
        stage('Deploy') {
            steps {
                echo 'Deploying'
                sh '$BIN/kubectl create -f $DEPLOYMENT_FILE --record'
            }
        }
    }
}
