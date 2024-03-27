package br.com.pdev.agendamento.controller;

import br.com.pdev.agendamento.model.Paciente;
import br.com.pdev.agendamento.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define a classe como um controlador REST.
@RequestMapping("/api") // Mapeia todas as requisições que começam com "/api" para este controlador.
@RequiredArgsConstructor //Lombok: cria um construtor com 1 parâmetro para cada campo final
public class PacienteController {

    // Injeção de dependência do serviço de paciente. O Spring cuidará da instanciação.
    private final PacienteService pacienteService;


    // Endpoint para criar um novo paciente. Usa o método POST.
    @PostMapping("/paciente")
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente){
        // Delega a chamada para o serviço de paciente e retorna o resultado.
        return pacienteService.cadastrarPaciente(paciente);
    }

    // Endpoint para listar todos os pacientes. Usa o método GET.
    @GetMapping("/paciente")
    public ResponseEntity<?> listarTodosPaciente(){
        // Delega a chamada para o serviço de paciente para listar todos os pacientes.
        return pacienteService.listarTodosPaciente();
    }

    // Endpoint para buscar um único paciente por ID. Usa o método GET.
    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> buscarUmPacientePorId(@PathVariable Long id){
        // Usa @PathVariable para capturar o ID na URL e passar para o serviço de paciente.
        return pacienteService.buscarUmPacientePorId(id);
    }

    // Endpoint para atualizar um paciente existente. Usa o método PUT.
    @PutMapping("/paciente")
    public Paciente editarPaciente(@RequestBody Paciente paciente){
        // Recebe um paciente no corpo da requisição e delega a atualização para o serviço de paciente.
        return pacienteService.editarPaciente(paciente);
    }

    // Endpoint para remover um paciente por ID. Usa o método DELETE.
    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<?> removerPaciente(@PathVariable Long id){
        // Chama o método de remoção no serviço de pacientes. Precisa ser ajustado para retornar ResponseEntity.
        pacienteService.removerPaciente(id);
        // Retorna um ResponseEntity sem corpo, mas com o código HTTP 204 No Content, indicando sucesso sem retorno.
        return ResponseEntity.noContent().build();
    }
}
