package org.example.services;

import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.repositories.FornecedorRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public FornecedorDTO create(FornecedorDTO dto) {
        Fornecedor fornecedor = fromDTO(dto);
        fornecedor = fornecedorRepository.save(fornecedor);
        return toDTO(fornecedor);
    }

    public FornecedorDTO update(Long id, FornecedorDTO dto) {
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);
        if (optionalFornecedor.isPresent()) {
            Fornecedor fornecedor = optionalFornecedor.get();

            fornecedor.setForNomeFantasia(dto.getForNomeFantasia());
            fornecedor.setForCnpj(dto.getForCnpj());  // Atribuindo o CNPJ
            fornecedor.setForRazaoSocial(dto.getForRazaoSocial());  // Atribuindo a Razão Social

            // Atualiza ou cria contato
            Contato contato = fornecedor.getContatos().isEmpty() ? new Contato() : fornecedor.getContatos().get(0);
            contato.setConCelular(dto.getConCelular());
            contato.setConTelefoneComercial(dto.getConTelefoneComercial());
            contato.setConEmail(dto.getConEmail());
            contato.setConFornecedor(fornecedor);
            fornecedor.getContatos().clear();
            fornecedor.getContatos().add(contato);

            // Atualiza ou cria endereço
            Endereco endereco = fornecedor.getEnderecos().isEmpty() ? new Endereco() : fornecedor.getEnderecos().get(0);
            endereco.setEndRua(dto.getEndRua());
            endereco.setEndNumero(dto.getEndNumero());
            endereco.setEndCidade(dto.getEndCidade());
            endereco.setEndCep(dto.getEndCep());
            endereco.setEndEstado(dto.getEndEstado());
            endereco.setEndFornecedor(fornecedor);
            fornecedor.getEnderecos().clear();
            fornecedor.getEnderecos().add(endereco);

            fornecedor = fornecedorRepository.save(fornecedor);
            return toDTO(fornecedor);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public FornecedorDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toDTO(fornecedor);
    }

    public List<FornecedorDTO> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }

    // Métodos auxiliares

    private Fornecedor fromDTO(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setForNomeFantasia(dto.getForNomeFantasia());
        fornecedor.setForCnpj(dto.getForCnpj());  // Atribuindo o CNPJ
        fornecedor.setForRazaoSocial(dto.getForRazaoSocial());  // Atribuindo a Razão Social

        // Lógica de criação de contato e endereço
        Contato contato = new Contato();
        contato.setConCelular(dto.getConCelular());
        contato.setConTelefoneComercial(dto.getConTelefoneComercial());
        contato.setConEmail(dto.getConEmail());
        contato.setConFornecedor(fornecedor);
        fornecedor.getContatos().add(contato);

        Endereco endereco = new Endereco();
        endereco.setEndRua(dto.getEndRua());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndCidade(dto.getEndCidade());
        endereco.setEndCep(dto.getEndCep());
        endereco.setEndEstado(dto.getEndEstado());
        endereco.setEndFornecedor(fornecedor);
        fornecedor.getEnderecos().add(endereco);

        return fornecedor;
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setForId(fornecedor.getForId());
        dto.setForNomeFantasia(fornecedor.getForNomeFantasia());
        dto.setForCnpj(fornecedor.getForCnpj());  // Incluindo CNPJ no DTO
        dto.setForRazaoSocial(fornecedor.getForRazaoSocial());  // Incluindo Razão Social no DTO

        if (!fornecedor.getContatos().isEmpty()) {
            Contato contato = fornecedor.getContatos().get(0);
            dto.setConCelular(contato.getConCelular());
            dto.setConTelefoneComercial(contato.getConTelefoneComercial());
            dto.setConEmail(contato.getConEmail());
        }

        if (!fornecedor.getEnderecos().isEmpty()) {
            Endereco endereco = fornecedor.getEnderecos().get(0);
            dto.setEndRua(endereco.getEndRua());
            dto.setEndNumero(endereco.getEndNumero());
            dto.setEndCidade(endereco.getEndCidade());
            dto.setEndCep(endereco.getEndCep());
            dto.setEndEstado(endereco.getEndEstado());
        }

        return dto;
    }
}
