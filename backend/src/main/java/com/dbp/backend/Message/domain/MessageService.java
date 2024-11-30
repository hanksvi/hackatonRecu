package com.dbp.backend.Message.domain;

import com.dbp.backend.Message.infrastructure.MessageRepository;
import com.dbp.backend.chat.infrastructure.ChatRepository;
import com.dbp.backend.chat.domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    public List<Message> getMessagesByChatID(String chatID) {
        Chat chat = chatRepository.findById(chatID)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        return messageRepository.findByChatID(chatID);
    }

    public Message saveMessage(Message message) {
        chatRepository.findById(message.getChatID())
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        return messageRepository.save(message);
    }

    public void deleteMessage(String id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        messageRepository.delete(message);
    }

    public List<Message> getMessagesForAIModel(String chatID, String aiModel) {
        chatRepository.findById(chatID)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        return messageRepository.findByChatIDAndAiModel(chatID, aiModel);
    }
}
