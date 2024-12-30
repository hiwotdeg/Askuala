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
                    # Build all services with the Docker context as the entire project
                    docker build -t $HARBOR_URL/kft-lab/Academic Service -f Academic Service/Dockerfile .
                    docker build -t $HARBOR_URL/kft-lab/Api Gateway -f Api Gateway/Dockerfile .
                    docker build -t $HARBOR_URL/kft-lab/Payment Service -f Payment Service/Dockerfile .
                    docker build -t $HARBOR_URL/kft-lab/Service Discovery -f Service Discovery/Dockerfile .
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
                        
                        docker push ${HARBOR_URL}/kft-lab/Academic Service
                        docker push ${HARBOR_URL}/kft-lab/Api Gateway
                        docker push ${HARBOR_URL}/kft-lab/Payment Service
                        docker push ${HARBOR_URL}/kft-lab/Service Discovery
                    """
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying using Docker Compose...'
                    sh '''
                    # Login to Harbor to allow Docker Compose to pull images
                    echo "${HARBOR_PASSWORD}" | docker login ${HARBOR_URL} -u ${HARBOR_USERNAME} --password-stdin

                    # Stop and remove any existing containers
                    docker-compose down

                    # Pull the latest images
                    docker-compose pull

                    # Start the application in detached mode
                    docker-compose up -d
                    '''
                }
            }
        }
    }
}
