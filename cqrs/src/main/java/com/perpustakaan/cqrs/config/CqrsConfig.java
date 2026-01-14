package com.perpustakaan.cqrs.config; // <-- Package disesuaikan

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CqrsConfig {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // PENTING: Agar JSON bisa membaca format Tanggal (LocalDate)
        // yang dikirim oleh Service Anggota/Buku/Pengembalian
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}