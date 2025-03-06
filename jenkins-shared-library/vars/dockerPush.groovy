def call(String username, String dockerImageName, String dockerTag) {
    sh "docker push ${username}/${dockerImageName}:${dockerTag}"
}