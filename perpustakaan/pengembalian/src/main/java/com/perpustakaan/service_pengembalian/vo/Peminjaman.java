package com.perpustakaan.service_pengembalian.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peminjaman {
    @JsonProperty("id")
    private Long id;
    
    private Long anggotaId;
    private Long bukuId;
    private LocalDate tanggalPinjam;
    
    @JsonProperty("tanggal_batas")
    private LocalDate tanggal_batas;
}