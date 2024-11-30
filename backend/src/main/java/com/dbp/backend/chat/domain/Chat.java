package com.dbp.backend.chat.domain;


import com.dbp.backend.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;
@Data
@Getter
@Setter
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario userId;

    private String chatName;

    private LocalDateTime dateCreation;

    public String getUserID() {
        return id;
    }

    public void setUserID(String userID) {
        this.id = userID;
    }

}
