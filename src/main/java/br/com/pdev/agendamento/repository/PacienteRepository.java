package br.com.pdev.agendamento.repository;

import br.com.pdev.agendamento.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

}
