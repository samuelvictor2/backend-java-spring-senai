package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.repositories.ClienteRepository;
import org.example.repositories.EnderecoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> getAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente obj) {
        try {
            obj.setCliId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj;
        } catch (DataIntegrityViolationException e) {
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public Cliente update(Long id, ClienteDTO objDTO){
        try {
            Cliente entity = findById(id);
            entity.setCliNome(objDTO.getCliNome());
            entity.setCliCpf(objDTO.getCliCpf());

            // Atualiza primeiro endereço e contato — adapte se precisar atualizar mais
            Endereco endereco = entity.getEnderecos().get(0);
            endereco.setEndRua(objDTO.getEndRua());
            endereco.setEndNumero(objDTO.getEndNumero());
            endereco.setEndCidade(objDTO.getEndCidade());
            endereco.setEndCep(objDTO.getEndCep());
            endereco.setEndEstado(objDTO.getEndEstado());

            Contato contato = entity.getContatos().get(0);
            contato.setConCelular(objDTO.getConCelular());
            contato.setConTelefoneComercial(objDTO.getConTelefoneComercial());
            contato.setConEmail(objDTO.getConEmail());

            repository.save(entity);
            return entity;
        } catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public void deleteCliente(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        Cliente cliente = new Cliente(null, objDto.getCliNome(), objDto.getCliCpf());

        Endereco endereco = new Endereco(
                null,
                cliente,
                null, // Fornecedor é null porque é Cliente
                objDto.getEndRua(),
                objDto.getEndNumero(),
                objDto.getEndCidade(),
                objDto.getEndCep(),
                objDto.getEndEstado()
        );

        Contato contato = new Contato(
                null,
                cliente,
                null, // Fornecedor é null porque é Cliente
                objDto.getConCelular(),
                objDto.getConTelefoneComercial(),
                objDto.getConEmail()
        );

        cliente.getEnderecos().add(endereco);
        cliente.getContatos().add(contato);

        return cliente;
    }

    public ClienteDTO toNewDTO(Cliente obj) {
        ClienteDTO dto = new ClienteDTO();

        dto.setCliId(obj.getCliId());
        dto.setCliNome(obj.getCliNome());
        dto.setCliCpf(obj.getCliCpf());

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
