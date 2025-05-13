package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @Column(name = "FPG_DESCRICAO")
    private String fpgDescricao;

    @Column(name = "FPG_ATIVO")
    private String fpgAtivo;

    @Column(name = "FPG_PERMITE_PARCELAMENTO")
    private String fpgPermiteParcelamento;
    @Column(name = "FPG_NUMERO_MAXIMO_PARCELA")
    private Integer fpgNumeroMaximoParcela;

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