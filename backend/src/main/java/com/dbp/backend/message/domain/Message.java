package com.dbp.backend.message.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String chatID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sender sender;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AIModel aiModel;

    public Message(String chatID, Sender sender, String content, AIModel aiModel) {
        this.chatID = chatID;
        this.sender = sender;
        this.content = content;
        this.aiModel = aiModel;
        this.timestamp = LocalDateTime.now();
    }
}

enum Sender {
    USER,
    AI
}

enum AIModel {
    GPT_4,
    LLAMA,
    OTHER
}
