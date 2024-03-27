package br.com.pdev.agendamento.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "data_nascimento")
    private Date data_Nascimento;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;

}
