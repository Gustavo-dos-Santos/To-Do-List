package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Informe o seu nome")
    @Size(min = 3, max = 45, message = "O nome deve conter entre {min} e {max} caracteres.")
    @Column(length = 45)
    private String name;

    @NotBlank(message = "Informe o seu sobrenome")
    @Size(min = 3, max = 45, message = "O nome deve conter entre {min} e {max} letras.")
    @Column(length = 45, columnDefinition = "VARCHAR")
    private String  lastname;

    @NotBlank(message = "Informe o seu telefone")
    @Size(min = 3, max = 45, message = "O nome deve conter entre {min} e {max} letras.")
    @Column(length = 15, columnDefinition = "VARCHAR")
    private String  phone;

    @Column(length = 100, columnDefinition = "VARCHAR")
    private String  email;

    @NotNull(message = "Senha obrigatoria")
    @Size(min = 6, max = 20, message = "A senha deve conter entre {min} e {max} letras.")
    @Column(length = 20, columnDefinition = "VARCHAR")
    private String  password;

    @CreationTimestamp
    private LocalDateTime createAccount;
    @UpdateTimestamp
    private LocalDateTime lastAccess;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> record;



}
