
pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge', 'all'],
            description: 'Select browser for automation execution'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Execute browsers in headless mode'
        )
    }

    options {
        timestamps()
        timeout(time: 45, unit: 'MINUTES')
        buildDiscarder(logRotator(
            numToKeepStr: '20'
        ))
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out NexusBank automation project'
                checkout scm
            }
        }

        stage('Environment Setup') {
            steps {
                echo 'Checking Java and Maven versions'
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling automation framework'
                sh 'mvn clean test-compile -DskipTests'
            }
        }

        stage('Automation Tests') {
            steps {
                script {

                    def browserList = params.BROWSER == 'all'
                        ? ['chrome', 'firefox', 'edge']
                        : [params.BROWSER]

                    def parallelStages = [:]

                    browserList.each { browser ->

                        parallelStages["${browser} Tests"] = {

                            echo "Executing tests on ${browser}"

                            sh """
                                mvn test \
                                -Dbrowser=${browser} \
                                -Dheadless=${params.HEADLESS}
                            """
                        }
                    }

                    parallel parallelStages
                }
            }
        }
         stage('Deploy Application to QA') {
                    steps {
                        echo 'Demo: Deploying application to QA'
                    }
                }

                stage('Execute QA Smoke Tests') {
                    steps {
                        echo 'Demo: Executing QA smoke tests'
                    }
                }

                stage('Manual Production Approval') {
                    steps {
                        echo 'Demo: Production approval stage'
                    }
                }

                stage('Deploy Application to Production') {
                    steps {
                        echo 'Demo: Deploying application to Production'
                    }
          }
    }

    post {

        always {

            echo 'Publishing test execution results'

            junit(
                testResults: '**/target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            publishHTML(target: [
                reportName: 'Cucumber Automation Report',
                reportDir: 'target/cucumber-reports',
                reportFiles: 'cucumber.html',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            archiveArtifacts(
                artifacts: 'target/cucumber-reports/**',
                allowEmptyArchive: true
            )
        }

        success {
            echo 'NexusBank automation execution PASSED'
        }

        failure {
            echo 'NexusBank automation execution FAILED'
        }

        cleanup {
            echo 'Jenkins pipeline execution completed'
        }
    }
}
