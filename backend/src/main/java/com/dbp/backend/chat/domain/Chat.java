package com.dbp.backend.chat.domain;


import com.dbp.backend.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Date;



@Data

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario userId;

    private String chatName;

    private LocalDateTime dateCreation;

}
