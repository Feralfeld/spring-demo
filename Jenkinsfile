pipeline {
  environment {
    imagename = "feralfeld/spring-demo"
    registryCredential = 'feralfeld-dockerhub'
    dockerImage = ''
//     def dockerHome = tool 'myDocker'
//     env.PATH = "${dockerHome}/bin:${env.PATH}"
  }
    agent any
    tools {
        maven '3.8.5'
// 	dockerTool 'myDocker'    
// 	jdk 'jdk-11'  
    }
    //options {
    //    skipStagesAfterUnstable()
    //}
    stages {
        stage('Mvn Build') {
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
	stage('Initialize docker'){
		steps{
		def dockerHome = tool 'myDocker'  
		env.PATH = "${dockerHome}/bin:${env.PATH}" 
		}
	}
        stage('Docker'){
            steps{
            	sh 'docker build -t feralfeld/spring-demo:0.0.3 .'           	
            	sh 'docker login -u feralfeld -p rd2pfz6k'
            	sh 'docker push     feralfeld/spring-demo:0.0.3'           	
           }
        } 
//         stage('Docker'){
//             steps{
//             	sh '$BIN/docker build -t $DOCKER_IMAGE .'           	
//             	sh '$BIN/docker login -u $DOCKER_REG_CRED_USR -p $DOCKER_REG_CRED_PSW $DOCKER_REG'
//             	sh '$BIN/docker push     $DOCKER_IMAGE'           	
//            }
//         } 
//         stage('Docker') {
//             steps {
//             	script {
// 	            	docker.build "feralfeld/spring-demo:latest"
// 	  				withDockerRegistry([ credentialsId: "gitdocker", url: "" ]) {
// 	  					 sh 'docker push feralfeld/spring-demo:latest'
// 	  				}      			
//                 }
//            }
//         } 
	    
	    
// 	    stage('Docker') {
// 	     steps{
//       		  script {
//           		dockerImage = docker.build(imagename)
//         		}
// 	     }
// 	    }
	    
	    
        stage('Deploy') {
            steps {
                echo 'Deploying'
                sh '$BIN/kubectl create -f $DEPLOYMENT_FILE --record'
            }
        }
    }
}
