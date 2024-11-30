package com.dbp.backend.message.infrastructure;

import com.dbp.backend.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByChatID(String chatID);
    List<Message> findByChatIDAndAiModel(String chatID, String aiModel);
}
