package com.dbp.backend.chat.application;

import com.dbp.backend.chat.domain.Chat;
import com.dbp.backend.chat.domain.ChatService;
import com.dbp.backend.message.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> getChats(Principal principal) {
        // Obtener ID del usuario autenticado
        String userID = principal.getName();
        return ResponseEntity.ok(chatService.getChatsByUser(userID));
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat, Principal principal) {
        // Asignar el usuario autenticado al chat
        chat.setUserID(principal.getName());
        return ResponseEntity.ok(chatService.createChat(chat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String id) {
        return ResponseEntity.ok(chatService.getMessagesByChat(id));
    }
}
