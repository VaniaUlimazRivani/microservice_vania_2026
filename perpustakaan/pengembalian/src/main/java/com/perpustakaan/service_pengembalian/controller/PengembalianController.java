package com.perpustakaan.service_pengembalian.controller;

import com.perpustakaan.service_pengembalian.model.Pengembalian;
import com.perpustakaan.service_pengembalian.service.PengembalianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/pengembalian")
@RequiredArgsConstructor
public class PengembalianController {
    
    private final PengembalianService pengembalianService;
    
    // 1. GET ALL
    @GetMapping
    public Map<String, Object> getAllReturns() {
        log.info("GET /api/pengembalian - Fetching all returns");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "pengembalian");
        response.put("data", pengembalianService.findAll());
        return response;
    }
    
    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getReturnById(@PathVariable Long id) {
        log.info("GET /api/pengembalian/{} - Fetching return by ID", id);
        Object result = pengembalianService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
    
    // 3. POST (Create Pengembalian & Hitung Denda)
    @PostMapping
    public Pengembalian createReturn(@RequestBody Pengembalian pengembalian) {
        log.info("POST /api/pengembalian - Processing return for Loan ID: {}", pengembalian.getPeminjamanId());
        // PENTING: Panggil method 'kembalikanBuku' (bukan save) karena di situ ada logika hitung denda
        return pengembalianService.kembalikanBuku(pengembalian);
    }
    
    /* NOTE: Fitur Update & Delete belum kita buat di PengembalianService.
       Untuk sementara kita matikan dulu agar tidak error saat compile.
       Jika nanti butuh, tinggal tambahkan method update/delete di Service-nya.
    */
    
    // @PutMapping("/{id}")
    // public Pengembalian updateReturn(@PathVariable Long id, @RequestBody Pengembalian pengembalian) { ... }
    
    // @DeleteMapping("/{id}")
    // public void deleteReturn(@PathVariable Long id) { ... }
}