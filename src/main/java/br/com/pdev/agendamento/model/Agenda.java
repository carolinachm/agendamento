package br.com.pdev.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="descricao")
    private String descricao;
    @Column(name = "data_hora")
    private LocalDateTime data_Hora;
    @Column(name = "data_criacao")
    private LocalDateTime data_Criacao;
    @ManyToOne
    private Paciente pacientes;
}
