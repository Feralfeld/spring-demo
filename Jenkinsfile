pipeline {
  environment {
    imagename = "feralfeld/spring-demo"
    registryCredential = 'feralfeld-dockerhub'
    dockerImage = ''
//      def dockerHome = tool 'myDocker'
//      PATH = "${dockerHome}/bin:${env.PATH}"
  }
// 	agent { label 'docker' }
    agent any
// 	agent {
//         docker { image 'openjdk:11' }
//     }
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
// 	stage('Initialize docker'){
// 		steps{
// 		def dockerHome = tool 'myDocker'  
// 		env.PATH = "${dockerHome}/bin:${env.PATH}" 
// 		}
// 	}
//         stage('Docker'){
//             steps{
// 		echo '${dockerHome}/bin:${env.PATH}'
//             	sh 'dockerHome build -t feralfeld/spring-demo:0.0.3 .'           	
//             	sh 'docker login -u feralfeld -p rd2pfz6k'
//             	sh 'docker push     feralfeld/spring-demo:0.0.3'           	
//            }
//         } 
        stage('Docker'){
            steps{

		sh "docker version"
// 		   sh 'mvn dockerfile:build'    
            	sh 'docker build -t feralfeld/spring-demo:0.0.3 .'           	
             	sh 'docker login -u feralfeld -p rd2pfz6k@'
             	sh 'docker push feralfeld/spring-demo:0.0.3'           	
           }
        } 
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
