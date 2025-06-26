package org.example.services;

import org.example.dto.FornecedorDTO;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.entities.Fornecedor;
import org.example.repositories.FornecedorRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Fornecedor insert(Fornecedor fornecedor) {
        fornecedor.setForId(null);
        return repository.save(fornecedor);
    }

    public Fornecedor update(Long id, FornecedorDTO dto) {
        Fornecedor entity = findById(id);
        entity.setForCnpj(dto.getForCnpj());
        entity.setForNomeFantasia(dto.getForNomeFantasia());
        entity.setForRazaoSocial(dto.getForRazaoSocial());

        Endereco endereco = entity.getEnderecos().get(0);
        endereco.setEndRua(dto.getEndRua());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndCidade(dto.getEndCidade());
        endereco.setEndCep(dto.getEndCep());
        endereco.setEndEstado(dto.getEndEstado());

        Contato contato = entity.getContatos().get(0);
        contato.setConCelular(dto.getConCelular());
        contato.setConTelefoneComercial(dto.getConTelefoneComercial());
        contato.setConEmail(dto.getConEmail());

        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Fornecedor fromDTO(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor(null, dto.getForNomeFantasia(), dto.getForCnpj(), dto.getForRazaoSocial());

        Endereco endereco = new Endereco(null, null, fornecedor, dto.getEndRua(), dto.getEndNumero(), dto.getEndCidade(), dto.getEndCep(), dto.getEndEstado());
        Contato contato = new Contato(null, null, fornecedor, dto.getConCelular(), dto.getConTelefoneComercial(), dto.getConEmail());

        fornecedor.getEnderecos().add(endereco);
        fornecedor.getContatos().add(contato);

        return fornecedor;
    }

    public List<Fornecedor> getAll() {
        return repository.findAll();
    }


    public FornecedorDTO toDTO(Fornecedor obj) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setForId(obj.getForId());
        dto.setForNomeFantasia(obj.getForNomeFantasia());
        dto.setForCnpj(obj.getForCnpj());
        dto.setForRazaoSocial(obj.getForRazaoSocial());

        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }
}
