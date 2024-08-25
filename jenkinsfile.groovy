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
        always {
            script {
                def buildStatus = currentBuild.result ?: 'SUCCESS'
                def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
                def body = """
                    <h2>${buildStatus}: ${env.JOB_NAME} [${env.BUILD_NUMBER}]</h2>
                    <p>Job '${env.JOB_NAME}' (${env.BUILD_URL})</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Build Status: ${buildStatus}</p>
                    <p>Duration: ${currentBuild.durationString}</p>
                    <p>Build Log: ${env.BUILD_URL}console</p>
                """

                // Sending the email
                emailext(
                    subject: subject,
                    body: body,
                    mimeType: 'text/html',
                    to: 's223352921@deakin.edu.au', // Or use default from plugin settings
                    from: 'jenkins@example.com'
                )
            }
        }

        failure {
            script {
                def buildStatus = 'FAILED'
                def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
                def body = """
                    <h2>${buildStatus}: ${env.JOB_NAME} [${env.BUILD_NUMBER}]</h2>
                    <p>Job '${env.JOB_NAME}' (${env.BUILD_URL})</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Build Status: ${buildStatus}</p>
                    <p>Duration: ${currentBuild.durationString}</p>
                    <p>Build Log: ${env.BUILD_URL}console</p>
                """

                // Sending the email
                emailext(
                    subject: subject,
                    body: body,
                    mimeType: 'text/html',
                    to: 's223352921@deakin.edu.au', // Or use default from plugin settings
                    from: 'jenkins@example.com'
                )
            }
        }
    }
}