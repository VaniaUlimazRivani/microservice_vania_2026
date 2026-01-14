package com.perpustakaan.service_pengembalian.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pengembalian {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("peminjaman_id")
    private Long peminjamanId;
    
    @JsonProperty("tanggal_dikembalikan")
    private LocalDate tanggalDikembalikan;
    
    private Integer terlambat; // Gunakan Integer (bukan int) agar bisa null safe
    private BigDecimal denda;
}