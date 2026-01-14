package com.perpustakaan.service_pengembalian;

import com.perpustakaan.service_pengembalian.model.Pengembalian;
import com.perpustakaan.service_pengembalian.service.CqrsClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        // Membuat Data Dummy Pengembalian
        // Urutan: id, peminjamanId, tanggalDikembalikan, terlambat, denda
        Pengembalian p = new Pengembalian(
            1L,                 // ID
            1L,                 // Peminjaman ID (Harus ada di service peminjaman biar valid)
            LocalDate.now(),    // Tanggal Kembali
            0,                  // Terlambat (hari)
            BigDecimal.ZERO     // Denda
        );

        // Simpan ke CQRS
        try {
            cqrsClient.save(p, "1");
            System.out.println("✅ Data Dummy Pengembalian berhasil di-load!");
        } catch (Exception e) {
            System.out.println("⚠️ Gagal load data dummy (Mungkin CQRS Service belum jalan): " + e.getMessage());
        }
    }
}