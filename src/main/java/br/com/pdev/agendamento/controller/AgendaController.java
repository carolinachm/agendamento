package br.com.pdev.agendamento.controller;

import br.com.pdev.agendamento.model.Agenda;
import br.com.pdev.agendamento.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define a classe como um controlador REST.
@RequestMapping("/api") // Mapeia todas as requisições que começam com "/api" para este controlador.
@RequiredArgsConstructor //Lombok: cria um construtor com 1 parâmetro para cada campo final
public class AgendaController {

    private final AgendaService agendaService;


    @PostMapping("/agenda")
    public ResponseEntity<?> cadastrarAgenda(@RequestBody Agenda agenda){
        return agendaService.cadastrarAgenda(agenda);
    }
    @GetMapping("/agenda")
    public ResponseEntity<?> listarTodasAgenda(){
        return agendaService.listarTodasAgenda();
    }
    @GetMapping("/agenda/{id}")
    public ResponseEntity<?> buscarUmaAgendaPorId(@PathVariable Long id){
        return agendaService.buscarUmaAgendaPorId(id);
    }
    @PutMapping("/agenda")
    public ResponseEntity<?> editarAgenda(@RequestBody Agenda agenda){
        return agendaService.editarAgenda(agenda);
    }
    @DeleteMapping("/agenda/{id}")
    public ResponseEntity<?> removerAgenda(@PathVariable Long id){
        return agendaService.removerAgenda(id);
    }
}
