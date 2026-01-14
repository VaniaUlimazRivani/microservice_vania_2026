package com.perpustakaan.service_peminjaman;

import com.perpustakaan.service_peminjaman.model.Peminjaman;
import com.perpustakaan.service_peminjaman.service.CqrsClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        // Membuat Data Dummy:
        // ID: 1
        // Tgl Pinjam: Hari ini
        // Tgl Dikembalikan: 10 hari lagi (Simulasi pengembalian)
        // Tgl Batas: 7 hari lagi
        // Anggota ID: 1
        // Buku ID: 1
        
        Peminjaman p = new Peminjaman(
            1L, 
            LocalDate.now(), 
            LocalDate.now().plusDays(10), 
            LocalDate.now().plusDays(7), 
            1L, 
            1L
        );
        
        // Simpan ke CQRS Service
        try {
            cqrsClient.save(p, "1");
            System.out.println("✅ Data Dummy Peminjaman berhasil di-load!");
        } catch (Exception e) {
            System.out.println("⚠️ Gagal load data dummy (Mungkin CQRS Service belum jalan): " + e.getMessage());
        }
    }
}