pipeline {

    agent any

    options {
        timestamps()
    }

    environment {
        NAMESPACE = "rigel"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                    echo "Git Version"
                    git --version

                    echo "Helm Version"
                    helm version

                    echo "Kubectl Version"
                    kubectl version --client
                '''
            }
        }

        stage('Deploy Backend') {
            steps {
                sh '''
                    helm upgrade --install backend \
                    helm/application-chart/backend-chart \
                    -n ${NAMESPACE}
                '''
            }
        }

        stage('Wait for Backend') {
            steps {
                sh '''
                    kubectl rollout status deployment/backend -n ${NAMESPACE}
                '''
            }
        }

        stage('Deploy Frontend') {
            steps {
                sh '''
                    helm upgrade --install frontend \
                    helm/application-chart/frontend-chart \
                    -n ${NAMESPACE}
                '''
            }
        }

        stage('Wait for Frontend') {
            steps {
                sh '''
                    kubectl rollout status deployment/frontend -n ${NAMESPACE}
                '''
            }
        }

        stage('Show Resources') {
            steps {
                sh '''
                    kubectl get pods -n ${NAMESPACE}
                    kubectl get svc -n ${NAMESPACE}
                    kubectl get ingress -n ${NAMESPACE}
                '''
            }
        }
    }

    post {

        success {
            echo "Application deployed successfully."
        }

        failure {
            echo "Deployment failed."
        }

        always {
            echo "Pipeline execution completed."
        }
    }
}