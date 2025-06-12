package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @GeneratedValue
    @JoinColumn
    private Cliente conCliente;

    @NotBlank(message = "celular é obrigatorio")
    @Size(max = 14, message = "celular deve ter no maximo 15 caractere")
    @Column(name = "CON_CELULAR", length = 14)
    private String conCelular;

    @NotBlank(message = "telefone comercial é obrigatorio")
    @Size(max = 14, message = "telefone comercial deve ter no maximo 15 caractere")
    @Column(name = "CON_TELEFONE_COMERCIAL", length = 14)
    private String conTelefoneComercial;

    @NotBlank(message = "email é obrigatorio")
    @Email(message = "email invalido")
    @Size (max = 100, message = "email deve ter no maximo 100 caractere")
    @Column(length = 55, name = "CON_EMAIL")
    private String conEmail;



    public Contato() {
    }

    public Contato(Long conId, Cliente conCliente, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.conCliente = conCliente;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public String getConCelular() {
        return conCelular;
    }

    public void setConCelular(String conCelular) {
        this.conCelular = conCelular;
    }

    public String getConTelefoneComercial() {
        return conTelefoneComercial;
    }

    public void setConTelefoneComercial(String conTelefoneComercial) {
        this.conTelefoneComercial = conTelefoneComercial;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}
