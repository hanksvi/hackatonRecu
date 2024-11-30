package com.dbp.backend.message.application;
import com.dbp.backend.message.domain.Message;
import com.dbp.backend.message.domain.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{chatID}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String chatID) {
        return ResponseEntity.ok(messageService.getMessagesByChatID(chatID));
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.saveMessage(message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{chatID}/aiModel/{aiModel}")
    public ResponseEntity<List<Message>> getMessagesForAIModel(
            @PathVariable String chatID,
            @PathVariable String aiModel) {
        return ResponseEntity.ok(messageService.getMessagesForAIModel(chatID, aiModel));
    }
}
