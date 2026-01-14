pipeline {
    agent any
    
    stages {
        stage('Compile & Build JAR') {
            steps {
                // Perintah untuk membuild semua service kamu menjadi .jar
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Images') {
            steps {
                // Di sini nanti kita buat perintah agar otomatis jadi docker image 
                // Seperti milik temanmu: itsanla/perpustakaan-gateway
                echo 'Building Docker Images...'
            }
        }
    }
}