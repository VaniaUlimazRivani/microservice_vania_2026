package com.perpustakaan.service_pengembalian.service;

import com.perpustakaan.service_pengembalian.model.Pengembalian;
import com.perpustakaan.service_pengembalian.vo.Peminjaman;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class PengembalianService {
    
    private final CqrsClientService cqrsClient;
    private final RestTemplate restTemplate;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    // Mengambil URL Service Peminjaman dari application.properties
    @Value("${service.peminjaman.url}")
    private String peminjamanServiceUrl;
    
    /**
     * Logic Utama: Mengembalikan Buku & Hitung Denda
     */
    public Pengembalian kembalikanBuku(Pengembalian pengembalian) {
        // 1. Set ID dan Tanggal Pengembalian (Hari ini)
        pengembalian.setId(idCounter.getAndIncrement());
        pengembalian.setTanggalDikembalikan(LocalDate.now());
        
        // Default nilai jika tidak ada keterlambatan
        pengembalian.setTerlambat(0);
        pengembalian.setDenda(BigDecimal.ZERO);
        
        try {
            // 2. Tembak API Service Peminjaman untuk dapatkan Tanggal Batas
            // URL: http://localhost:8084/api/peminjaman/{id}
            String url = peminjamanServiceUrl + "/api/peminjaman/" + pengembalian.getPeminjamanId();
            log.info("Fetching Loan Data from: {}", url);
            
            Peminjaman p = restTemplate.getForObject(url, Peminjaman.class);
            
            // 3. Logic Hitung Denda
            if (p != null && p.getTanggal_batas() != null) {
                long selisihHari = ChronoUnit.DAYS.between(p.getTanggal_batas(), pengembalian.getTanggalDikembalikan());
                
                if (selisihHari > 0) {
                    pengembalian.setTerlambat((int) selisihHari);
                    // Rumus: Denda = Hari Terlambat * Rp 1.000
                    pengembalian.setDenda(BigDecimal.valueOf(selisihHari * 1000));
                    log.info("Terlambat {} hari. Denda: {}", selisihHari, pengembalian.getDenda());
                }
            }
        } catch (Exception e) {
            log.error("Gagal mengambil data peminjaman (Mungkin service mati atau ID salah): {}", e.getMessage());
            // Lanjut save walaupun gagal hitung denda, agar data tidak hilang
        }
        
        // 4. Simpan ke Database Pusat (CQRS)
        cqrsClient.save(pengembalian, pengembalian.getId().toString());
        return pengembalian;
    }
    
    public void delete(Long id) {
        cqrsClient.delete(id.toString());
    }
    
    public Object findById(Long id) {
        return cqrsClient.findById(id.toString());
    }
    
    public List<Object> findAll() {
        return cqrsClient.findAll();
    }
    
    // Method update jika diperlukan nanti
    public Pengembalian update(Pengembalian pengembalian) {
        cqrsClient.update(pengembalian, pengembalian.getId().toString());
        return pengembalian;
    }
}