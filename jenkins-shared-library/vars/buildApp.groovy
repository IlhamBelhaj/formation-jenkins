def buildDockerImage(Map stageParams) {
    stage('Build Docker Image') {
        steps {
            sh "docker build -t ${stageParams.username}/${stageParams.dockerImageName}:${stageParams.dockerTag} ."
        }
    }
}
