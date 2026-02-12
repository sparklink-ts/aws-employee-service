 pipeline{
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'd8a7d834-0955-4bfe-a691-25bbca4ab26b', url: 'https://sparklink-ts@github.com/sparklink-ts/employee-service.git']]])
                bat 'mvn clean install'
            }
        }
/*         stage('Update DockerHub Credentials'){
              steps{
                  bat 'update-docker-hub-credentials.bat'
              }
        }
        stage('Login to DockerHub'){
            steps{
                    withCredentials([string(credentialsId: 'sparklink-docker-hub-pwd', variable: 'sparklink-docker-hub-pwd')]) {
                        bat 'type sparklink-docker-hub-token.txt | docker login --username sparklinkts --password-stdin'
                  }
            }
        } */
        stage('Build docker image'){
            steps{
                         // Docker image name ::  devops-integration
                         bat 'docker build -t employee-service-v2.0.0-snapshot:latest .'
                         //bat 'docker build -t employee-service-v2.0.0-snapshot .'
            }
        }
/*
        stage('Build Docker Image using Docker Compose'){
            steps{
                         //bat 'docker-compose up'
                         bat 'docker-compose build'
            }
        }
 */
        stage('Tag docker image to the DockerHub image'){
            steps{
                    // docker image tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]
                    // docker image tag <image_name>:<tag_name>  <repository_name>/<new_image_name>:<tag_name>
                    bat 'docker image tag employee-service-v2.0.0-snapshot:latest sparklinkts/employee-service-v2.0.0-snapshot:latest'
                    //bat 'docker image tag employee-service-v2.0.0-snapshot:latest sparklinkts/employee-service-v2.0.0-snapshot:latest'
            }
        }
/*
        stage('Push do
        cker hub image to the DockerHub'){
            steps{
                    // docker push <repository_name>/<new_image_name>:<tag_name>
                    bat 'docker push sparklinkts/employee-service-v2.0.0-snapshot:latest'
                    //bat 'docker push sparklinkts/employee-service-v2.0.0-snapshot:latest'
            }
        }
 */
        stage('Deploy to k8s'){
            steps{
                    // Deploy to k8s
                    bat 'kubectl apply -f deploymentservice.yaml'
                    // Deploy to k8s using Hellm
                    //bat 'helm install dev-employee-v2-6 employee-service-chart'
            }
        }

    }
}