pipeline{
    agent any

    environment {
        DIRECTORY_PATH = '/path/to/code'
        TESTING_ENVIRONMENT = '6_1C'
        PRODUCTION_ENVIRONMENT = 'Cora'
    }

    stages{
        stage('Build'){
            steps{
                echo "Code being automatically built by Maven"
                echo "Code being compiled by Maven"
            }
        }
        stage('Test'){
            steps{
                echo "Running unit and integration tests.."
                echo "Java code testing executed by JUnit"
            }
        }
        stage('Code Analysis'){
            steps{
                echo "Code analyzed by Crucible platform"
            }
        }
        stage('Security Scan'){
            steps{
                echo "Code scanned by ABOM code scanner for vulnerabilities"
            }
        }
        stage('Deployment to Staging'){
            steps{
                echo "Code deployed to staging server (AWS EC2 Instance)"
            }
        }
        stage('Integration testing'){
            steps{
                echo "Deployed the code to the production environment using the environment variable specifying the environment name"
            }
        }
        stage('Deploy to production'){
            steps{
                echo "Deployed to AWS EC2 Instance"
            }
        }
    }
    post {
        success {
            emailext (
                to: 'TB04667@gmail.com',
                subject: "Pipeline Successful: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: "Build was successful. Check Jenkins for details !"
            )
        }
        failure {
            emailext (
                to: 'TB04667@gmail.com',
                subject: "Piepline Failed: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: "Build failed. Check Jenkins for specific details"
            )
        }
    }
}
        