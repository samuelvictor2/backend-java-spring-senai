package org.example.entities;

import org.aspectj.bridge.IMessage;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long cliId;


    @NotBlank(message = "nome é obrigatorio")
    @Size(max = 100, message = "nome deve ter no maximo 100 caractere")
    @Column(name = "CLI_NOME")
    private String cliNome;


    @NotBlank(message = "CPF é obrigatorio")
    @CPF(message = "CPF invalido")
    @Column(name = "CLI_CPF", length = 11)
    private String cliCpf;

    @NotBlank(message = "email é obrigatorio")
    @Email(message = "email invalido")
    @Size (max = 100, message = "email deve ter no maximo 100 caractere")
    @Column(name = "CLI_EMAIL")
    private String cliEmail;

    @NotBlank(message = "telefone é obrigatorio")
    @Size(max = 14, message = "telefone deve ter no maximo 15 caractere")
    @Column(name = "CLI_TELEFONE", length = 14)
    private String cliTelefone;

    public Cliente() {
    }

    public Cliente(Long cliId, String cliNome, String cliCpf, String cliEmail, String cliTelefone) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliCpf = cliCpf;
        this.cliEmail = cliEmail;
        this.cliTelefone = cliTelefone;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliTelefone() {
        return cliTelefone;
    }

    public void setCliTelefone(String cliTelefone) {
        this.cliTelefone = cliTelefone;
    }
}
