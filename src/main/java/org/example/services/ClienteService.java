package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.repositories.ClienteRepository;
import org.example.services.exeptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO create(ClienteDTO dto) {
        Cliente cliente = fromDTO(dto);
        cliente = clienteRepository.save(cliente);
        return toDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO dto) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            cliente.setCliNome(dto.getCliNome());
            cliente.setCliCpf(dto.getCliCpf());  // Atribuindo o CPF

            // Atualiza ou cria contato
            Contato contato = cliente.getContatos().isEmpty() ? new Contato() : cliente.getContatos().get(0);
            contato.setConCelular(dto.getConCelular());
            contato.setConTelefoneComercial(dto.getConTelefoneComercial());
            contato.setConEmail(dto.getConEmail());
            contato.setConCliente(cliente);
            cliente.getContatos().clear();
            cliente.getContatos().add(contato);

            // Atualiza ou cria endereço
            Endereco endereco = cliente.getEnderecos().isEmpty() ? new Endereco() : cliente.getEnderecos().get(0);
            endereco.setEndRua(dto.getEndRua());
            endereco.setEndNumero(dto.getEndNumero());
            endereco.setEndCidade(dto.getEndCidade());
            endereco.setEndCep(dto.getEndCep());
            endereco.setEndEstado(dto.getEndEstado());
            endereco.setEndCliente(cliente);
            cliente.getEnderecos().clear();
            cliente.getEnderecos().add(endereco);

            cliente = clienteRepository.save(cliente);
            return toDTO(cliente);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toDTO(cliente);
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteCliente(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Violação de integridade ao tentar excluir Cliente com ID: " + id);
        }
    }

    // Métodos auxiliares

    private Cliente fromDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setCliNome(dto.getCliNome());
        cliente.setCliCpf(dto.getCliCpf());  // Atribuindo o CPF

        Contato contato = new Contato();
        contato.setConCelular(dto.getConCelular());
        contato.setConTelefoneComercial(dto.getConTelefoneComercial());
        contato.setConEmail(dto.getConEmail());
        contato.setConCliente(cliente);
        cliente.getContatos().add(contato);

        Endereco endereco = new Endereco();
        endereco.setEndRua(dto.getEndRua());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndCidade(dto.getEndCidade());
        endereco.setEndCep(dto.getEndCep());
        endereco.setEndEstado(dto.getEndEstado());
        endereco.setEndCliente(cliente);
        cliente.getEnderecos().add(endereco);

        return cliente;
    }

    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setCliId(cliente.getCliId());
        dto.setCliNome(cliente.getCliNome());
        dto.setCliCpf(cliente.getCliCpf());  // Incluindo CPF no DTO

        if (!cliente.getContatos().isEmpty()) {
            Contato contato = cliente.getContatos().get(0);
            dto.setConCelular(contato.getConCelular());
            dto.setConTelefoneComercial(contato.getConTelefoneComercial());
            dto.setConEmail(contato.getConEmail());
        }

        if (!cliente.getEnderecos().isEmpty()) {
            Endereco endereco = cliente.getEnderecos().get(0);
            dto.setEndRua(endereco.getEndRua());
            dto.setEndNumero(endereco.getEndNumero());
            dto.setEndCidade(endereco.getEndCidade());
            dto.setEndCep(endereco.getEndCep());
            dto.setEndEstado(endereco.getEndEstado());
        }

        return dto;
    }
}