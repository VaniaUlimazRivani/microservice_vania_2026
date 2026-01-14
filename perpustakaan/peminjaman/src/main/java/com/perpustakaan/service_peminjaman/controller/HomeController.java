package com.perpustakaan.service_peminjaman.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        
        return "<html>" +
            "<head><meta charset='UTF-8'><title>Peminjaman Service</title></head>" +
            "<body style='font-family:monospace;padding:20px;max-width:900px;margin:0 auto'>" +
            
            // Header
            "<h1 style='font-size:24px'>📖 Peminjaman Service API (Aggregate System)</h1>" +
            "<p style='color:green;font-weight:bold'>✓ Service Running on Port " + request.getServerPort() + "</p>" +
            
            // Tabel Endpoint
            "<h3>Available Endpoints:</h3>" +
            "<table style='border-collapse:collapse;width:100%;border:1px solid #ddd'>" +
            
            // Header Tabel
            "<tr style='background:#f2f2f2;text-align:left'>" +
                "<th style='padding:10px;border:1px solid #ddd'>Method</th>" +
                "<th style='padding:10px;border:1px solid #ddd'>URL</th>" +
                "<th style='padding:10px;border:1px solid #ddd'>Description</th>" +
            "</tr>" +

            // 1. GET ALL
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:blue'><b>GET</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/peminjaman'>/api/peminjaman</a></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Get all transaction data</td>" +
            "</tr>" +

            // 2. GET BY ID
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:blue'><b>GET</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Get simple data by ID</td>" +
            "</tr>" +

            // 3. GET DETAIL (AGGREGATE) - Ini fitur unggulan service ini
            "<tr style='background-color:#fff3cd'>" + // Warna kuning muda biar highlight
                "<td style='padding:8px;border:1px solid #ddd;color:blue'><b>GET</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'><a href='" + baseUrl + "/api/peminjaman/detail/1'>/api/peminjaman/detail/{id}</a></td>" +
                "<td style='padding:8px;border:1px solid #ddd'><b>AGGREGATE:</b> Get data + Buku info + Anggota info</td>" +
            "</tr>" +

            // 4. GET DENDA
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:blue'><b>GET</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/denda/{id}</td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Calculate Late Fees (Denda)</td>" +
            "</tr>" +

            // 5. POST
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:green'><b>POST</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>/api/peminjaman</td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Create new loan (Triggers RabbitMQ)</td>" +
            "</tr>" +

            // 6. PUT
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:orange'><b>PUT</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Update transaction</td>" +
            "</tr>" +

            // 7. DELETE
            "<tr>" +
                "<td style='padding:8px;border:1px solid #ddd;color:red'><b>DELETE</b></td>" +
                "<td style='padding:8px;border:1px solid #ddd'>/api/peminjaman/{id}</td>" +
                "<td style='padding:8px;border:1px solid #ddd'>Delete transaction</td>" +
            "</tr>" +

            "</table>" +
            "</body>" +
            "</html>";
    }
}