package com.perpustakaan.service_peminjaman.controller;

import com.perpustakaan.service_peminjaman.dto.PeminjamanDto;
import com.perpustakaan.service_peminjaman.model.Peminjaman;
import com.perpustakaan.service_peminjaman.service.PeminjamanService;
import com.perpustakaan.service_peminjaman.vo.ResponseTemplateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/peminjaman")
@RequiredArgsConstructor
public class PeminjamanController {

    private final PeminjamanService service;

    // 1. GET ALL
    @GetMapping
    public Map<String, Object> getAllPeminjaman() {
        log.info("GET /api/peminjaman - Fetching all data");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "peminjaman");
        response.put("data", service.getAllPeminjaman());
        return response;
    }

    // 2. GET BY ID (Standar)
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeminjamanById(@PathVariable Long id) {
        log.info("GET /api/peminjaman/{} - Fetching by ID", id);
        Object result = service.getPeminjamanById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // 3. CREATE (Dengan Notifikasi RabbitMQ di Service)
    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman) {
        log.info("POST /api/peminjaman - Membuat peminjaman baru");
        return service.createPeminjaman(peminjaman);
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public Peminjaman updatePeminjaman(@PathVariable Long id, @RequestBody Peminjaman peminjaman) {
        log.info("PUT /api/peminjaman/{}", id);
        return service.updatePeminjaman(id, peminjaman);
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public void deletePeminjaman(@PathVariable Long id) {
        log.info("DELETE /api/peminjaman/{}", id);
        service.deletePeminjaman(id);
    }

    // ==========================================
    //  FITUR ADVANCED (AGGREGATE & DTO)
    // ==========================================

    // 6. GET DETAIL (Aggregate: Menggabungkan Data Peminjaman + Buku + Anggota)
    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseTemplateVO> getPeminjamanWithDetailById(@PathVariable Long id) {
        log.info("GET /api/peminjaman/detail/{} - Fetching Aggregate Data", id);
        ResponseTemplateVO result = service.getPeminjamanWithDetailById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // 7. GET DENDA (Logic Hitung Denda)
    @GetMapping("/denda/{id}")
    public ResponseEntity<PeminjamanDto> getPeminjamanWithDenda(@PathVariable Long id) {
        log.info("GET /api/peminjaman/denda/{} - Calculating Fine", id);
        PeminjamanDto result = service.getPeminjamanWithDenda(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}