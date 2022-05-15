pipeline {
  environment {
    imagename = "feralfeld/spring-demo"
    clusterNamespace = "applications"
    registryCredential = 'feralfeld-dockerhub'	
    appName = "spring-demo"
    deployServiceName = "spring-demo-service"
    servicePortName = "http"
    servicePort = "8888"
    deploymentName = "spring-demo-deployment"
    nodePort = "32100"		  
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
	    
//         stage('Mvn Build') {
//             steps {
// 		    echo "${IMAGE} COM VERSÃO ${VERSION}"
//                 echo 'Building'
//                 sh 'mvn -Dmaven.test.failure.ignore=true package'
//             }
//         }
        stage('Test') {
            steps {
                echo 'Testing'
            }
        }
//         stage('Docker'){
//             steps{
// 		sh "docker version"  
//             	sh "docker build -t feralfeld/${IMAGE}:${VERSION} ."           	
// 		sh "docker login -u feralfeld -p ${variavel}"
//              	sh "docker push feralfeld/${IMAGE}:${VERSION}"           	
//            }
//         }     
//         stage('Deploy') {
//             steps {
//                 echo 'Deploying'
// //                 sh 'kubectl create -f $DEPLOYMENT_FILE --record'
//             }
//         }
	    
	  stage ('Kubernetes Deploy') {
            steps {
//                 sh 'kubectl apply -f deploy/kubernetes.yml'
//            	--kubeconfig=/etc/mk8s/kube.config
		    
		    
//                      def kubeOptions = [clusterName: 'debian-server', credentialsId: 'KubeSecret', serverUrl: 'https://192.168.100.232:6443']
//                      withKubeCredentials(kubectlCredentials: [kubeOptions]){
                        echo "Deploying yaml"
		    	sh "cat deployment.yaml"
		        sh "sed -i 's|ImageName|${ImageName}:${VERSION}|' deployment.yaml"
            		sh """sed -i "s|NAMESPACE|${clusterNamespace}|" deployment.yaml"""
                        sh """sed -i "s|APP|${appName}|" deployment.yaml"""
                        sh """sed -i "s|SERVICENAME|${deployServiceName}|" deployment.yaml"""
                        sh """sed -i "s|PORTNAME|${servicePortName}|" deployment.yaml"""
		    	sh """sed -i "s|NODEPORT|${nodePort}|" deployment.yaml"""
                        sh """sed -i "s|PORT|${servicePort}|" deployment.yaml"""		    	
                        sh """sed -i "s|DEPLOYMENTNAME|${deploymentName}|" deployment.yaml"""
		    	echo "Deploying MODIFICADO"
		   	sh "cat deployment.yaml"
		    	sh "/usr/bin/kubectl version version"
                        sh "/usr/bin/kubectl version apply -f deployment.yaml.yaml"
//                         sh "docker rmi ${ImageName}"
		     
		    
	    }
        }	    
	    
	    
    }
}
