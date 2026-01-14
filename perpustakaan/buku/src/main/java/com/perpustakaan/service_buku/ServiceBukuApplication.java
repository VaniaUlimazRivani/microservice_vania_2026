package com.perpustakaan.service_buku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration; // Import ini penting
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// TAMBAHAN: (exclude = {DataSourceAutoConfiguration.class})
// Artinya: "Jangan coba-coba konek ke database, saya tidak butuh database lokal."
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ServiceBukuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBukuApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}