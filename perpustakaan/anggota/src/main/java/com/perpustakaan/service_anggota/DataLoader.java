package com.perpustakaan.service_anggota;

import com.perpustakaan.service_anggota.model.Anggota;
import com.perpustakaan.service_anggota.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        // Ini akan berjalan otomatis saat aplikasi start untuk isi data awal
        cqrsClient.save(new Anggota(1L, "2311083015", "Anla", "Padang", "Laki-laki", "anlaharpanda@gmail.com"), "1");
    }
}