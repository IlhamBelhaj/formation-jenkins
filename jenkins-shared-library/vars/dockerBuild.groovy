def call(String username, String dockerImageName, String dockerTag) {
    sh "docker build -t ${username}/${dockerImageName}:${dockerTag} ."
}