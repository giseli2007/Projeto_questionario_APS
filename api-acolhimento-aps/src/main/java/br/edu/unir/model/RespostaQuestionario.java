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
    private String tipoAtendimentoDoDia;

    //Avaliação de antendimento
    private Integer tempoEsperaAdequado;
    private Integer notaFacilidadeAtendimento;
    private Integer atencaoProfissionalSaude;
    private Integer tempoProblemaSaude;
    private Integer informacoesClaras;
    private Integer opiniaoConsideradaAtendimento;
    private Integer confiancaEquipeSaude;
    private Integer atendidoProfissionalHistoricoSaude;
    private Integer meuProblemaResolvido;
    private Integer encaminhamentoAdequado;
    private Integer tratadoComRespeito;
    private Integer profissionaisEmpatia;
    private Integer meSentiValorizado;
    private Integer ambienteAcolhedor;
    private Integer perceboContinuidadeCuidado;
    private Integer meSintoSeguro;

    @NotNull(message = "O campo de satisfação de atendimento é obrigatório")
    private Integer satisfeitoAtendimento;
    private Integer indicariaUnidadeSaude;
    private Integer pretendoContinuarUsandoUnidade;
    private String consideracaoPositivaAtendimento;
    private String sugestaoMelhoriaAtendimento;

}