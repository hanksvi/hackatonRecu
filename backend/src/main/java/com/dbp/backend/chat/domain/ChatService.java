package com.dbp.backend.chat.domain;


import com.dbp.backend.chat.infrastructure.ChatRepository;
import com.dbp.backend.message.domain.Message;
import com.dbp.backend.message.infrastructure.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public List<Chat> getChatsByUser(String userID) {
        // Obtener todos los chats asociados a un usuario
        return chatRepository.findByUserID(userID);
    }

    public Chat createChat(Chat chat) {
        // Crear un nuevo chat asociado al usuario
        return chatRepository.save(chat);
    }

    public List<Message> getMessagesByChat(String chatID) {
        // Validar que el chat existe
        chatRepository.findById(chatID)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        // Obtener mensajes asociados al chat
        return messageRepository.findByChatID(chatID);
    }



}
