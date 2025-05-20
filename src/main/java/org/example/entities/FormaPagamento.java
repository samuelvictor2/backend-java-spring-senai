package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @NotBlank(message = "Forma de pagamento é obrigatorio")
    @Size(max = 100, message = "Forma de pagamento deve ter no maximo 100 caractere")
    @Column(name = "FPG_DESCRICAO")
    private String fpgDescricao;

    @NotBlank(message = "Status de ativo é obrigatório")
    @Column(name = "FPG_ATIVO")
    private String fpgAtivo;

    @NotBlank(message = "Informação sobre parcelamento é obrigatória")
    @Column(name = "FPG_PERMITE_PARCELAMENTO")
    private String fpgPermiteParcelamento;

    @NotBlank(message = "Número máximo de parcelas é obrigatório")
    @Size (max = 10, message = "Número máximo 10 parcelas")
    @Column(name = "FPG_NUMERO_MAXIMO_PARCELA")
    private Integer fpgNumeroMaximoParcela;

    @NotBlank(message = "Taxa adicional é obrigatória")
    @Column(name = "FPG_TAXA_ADICIONAL")
    private String fpgTaxaAdicional;
    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgDescricao, String fpgAtivo, String fpgPermiteParcelamento, Integer fpgNumeroMaximoParcela, String fpgTaxaAdicional) {
        this.fpgId = fpgId;
        this.fpgDescricao = fpgDescricao;
        this.fpgAtivo = fpgAtivo;
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
        this.fpgNumeroMaximoParcela = fpgNumeroMaximoParcela;
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }

    public Long getFpgId() {
        return fpgId;
    }

    public void setFpgId(Long fpgId) {
        this.fpgId = fpgId;
    }

    public String getFpgDescricao() {
        return fpgDescricao;
    }

    public void setFpgDescricao(String fpgDescricao) {
        this.fpgDescricao = fpgDescricao;
    }

    public String getFpgAtivo() {
        return fpgAtivo;
    }

    public void setFpgAtivo(String fpgAtivo) {
        this.fpgAtivo = fpgAtivo;
    }

    public String getFpgPermiteParcelamento() {
        return fpgPermiteParcelamento;
    }

    public void setFpgPermiteParcelamento(String fpgPermiteParcelamento) {
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
    }

    public Integer getFpgNumeroMaximoParcela() {
        return fpgNumeroMaximoParcela;
    }

    public void setFpgNumeroMaximoParcela(Integer fpgNumeroMaximoParcela) {
        this.fpgNumeroMaximoParcela = fpgNumeroMaximoParcela;
    }

    public String getFpgTaxaAdicional() {
        return fpgTaxaAdicional;
    }

    public void setFpgTaxaAdicional(String fpgTaxaAdicional) {
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }
}