package com.perpustakaan.service_peminjaman.dto;
import com.perpustakaan.service_peminjaman.model.Peminjaman;
import lombok.Data;
import java.time.temporal.ChronoUnit;

@Data
public class PeminjamanDto {
    private Peminjaman peminjaman;
    private Double denda;

    public PeminjamanDto(Peminjaman p) {
        this.peminjaman = p;
        this.denda = p.getTanggalDikembalikan() != null && p.getTanggal_batas() != null
            ? Math.max(0, ChronoUnit.DAYS.between(p.getTanggal_batas(), p.getTanggalDikembalikan()) * 1000.0)
            : 0.0;
    }
}