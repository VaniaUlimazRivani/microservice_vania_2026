package com.perpustakaan.cqrs.controller; // Pastikan package ini benar

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/cqrs")
public class CqrsController {

    // GUDANG SEMENTARA (RAM)
    // Kita pakai ini dulu biar Service Anggota tidak error
    private final Map<String, Map<String, Object>> dataStore = new ConcurrentHashMap<>();

    // 1. Menerima Data (Command)
    @PostMapping("/{entity}/command")
    public String handleCommand(@PathVariable String entity, @RequestBody Map<String, Object> payload) {
        // Log biar kelihatan kalau ada data masuk
        log.info("Menerima Data Baru Untuk: {} -> {}", entity, payload);
        
        String id = String.valueOf(payload.get("id"));
        String eventType = (String) payload.get("eventType");
        Object data = payload.get("data");

        // Buat folder penyimpanan jika belum ada
        dataStore.putIfAbsent(entity, new ConcurrentHashMap<>());

        if ("DELETE".equals(eventType)) {
            dataStore.get(entity).remove(id);
        } else {
            dataStore.get(entity).put(id, data);
        }

        return "Sukses Men ke Memory: " + entity;
    }

    // 2. Mengambil Semua Data (Query)
    @GetMapping("/{entity}/query")
    public List<Object> getAllData(@PathVariable String entity) {
        Map<String, Object> entityData = dataStore.get(entity);
        if (entityData == null) {
            return List.of();
        }
        return new ArrayList<>(entityData.values());
    }

    // 3. Mengambil Data by ID
    @GetMapping("/{entity}/query/{id}")
    public Object getDataById(@PathVariable String entity, @PathVariable String id) {
        Map<String, Object> entityData = dataStore.get(entity);
        if (entityData == null) {
            return null;
        }
        return entityData.get(id);
    }
}