package com.dbp.backend.usuario.domain;


import com.dbp.backend.message.domain.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;


};  // Relación One to Many con Mensaje
