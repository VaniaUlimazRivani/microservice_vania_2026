package com.perpustakaan.service_pengembalian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 1. Exclude Database (Agar tidak error DataSource)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 2. Enable Discovery (Agar terbaca Eureka)
@EnableDiscoveryClient
public class ServicePengembalianApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePengembalianApplication.class, args);
    }

    // ======================================================
    // BAGIAN PENTING: PABRIK PEMBUAT REST TEMPLATE
    // Tanpa ini, CqrsClientService akan error saat dijalankan
    // ======================================================
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}