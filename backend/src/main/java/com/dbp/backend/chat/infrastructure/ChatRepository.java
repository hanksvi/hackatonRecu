package com.dbp.backend.chat.infrastructure;


import com.dbp.backend.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {

}