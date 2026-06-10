package br.edu.unir.controller;

import br.edu.unir.model.RespostaQuestionario;
import br.edu.unir.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Questionário APS", description = "Endpoints para coleta e análise de dados da Atenção Primária à Saúde")
@RestController
@RequestMapping("/api/questionario")
public class QuestionarioController {

    @Operation(summary = "Lista todas as respostas coletadas",
                description = "Recupera todos os documentos da coleção no Firebase para fins de análise estatística.")
    

    @GetMapping
    public ResponseEntity<?> listar(){
        try {
            List<RespostaQuestionario> lista = respostaService.listarTodas();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro ao buscar dados: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private RespostaService respostaService; // Spring instalará o serviço de resposta

    @Operation(summary = "Salva uma nova resposta no Firebase", 
                description = "Recebe 27 atributos validados sobre o acolhimento na APS e os persiste no Firestore."
    )

    @PostMapping
    public ResponseEntity<String> receberResposta(@Valid@RequestBody RespostaQuestionario resposta){
        String resultado = respostaService.salvarResposta(resposta);

        if(resultado.contains("Sucesso")){
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }
    }
}