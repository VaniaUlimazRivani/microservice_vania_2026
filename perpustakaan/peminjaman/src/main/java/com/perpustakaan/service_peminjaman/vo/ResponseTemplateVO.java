package com.perpustakaan.service_peminjaman.vo;
import com.perpustakaan.service_peminjaman.model.Peminjaman;
import lombok.*;

@Data @AllArgsConstructor
public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Buku buku;
    private Anggota anggota;
    private Pengembalian pengembalian;
}