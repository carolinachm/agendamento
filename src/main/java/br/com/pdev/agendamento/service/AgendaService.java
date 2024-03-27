package br.com.pdev.agendamento.service;

import br.com.pdev.agendamento.model.Agenda;
import br.com.pdev.agendamento.repository.AgendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service // Marca a classe como um componente do Spring, especificamente um Service
@Transactional // Garante que os métodos sejam executados dentro de uma transação
@RequiredArgsConstructor // Lombok: cria um construtor com 1 parâmetro para cada campo final
public class AgendaService {

    private final AgendaRepository agendaRepository;

    //Método para cadastrar agenda
    public ResponseEntity<?> cadastrarAgenda(Agenda agenda){
        return new ResponseEntity<>(agendaRepository.save(agenda), HttpStatus.CREATED);
    }
    //Método para listar todos as agendas
    public ResponseEntity<?> listarTodasAgenda(){
        return new ResponseEntity<>(agendaRepository.findAll(), HttpStatus.OK);
    }
    //Metodo para buscar uma agenda por id
    public ResponseEntity<?> buscarUmaAgendaPorId(Long id){
        return new ResponseEntity<>(agendaRepository.findById(id), HttpStatus.OK);
    }
    //Metódo para editar uma agenda
    public ResponseEntity<?> editarAgenda(Agenda agenda){
        return new ResponseEntity<>(agendaRepository.save(agenda), HttpStatus.OK);
    }
    public ResponseEntity<?> removerAgenda(Long id){
        // Primeiro, verifica se o paciente com o ID fornecido existe
        if (agendaRepository.existsById(id)) {
            // Se existir, deleta o agendamento e retorna status OK
            agendaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Se não existir, retorna NOT FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
