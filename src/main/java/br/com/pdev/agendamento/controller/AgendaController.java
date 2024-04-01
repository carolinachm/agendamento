package br.com.pdev.agendamento.controller;

import br.com.pdev.agendamento.model.Agenda;
import br.com.pdev.agendamento.model.Paciente;
import br.com.pdev.agendamento.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define a classe como um controlador REST.
@RequestMapping("/api") // Mapeia todas as requisições que começam com "/api" para este controlador.
@RequiredArgsConstructor //Lombok: cria um construtor com 1 parâmetro para cada campo final
public class AgendaController {

    private final AgendaService agendaService;


    @PostMapping("/agenda")
    @Operation(summary = "Rota responsável pelo cadastro uma agenda")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Agendamento cadastrado com sucesso",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class)
                            )
                    }
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Informação inválida",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                                    // schema = @Schema(implementation = Mensagem.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> cadastrarAgenda(@RequestBody Agenda agenda){
        return agendaService.cadastrarAgenda(agenda);
    }
    @GetMapping("/agenda")
    @Operation(summary = "Rota responsável pelo listar todos os agendamentos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listar todos os agendamentos",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class)
                            )
                    }
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Informação inválida",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                                    // schema = @Schema(implementation = Mensagem.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> listarTodasAgenda(){
        return agendaService.listarTodasAgenda();
    }
    @GetMapping("/agenda/{id}")
    @Operation(summary = "Rota responsável por buscar um agendamento por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Buscar um agendamento por id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class)
                            )
                    }
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Informação inválida",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                                    // schema = @Schema(implementation = Mensagem.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> buscarUmaAgendaPorId(@PathVariable Long id){
        return agendaService.buscarUmaAgendaPorId(id);
    }
    @PutMapping("/agenda")
    @Operation(summary = "Rota responsável atualizar um agendamento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Agendamento atualizado com sucesso",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class)
                            )
                    }
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Informação inválida",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                                    // schema = @Schema(implementation = Mensagem.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> editarAgenda(@RequestBody Agenda agenda){
        return agendaService.editarAgenda(agenda);
    }
    @DeleteMapping("/agenda/{id}")
    @Operation(summary = "Rota responsável por remover um agendamento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente removido com sucesso",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class)
                            )
                    }
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Informação inválida",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                                    // schema = @Schema(implementation = Mensagem.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> removerAgenda(@PathVariable Long id){
        return agendaService.removerAgenda(id);
    }
}
