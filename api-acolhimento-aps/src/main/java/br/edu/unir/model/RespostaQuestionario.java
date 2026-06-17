package br.edu.unir.model;
import org.checkerframework.checker.units.qual.N;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaQuestionario {
    // Dados gerais do usuário
    @NotBlank(message = "A faixa etária é obrigatória")
    private String faixaEtaria;

    @NotBlank(message = "A identidade de gênero é obrigatória")
    private String identidadeGenero;

    private String escolaridade;

    @NotNull(message = "Informe se possui deficiência")
    private Boolean possuiDeficiencia;
    private String frequenciaUsoUnidade;
    private String tipoAtendimentoRecebido;

    //Avaliação de antendimento
    private Integer tempoEspera;
    private Integer facilAtendimento;
    private Integer ouvidoComAtencao;
    private Integer tempoExplicarProblema;
    private Integer informacoesClarasSaude;
    private Integer sentiOpiniaoConsiderada;
    private Integer tenhoConfiancaProfissionais;
    private Integer conhecemHistoricoSaude;
    private Integer problemaResolvido;
    private Integer encaminhadoOutrosServicos;
    private Integer tratadoRespeito;
    private Integer empatiaAtendimento;
    private Integer sentiValorizado;
    private Integer sentiAcolhido;
    private Integer continuidadeCuidado;
    private Integer sintoSeguro;

    @NotNull(message = "O campo de satisfação de atendimento é obrigatório")
    private Integer satisfeitoAtendimento;
    private Integer indicariaUnidade;
    private Integer continuarUsandoServicos;
    private String consideracaoPositiva;
    private String poderiaMelhorar;

}