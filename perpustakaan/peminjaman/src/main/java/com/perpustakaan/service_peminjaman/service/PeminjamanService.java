package com.perpustakaan.service_peminjaman.service;

import com.perpustakaan.service_peminjaman.dto.PeminjamanDto;
import com.perpustakaan.service_peminjaman.dto.PeminjamanMessage;
import com.perpustakaan.service_peminjaman.model.Peminjaman;
import com.perpustakaan.service_peminjaman.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PeminjamanService {

    private final CqrsClientService cqrsClient;
    private final RestTemplate restTemplate;
    private final PeminjamanProducerService producer;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    @Value("${service.anggota.url}")
    private String anggotaServiceUrl;
    
    @Value("${service.buku.url}")
    private String bukuServiceUrl;
    
    @Value("${service.pengembalian.url}")
    private String pengembalianServiceUrl;

    public List<Object> getAllPeminjaman() {
        return cqrsClient.findAll();
    }

    public Object getPeminjamanById(Long id) {
        return cqrsClient.findById(id.toString());
    }

    public Peminjaman createPeminjaman(Peminjaman p) {
        p.setId(idCounter.getAndIncrement());
        cqrsClient.save(p, p.getId().toString());
        // Kirim notifikasi
        producer.sendPeminjamanNotification(new PeminjamanMessage(p.getId(), p.getAnggotaId(), p.getBukuId()));
        return p;
    }

    public Peminjaman updatePeminjaman(Long id, Peminjaman p) {
        p.setId(id);
        cqrsClient.update(p, id.toString());
        return p;
    }

    public void deletePeminjaman(Long id) {
        cqrsClient.delete(id.toString());
    }

    // Logic Aggregate (Mengambil data Buku & Anggota)
    public ResponseTemplateVO getPeminjamanWithDetailById(Long id) {
        Object obj = getPeminjamanById(id);
        if (obj == null) return null;
        
        @SuppressWarnings("unchecked")
        Map<String, Object> dataMap = (Map<String, Object>) obj;
        Peminjaman p = new Peminjaman();
        p.setId(((Number) dataMap.get("id")).longValue());
        
        // Handle konversi tanggal dari Map (String) ke LocalDate jika perlu, atau biarkan null
        // Di sini kita ambil ID saja dulu untuk contoh
        p.setAnggotaId(dataMap.get("anggota_id") != null ? ((Number) dataMap.get("anggota_id")).longValue() : null);
        p.setBukuId(dataMap.get("buku_id") != null ? ((Number) dataMap.get("buku_id")).longValue() : null);
        
        Buku b = null;
        Anggota a = null;
        Pengembalian pg = null;
        
        // Panggil Service Buku
        try {
            if (p.getBukuId() != null) {
                b = restTemplate.getForObject(bukuServiceUrl + "/api/buku/" + p.getBukuId(), Buku.class);
            }
        } catch (Exception e) { System.err.println("Error fetch buku: " + e.getMessage()); }
        
        // Panggil Service Anggota
        try {
            if (p.getAnggotaId() != null) {
                a = restTemplate.getForObject(anggotaServiceUrl + "/api/anggota/" + p.getAnggotaId(), Anggota.class);
            }
        } catch (Exception e) { System.err.println("Error fetch anggota: " + e.getMessage()); }
        
        return new ResponseTemplateVO(p, b, a, pg);
    }

    public PeminjamanDto getPeminjamanWithDenda(Long id) {
        Object obj = getPeminjamanById(id);
        if (obj == null) return null;
        
        @SuppressWarnings("unchecked")
        Map<String, Object> dataMap = (Map<String, Object>) obj;
        
        // Konversi Map ke Peminjaman object
        Peminjaman p = new Peminjaman();
        p.setId(((Number) dataMap.get("id")).longValue());
        p.setAnggotaId(dataMap.get("anggota_id") != null ? ((Number) dataMap.get("anggota_id")).longValue() : null);
        p.setBukuId(dataMap.get("buku_id") != null ? ((Number) dataMap.get("buku_id")).longValue() : null);
        
        return new PeminjamanDto(p); 
    }
}