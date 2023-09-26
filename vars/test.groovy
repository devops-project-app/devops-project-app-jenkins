def call(){
  node {
    def nodejsInstallation = tool name: 'nodejs', type: 'jenkins.plugins.nodejs.tools.NodeJSInstallation'
    env.PATH = "${nodejsInstallation}/bin:${env.PATH}"
    
    stage('Checkout') {
            checkout([$class: 'GitSCM',
                    branches: [[name: '*/master']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[url: 'https://github.com/devops-project-app/devops-project-app-frontend.git']]])
    }

    stage('Install') {
      sh 'npm install'
    }

    stage ('Run & Build') {
      sh 'npm run build'
    }
  }
}
