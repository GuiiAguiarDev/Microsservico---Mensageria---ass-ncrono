package com.bank;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestRabbit {
    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("amqps://abdchoir:D5Kn7fau_uKtP64c4U2IMEa7PZ2owtKX@gorilla.lmq.cloudamqp.com/abdchoir");

            try (Connection connection = factory.newConnection()) {
                System.out.println("✅ Conexão com CloudAMQP estabelecida com sucesso!");
            }

        } catch (Exception e) {
            System.err.println("❌ Falha na conexão:");
            e.printStackTrace();
        }
    }
}
