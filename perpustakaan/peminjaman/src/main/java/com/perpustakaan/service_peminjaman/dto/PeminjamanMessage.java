package com.perpustakaan.service_peminjaman.dto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PeminjamanMessage implements Serializable {
    private Long peminjamanId;
    private Long anggotaId;
    private Long bukuId;
}