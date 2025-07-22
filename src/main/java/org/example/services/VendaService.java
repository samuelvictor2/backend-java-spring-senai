package org.example.services;

import org.example.dto.VendaDTO;
import org.example.entities.Cliente;
import org.example.entities.ItemVenda;
import org.example.entities.Produto;
import org.example.entities.Venda;
import org.example.repositories.ClienteRepository;
import org.example.repositories.ProdutoRepository;
import org.example.repositories.VendaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired private VendaRepository vendaRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private ProdutoRepository produtoRepo;

    public Venda criarVenda(VendaDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setData(LocalDate.now());

        List<ItemVenda> itens = new ArrayList<>();
        double total = 0;

        for (VendaDTO.ItemVendaDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepo.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            double preco = produto.getProPrecoVenda(); // Supondo que Produto tenha getPreco()
            int qtd = itemDto.getQuantidade();
            double subtotal = preco * qtd;

            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(qtd);
            item.setPrecoUnitario(preco);
            item.setSubtotal(subtotal);
            item.setVenda(venda);

            itens.add(item);
            total += subtotal;
        }

        venda.setItens(itens);
        venda.setValorTotal(total);

        return vendaRepo.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepo.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
    }

}
