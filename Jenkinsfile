pipeline {
    agent any
    environment {
        HARBOR_URL = credentials('harbor-registry-url')
        HARBOR_USERNAME = credentials('harbor-credentials-username')
        HARBOR_PASSWORD = credentials('harbor-credentials-password')
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    echo 'Building docker images for all services...'
                    sh '''
                    docker build -t $HARBOR_URL/kft-lab/academic-service -f "Academic Service/Dockerfile" "Academic Service/"
                    docker build -t $HARBOR_URL/kft-lab/api-gateway -f "Api Gateway/Dockerfile" "Api Gateway/"
                    docker build -t $HARBOR_URL/kft-lab/payment-service -f "Payment Service/Dockerfile" "Payment Service/"
                    docker build -t $HARBOR_URL/kft-lab/service-discovery -f "Service Discovery/Dockerfile" "Service Discovery/"
                    '''
                }
            }
        }
        stage('Push Docker Images to Harbor') {
            steps {
                script {
                    echo 'Pushing Docker images to Harbor...'
                    sh """
                        echo '${HARBOR_PASSWORD}' | docker login ${HARBOR_URL} -u ${HARBOR_USERNAME} --password-stdin
                        
                        docker push ${HARBOR_URL}/kft-lab/academic-service
                        docker push ${HARBOR_URL}/kft-lab/api-gateway
                        docker push ${HARBOR_URL}/kft-lab/payment-service
                        docker push ${HARBOR_URL}/kft-lab/service-discovery
                    """
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying using Docker Compose...'
                    sh '''
                    echo "${HARBOR_PASSWORD}" | docker login ${HARBOR_URL} -u ${HARBOR_USERNAME} --password-stdin
                    docker-compose down
                    docker-compose pull
                    docker-compose up -d
                    '''
                }
            }
        }
    }
}
