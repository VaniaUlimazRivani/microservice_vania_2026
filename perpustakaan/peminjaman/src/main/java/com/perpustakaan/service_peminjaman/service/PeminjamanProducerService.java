package com.perpustakaan.service_peminjaman.service;

import com.perpustakaan.service_peminjaman.dto.PeminjamanMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeminjamanProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${app.rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    public PeminjamanProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPeminjamanNotification(PeminjamanMessage message) {
        // Kita bungkus try-catch agar jika RabbitMQ mati, aplikasi tidak crash saat save
        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        } catch (Exception e) {
            System.err.println("Gagal kirim notifikasi RabbitMQ: " + e.getMessage());
        }
    }
}