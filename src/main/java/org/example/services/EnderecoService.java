package org.example.services;

import org.example.entities.Endereco;
import org.example.repositories.EnderecoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> getAll() {
        return repository.findAll();
    }

    public Endereco findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Endereco insert(Endereco endereco) {
        return repository.save(endereco);
    }

    public boolean update(Long id, Endereco endereco) {
        Optional<Endereco> optionalEndereco = repository.findById(id);
        if (optionalEndereco.isPresent()) {
            Endereco enderecoSistema = optionalEndereco.get();
            enderecoSistema.setEndCep(endereco.getEndCep());
            enderecoSistema.setEndCidade(endereco.getEndCidade());
            enderecoSistema.setEndEstado(endereco.getEndEstado());
            enderecoSistema.setEndRua(endereco.getEndRua());
            enderecoSistema.setEndNumero(endereco.getEndNumero());
            repository.save(enderecoSistema);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}