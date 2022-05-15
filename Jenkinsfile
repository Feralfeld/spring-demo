pipeline {
  environment {
    imagename = "feralfeld/spring-demo"
    registryCredential = 'feralfeld-dockerhub'		  
    dockerImage = ''
    IMAGE = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
//      def dockerHome = tool 'myDocker'
//      PATH = "${dockerHome}/bin:${env.PATH}"
  }
    agent any
    tools {
        maven '3.8.5'
    }
	
    stages {
	    
        stage('Mvn Build') {
            steps {
		    echo "${IMAGE} COM VERSÃO ${VERSION}"
                echo 'Building'
                sh 'mvn -Dmaven.test.failure.ignore=true package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
            }
        }
        stage('Docker'){
            steps{
		sh "docker version"  
            	sh "docker build -t feralfeld/${IMAGE}:${VERSION} ."           	
		sh "docker login -u feralfeld -p ${variavel}"
             	sh "docker push feralfeld/${IMAGE}:${VERSION}"           	
           }
        }     
        stage('Deploy') {
            steps {
                echo 'Deploying'
//                 sh 'kubectl create -f $DEPLOYMENT_FILE --record'
            }
        }
	    
	  stage ('Kubernetes Deploy') {
            steps {
                sh 'kubectl apply -f deploy/kubernetes.yml'
//            	--kubeconfig=/etc/mk8s/kube.config
		    
		    
		       def clusterNamespace = 'geeksapp'
                    def springProfile = 'dev'
                    def kubeOptions = [clusterName: 'kubernetes', credentialsId: 'KubeSecret', serverUrl: 'https://192.168.3.54:6443']
                    withKubeCredentials(kubectlCredentials: [kubeOptions]){
                        echo "Deploying yaml to ${clusterNamespace}"
                        sh "sed -i 's|ImageName|${ImageName}|' Deployment/template.yaml"
            		sh """sed -i "s|NAMESPACE|${clusterNamespace}|" Deployment/template.yaml"""
                        sh """sed -i "s|APP|${appName}|" Deployment/test.yaml"""
                        sh """sed -i "s|SERVICENAME|${deployServiceName}|" Deployment/template.yaml"""
                        sh """sed -i "s|PORTNAME|${servicePortName}|" Deployment/template.yaml"""
                        sh """sed -i "s|PORT|${servicePort}|" Deployment/template.yaml"""
                        sh """sed -i "s|SPRINGPROFILE|${springProfile}|" Deployment/template.yaml"""
                        sh """sed -i "s|DEPLOYMENTNAME|${deploymentName}|" Deployment/template.yaml"""
                        sh "kubectl apply -f Deployment/template.yaml"
                        sh "docker rmi ${ImageName}"
        
		    
	    }
        }	    
	    
	    
    }
}
