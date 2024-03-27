package br.com.pdev.agendamento.service;

// Importações necessárias
import br.com.pdev.agendamento.model.Paciente;
import br.com.pdev.agendamento.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service // Marca a classe como um componente do Spring, especificamente um Service
@Transactional // Garante que os métodos sejam executados dentro de uma transação
@RequiredArgsConstructor // Lombok: cria um construtor com 1 parâmetro para cada campo final
public class PacienteService {

    // Injeção do repositório de pacientes, facilitando operações de banco de dados
    private final PacienteRepository pacienteRepository;

    // Método para cadastrar um paciente
    // Método para cadastrar um novo paciente, validando a unicidade de e-mail e CPF.
    public Paciente cadastrarPaciente(Paciente paciente) {
        // Verifica se o e-mail já está cadastrado no banco de dados.
        if (pacienteRepository.existsByEmail(paciente.getEmail())) {
            // Lança uma exceção com status BAD_REQUEST se o e-mail já estiver em uso.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado.");
        }
        // Verifica se o CPF já está cadastrado no banco de dados.
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            // Lança uma exceção com status BAD_REQUEST se o CPF já estiver em uso.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado.");
        }
        // Salva o paciente no banco de dados caso não haja conflitos e retorna o paciente salvo.
        return pacienteRepository.save(paciente);
    }


    // Método para listar todos os pacientes
    public ResponseEntity<?> listarTodosPaciente() {
        // Busca todos os pacientes no banco de dados e retorna junto com o status OK
        return new ResponseEntity<>(pacienteRepository.findAll(), HttpStatus.OK);
    }

    // Método para buscar um paciente pelo seu ID
    public ResponseEntity<?> buscarUmPacientePorId(Long id) {
        // Tenta encontrar o paciente pelo ID. O retorno é um Optional, então é bom tratar o caso de não encontrar
        return pacienteRepository.findById(id)
                .map(paciente -> new ResponseEntity<>(paciente, HttpStatus.OK)) // Se encontrar, retorna o paciente com status OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não, retorna NOT FOUND
    }

    // Método para editar um paciente
    public Paciente editarPaciente(Paciente paciente) {
        // Verifica se o paciente a ser editado possui um ID. Se não tiver, lança uma exceção indicando que o ID é necessário.
        if (paciente.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do paciente é necessário para atualização.");
        }

        // Busca o paciente no banco de dados pelo ID fornecido. Se não encontrar, lança uma exceção indicando que o paciente não foi encontrado.
        Paciente pacienteExistente = pacienteRepository.findById(paciente.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));

        // Verifica se o e-mail fornecido para o paciente já está em uso por outro paciente.
        // Primeiro, verifica se algum paciente diferente do atual tem o mesmo e-mail.
        boolean emailInUse = pacienteRepository.existsByEmail(paciente.getEmail()) && !pacienteExistente.getEmail().equals(paciente.getEmail());
        if (emailInUse) {
            // Se o e-mail já estiver em uso, lança uma exceção indicando o problema.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já em uso por outro paciente.");
        }

        // Realiza uma verificação semelhante para o CPF, garantindo que ele seja único entre os pacientes.
        boolean cpfInUse = pacienteRepository.existsByCpf(paciente.getCpf()) && !pacienteExistente.getCpf().equals(paciente.getCpf());
        if (cpfInUse) {
            // Se o CPF já estiver em uso, lança uma exceção.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já em uso por outro paciente.");
        }

        // Se passar por todas as verificações sem lançar exceções, salva as alterações do paciente no banco de dados.
        // Este passo atualiza o paciente com os novos dados fornecidos.
        return pacienteRepository.save(paciente);
    }

    // Método para remover um paciente pelo ID
    public ResponseEntity<?> removerPaciente(Long id) {
        // Primeiro, verifica se o paciente com o ID fornecido existe
        if (pacienteRepository.existsById(id)) {
            // Se existir, deleta o paciente e retorna status OK
            pacienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Se não existir, retorna NOT FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
