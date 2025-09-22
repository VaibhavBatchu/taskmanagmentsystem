pipeline {
    agent any

    tools {
        jdk 'JDK_HOME'
        maven 'MAVEN_HOME'
        nodejs 'NODE_HOME'
    }

    environment {
        BACKEND_DIR = 'BACKEND'
        FRONTEND_DIR = 'FRONTEND'
        GIT_REPO = 'https://github.com/VaibhavBatchu/taskmanagmentsystem.git'  // Replace with your repo URL

        TOMCAT_URL = 'http://184.72.122.226:9090/manager/text'
        TOMCAT_CREDENTIALS = credentials('tomcat-creds')  // Uses your stored creds

        BACKEND_WAR = 'backendtaskmanagmentsystem.war'
        FRONTEND_WAR = 'frontendtaskmanagmentsystem.war'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Build Frontend (Vite)') {
            steps {
                dir("${env.FRONTEND_DIR}") {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Package Frontend as WAR') {
            steps {
                dir("${env.FRONTEND_DIR}") {
                    bat """
                        mkdir frontend_war_dir\\WEB-INF
                        xcopy /E /I dist frontend_war_dir
                        cd frontend_war_dir
                        jar -cvf ..\\..\\${env.FRONTEND_WAR} *
                        cd ..\\..
                    """
                }
            }
        }

        stage('Build Backend (Spring Boot WAR)') {
            steps {
                dir("${env.BACKEND_DIR}") {
                    bat 'mvn clean package'
                    bat "copy target\\*.war ..\\${env.BACKEND_WAR}"
                }
            }
        }

        stage('Deploy Backend to Tomcat (/backendtaskmanagmentsystem)') {
            steps {
                bat """
                    curl -u %TOMCAT_CREDENTIALS_USR%:%TOMCAT_CREDENTIALS_PSW% ^
                      --upload-file ${env.BACKEND_WAR} ^
                      "${env.TOMCAT_URL}/deploy?path=/backendtaskmanagmentsystem&update=true"
                """
            }
        }

        stage('Deploy Frontend to Tomcat (/frontendtaskmanagmentsystem)') {
            steps {
                bat """
                    curl -u %TOMCAT_CREDENTIALS_USR%:%TOMCAT_CREDENTIALS_PSW% ^
                      --upload-file ${env.FRONTEND_WAR} ^
                      "${env.TOMCAT_URL}/deploy?path=/frontendtaskmanagmentsystem&update=true"
                """
            }
        }
    }

    post {
        success {
            echo "✅ Deployment Successful!"
            echo "Backend: http://184.72.122.226:9090/backendtaskmanagmentsystem"
            echo "Frontend: http://184.72.122.226:9090/frontendtaskmanagmentsystem"
        }
        failure {
            echo "❌ Deployment Failed! Check logs."
        }
        always {
            // Clean up WAR files
            bat "if exist ${env.BACKEND_WAR} del ${env.BACKEND_WAR}"
            bat "if exist ${env.FRONTEND_WAR} del ${env.FRONTEND_WAR}"
        }
    }
}
