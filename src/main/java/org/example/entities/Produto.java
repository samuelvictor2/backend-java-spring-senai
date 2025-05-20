package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long proId;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "PRO_NOME")
    private String proNome;

    @NotBlank(message = "Preço de custo é obrigatório")
    @DecimalMin(value = "0.00", inclusive = true, message = "Preço de custo não pode ser negativo")
    @Digits(integer = 8, fraction = 2, message = "Preço de custo inválido")
    @Column(name = "PRO_PRECO_CUSTO", precision = 10, scale = 2)
    private Double proPrecoCusto;

    @NotBlank(message = "Preço de venda é obrigatório")
    @DecimalMin(value = "0.00", inclusive = true, message = "Preço de venda não pode ser negativo")
    @Digits(integer = 8, fraction = 2, message = "Preço de venda inválido")
    @Column(name = "PRO_PRECO_VENDA", precision = 10, scale = 2)
    private Double proPrecoVenda;

    @NotBlank(message = "Quantidade em estoque é obrigatória")
    @Min(value = 0, message = "Quantidade em estoque não pode ser negativa")
    @Column(name = "PRO_QUANTIDADE_ESTOQUE")
    private Integer proQuantidadeEstoque;

    @NotBlank(message = "Categoria é obrigatória")
    @Size(max = 50, message = "Categoria deve ter no máximo 50 caracteres")
    @Column(name = "PRO_CATEGORIA")
    private String proCategoria;

    @NotBlank(message = "Código de barras é obrigatório")
    @Size(max = 20, message = "Código de barras deve ter no máximo 20 caracteres")
    @Column(name = "PRO_CODIGO_DE_BARRAS")
    private String proCodigoDeBarras;

    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    @Column(name = "PRO_MARCA")
    private String proMarca;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(max = 10, message = "Unidade de medida deve ter no máximo 10 caracteres")
    @Column(name = "PRO_UNIDADE_MEDIDA")
    private String proUnidadeMedida;

    @NotBlank(message = "Status de ativo é obrigatório")
    @Column(name = "PRO_ATIVO")
    private String proAtivo;

    @NotBlank(message = "Data de cadastro é obrigatória")
    @Column(name = "PRO_DATA_CADASTRO")
    private LocalDateTime proDataCadastro;

    @NotBlank(message = "Data de atualização é obrigatória")
    @Column(name = "PRO_DATA_ATUALIZACAO")
    private LocalDateTime proDataAtualizacao;



    public Produto() {
    }

    public Produto(Long proId, String proNome, Double proPrecoCusto, Double proPrecoVenda, Integer proQuantidadeEstoque,
                   String proCategoria, String proCodigoDeBarras, String proMarca, String proUnidadeMedida, String proAtivo,
                   LocalDateTime proDataCadastro, LocalDateTime proDataAtualizacao) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrecoCusto = proPrecoCusto;
        this.proPrecoVenda = proPrecoVenda;
        this.proQuantidadeEstoque = proQuantidadeEstoque;
        this.proCategoria = proCategoria;
        this.proCodigoDeBarras = proCodigoDeBarras;
        this.proMarca = proMarca;
        this.proUnidadeMedida = proUnidadeMedida;
        this.proAtivo = proAtivo;
        this.proDataCadastro = proDataCadastro;
        this.proDataAtualizacao = proDataAtualizacao;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public Double getProPrecoCusto() {
        return proPrecoCusto;
    }

    public void setProPrecoCusto(Double proPrecoCusto) {
        this.proPrecoCusto = proPrecoCusto;
    }

    public Double getProPrecoVenda() {
        return proPrecoVenda;
    }

    public void setProPrecoVenda(Double proPrecoVenda) {
        this.proPrecoVenda = proPrecoVenda;
    }

    public Integer getProQuantidadeEstoque() {
        return proQuantidadeEstoque;
    }

    public void setProQuantidadeEstoque(Integer proQuantidadeEstoque) {
        this.proQuantidadeEstoque = proQuantidadeEstoque;
    }

    public String getProCategoria() {
        return proCategoria;
    }

    public void setProCategoria(String proCategoria) {
        this.proCategoria = proCategoria;
    }

    public String getProCodigoDeBarras() {
        return proCodigoDeBarras;
    }

    public void setProCodigoDeBarras(String proCodigoDeBarras) {
        this.proCodigoDeBarras = proCodigoDeBarras;
    }

    public String getProMarca() {
        return proMarca;
    }

    public void setProMarca(String proMarca) {
        this.proMarca = proMarca;
    }

    public String getProUnidadeMedida() {
        return proUnidadeMedida;
    }

    public void setProUnidadeMedida(String proUnidadeMedida) {
        this.proUnidadeMedida = proUnidadeMedida;
    }

    public String getProAtivo() {
        return proAtivo;
    }

    public void setProAtivo(String proAtivo) {
        this.proAtivo = proAtivo;
    }

    public LocalDateTime getProDataCadastro() {
        return proDataCadastro;
    }

    public void setProDataCadastro(LocalDateTime proDataCadastro) {
        this.proDataCadastro = proDataCadastro;
    }

    public LocalDateTime getProDataAtualizacao() {
        return proDataAtualizacao;
    }

    public void setProDataAtualizacao(LocalDateTime proDataAtualizacao) {
        this.proDataAtualizacao = proDataAtualizacao;
    }
}