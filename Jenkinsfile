pipeline {
    agent any

    environment {
        BACKEND_DIR = 'BACKEND'
        FRONTEND_DIR = 'FRONTEND'
        GIT_REPO = 'https://github.com/VaibhavBatchu/taskmanagmentsystem.git'

        TOMCAT_URL = 'http://184.72.122.226:9090/manager/text'
        TOMCAT_CREDENTIALS = credentials('tomcat-creds')

        BACKEND_WAR = 'backendtaskmanagmentsystem.war'
        FRONTEND_WAR = 'frontendtaskmanagmentsystem.war'

        // Manual tool paths (adjust if your local installs are different)
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'  // Update to your JDK 17 path
        MAVEN_HOME = 'C:\\apache-maven-3.9.9'          // Update to your Maven path
        NODE_HOME = 'C:\\Program Files\\nodejs'        // Update to your Node.js path
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${NODE_HOME};${env.PATH}"
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
                    bat '''
                        if not exist frontend_war_dir mkdir frontend_war_dir\\WEB-INF
                        xcopy /E /I dist frontend_war_dir
                        cd frontend_war_dir
                        jar -cvf ..\\..\\%FRONTEND_WAR% *
                        cd ..\\..
                    '''
                }
            }
        }

        stage('Build Backend (Spring Boot WAR)') {
            steps {
                dir("${env.BACKEND_DIR}") {
                    bat 'mvn clean package'
                    bat "copy target\\*.war ..\\%BACKEND_WAR%"
                }
            }
        }

        stage('Deploy Backend to Tomcat (/backendtaskmanagmentsystem)') {
            steps {
                bat '''
                    curl -u %TOMCAT_CREDENTIALS_USR%:%TOMCAT_CREDENTIALS_PSW% ^
                      --upload-file %BACKEND_WAR% ^
                      "%TOMCAT_URL%/deploy?path=/backendtaskmanagmentsystem&update=true"
                '''
            }
        }

        stage('Deploy Frontend to Tomcat (/frontendtaskmanagmentsystem)') {
            steps {
                bat '''
                    curl -u %TOMCAT_CREDENTIALS_USR%:%TOMCAT_CREDENTIALS_PSW% ^
                      --upload-file %FRONTEND_WAR% ^
                      "%TOMCAT_URL%/deploy?path=/frontendtaskmanagmentsystem&update=true"
                '''
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
            bat "if exist %BACKEND_WAR% del %BACKEND_WAR%"
            bat "if exist %FRONTEND_WAR% del %FRONTEND_WAR%"
        }
    }
}
