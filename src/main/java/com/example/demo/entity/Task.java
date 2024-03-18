package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Integer id;

    @NotBlank(message = "Informe a descrição ")
    @Size(min = 3, max = 45, message = "A descrição deve conter entre {min} e {max} letras.")
    @Column(length = 45, columnDefinition = "VARCHAR")
    private String description;

    @NotBlank(message = "Informe um horario")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;
}
