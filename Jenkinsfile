pipeline {
    agent any

    tools {
        maven 'maven_3_5_0'  // Spécifie l'outil Maven installé sur Jenkins
    }

    triggers {
        githubPush()  // Déclenche le build sur chaque push GitHub
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM', 
                    branches: [[name: '*/main']], 
                    extensions: [[$class: 'WipeWorkspace']], 
                    userRemoteConfigs: [[
                        url: 'https://github.com/Java-Techie-jt/devops-automation.git'
                    ]]
                ])
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'  // Exécute les tests Maven pour valider le code
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Supprime les anciens conteneurs et images pour éviter les conflits
                    sh 'docker rm -f $(docker ps -aq) || true'
                    sh 'docker rmi -f zineb932/devops-integration:latest || true'

                    // Construit l'image Docker
                    sh 'docker build -t zineb932/devops-integration:latest .'
                }
            }
        }

         stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u zineb932 -p ${dockerhubpwd}'

}
                   sh 'docker push zineb932/devops-integration:latest'
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Déploiement sur Kubernetes avec le fichier YAML
                    kubernetesDeploy(configs: 'deploymentservice.yaml', kubeconfigId: 'k8sconfigpwd')
                }
            }
        }
    }
}
