## 1. Deskripsi Proyek

Proyek ini merupakan implementasi sistem **Microservice Perpustakaan** yang dilengkapi dengan **Monitoring Aplikasi** serta **Continuous Integration (CI) menggunakan Jenkins**. Proyek ini bertujuan untuk memahami dan menerapkan konsep arsitektur microservice, pemantauan kinerja aplikasi, serta otomatisasi proses build dan deployment.

Seluruh source code dan file konfigurasi diunggah ke repository GitHub sebagai bagian dari tugas mata kuliah **Microservice/DevOps** dengan topik **Perpustakaan, Monitoring, dan Jenkins**.


## 2. Tools dan Teknologi yang Digunakan

Teknologi dan tools yang digunakan dalam proyek ini antara lain:

- Git dan GitHub
- Bahasa Pemrograman Backend (Java / Node.js)
- Docker
- Jenkins
- Prometheus
- Grafana


## 3. Inisialisasi Repository Git

Tahap awal pengembangan dimulai dengan pembuatan repository Git lokal yang kemudian dihubungkan dengan repository GitHub.

Langkah-langkah yang dilakukan:
1. Membuat folder project
2. Melakukan inisialisasi Git
3. Menambahkan remote repository GitHub
4. Melakukan commit awal dan push ke GitHub


perintah git :
git init
git branch -M main
git remote add origin https://github.com/USERNAME/NAMA_REPOSITORY.git
git add .
git commit -m "Initial commit"
git push -u origin main



---

## 4. Pembuatan Microservice Perpustakaan

```md
## 4. Pembuatan Microservice Perpustakaan

Microservice Perpustakaan dikembangkan untuk menangani beberapa fungsi utama, yaitu:
- Pengelolaan data buku
- Pengelolaan data anggota
- Proses peminjaman dan pengembalian buku

Aplikasi dirancang menggunakan konsep **REST API**, sehingga setiap fitur dapat diakses melalui endpoint HTTP secara terpisah dan modular sesuai dengan prinsip microservice.


## 5. Dockerisasi Aplikasi

Untuk memastikan aplikasi dapat dijalankan secara konsisten di berbagai lingkungan, microservice dikemas menggunakan **Docker**.

Langkah-langkah dockerisasi aplikasi meliputi:
- Membuat file `Dockerfile`
- Menentukan base image yang sesuai
- Menyalin source code aplikasi ke dalam container
- Menjalankan aplikasi di dalam container Docker

Setelah itu dilakukan proses build dan run Docker image untuk memastikan aplikasi berjalan dengan baik.


## 6. Implementasi Monitoring

Monitoring diterapkan untuk memantau kondisi dan performa aplikasi microservice yang sedang berjalan.

Tahapan implementasi monitoring adalah sebagai berikut:
- Menggunakan **Prometheus** untuk mengambil dan mengumpulkan data metrik aplikasi
- Menggunakan **Grafana** untuk menampilkan data monitoring dalam bentuk dashboard visual
- Menjalankan layanan monitoring menggunakan **Docker Compose**
- Memastikan aplikasi dapat dimonitor secara real-time

Dengan adanya monitoring ini, performa dan kondisi aplikasi dapat diamati secara lebih efektif.


## 7. Instalasi dan Konfigurasi Jenkins

Jenkins digunakan sebagai alat **Continuous Integration (CI)** untuk mengotomatisasi proses build dan deploy aplikasi.

Langkah-langkah yang dilakukan meliputi:
- Menginstal Jenkins
- Mengakses Jenkins melalui browser
- Membuat Pipeline Project
- Menghubungkan Jenkins dengan repository GitHub


## 8. Konfigurasi Jenkins Pipeline

Pipeline Jenkins didefinisikan menggunakan file `Jenkinsfile` yang berisi beberapa tahapan utama, yaitu:
- Mengambil source code dari GitHub
- Build aplikasi
- Build Docker image
- Menjalankan aplikasi menggunakan Docker

Pipeline ini memungkinkan setiap perubahan kode yang di-push ke GitHub dapat diproses secara otomatis oleh Jenkins.


## 9. Integrasi GitHub dan Jenkins

Setelah integrasi antara GitHub dan Jenkins berhasil dilakukan:
- Setiap perintah `git push` ke branch utama
- Jenkins akan otomatis menjalankan pipeline
- Proses build dan deploy berjalan secara otomatis tanpa dilakukan secara manual

Hal ini menunjukkan penerapan konsep **CI/CD (Continuous Integration dan Continuous Deployment)**.


## 10. Upload ke GitHub

Repository GitHub pada proyek ini berisi:
- Source code aplikasi Perpustakaan
- File `Dockerfile`
- File `docker-compose.yml` untuk monitoring
- File `Jenkinsfile`
- File `README.md`

Seluruh file konfigurasi dan dokumentasi telah diunggah sesuai dengan ketentuan tugas.


## 11. Kesimpulan

Melalui proyek ini, telah berhasil diimplementasikan:
- Arsitektur Microservice
- Monitoring aplikasi menggunakan Prometheus dan Grafana
- Continuous Integration menggunakan Jenkins
- Pengelolaan source code menggunakan GitHub

Proyek ini menunjukkan integrasi yang baik antara pengembangan aplikasi, monitoring sistem, serta otomatisasi proses build dan deployment.


## 12. Identitas

- **Nama** : Vania  
- **Mata Kuliah** : Microservice / DevOps  
- **Topik Tugas** : Perpustakaan, Monitoring, dan Jenkins
