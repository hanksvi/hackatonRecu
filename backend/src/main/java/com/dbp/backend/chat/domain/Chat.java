package com.dbp.backend.chat.domain;


import com.dbp.backend.message.domain.Message;
import com.dbp.backend.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario userId;

    private String chatName;

    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> message;


}
