package org.example.entities;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_ID")
    private Long forId;

    @NotBlank(message = "nome fantasia  é obrigatorio")
    @Size(max = 100, message = "nome deve ter no maximo 100 caractere")
    @Column(name = "FOR_NOME_FANTASIA")
    private String forNomeFantasia;

    @NotBlank(message = "CNPJ é obrigatorio")
    @CNPJ(message = "CNPJ invalido")
    @Column(name = "FOR_CNPJ", unique = true, length = 15)
    private String forCnpj;

    @NotBlank(message = "razao social  é obrigatorio")
    @Size(max = 100, message = "razao social deve ter no maximo 100 caractere")
    @Column(name = "FOR_RAZAO_SOCIAL")
    private String forRazaoSocial;

    public Fornecedor() {
    }

    public Fornecedor(Long forId, String forNome, String forNomeFantasia, String forCnpj, String forRazaoSocial) {
        this.forId = forId;
        this.forNomeFantasia = forNomeFantasia;
        this.forCnpj = forCnpj;
        this.forRazaoSocial = forRazaoSocial;
    }

    public Long getForId() {
        return forId;
    }

    public void setForId(Long forId) {
        this.forId = forId;
    }

    public String getForNomeFantasia() {
        return forNomeFantasia;
    }

    public void setForNomeFantasia(String forNomeFantasia) {
        this.forNomeFantasia = forNomeFantasia;
    }

    public String getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(String forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForRazaoSocial() {
        return forRazaoSocial;
    }

    public void setForRazaoSocial(String forRazaoSocial) {
        this.forRazaoSocial = forRazaoSocial;
    }

}
