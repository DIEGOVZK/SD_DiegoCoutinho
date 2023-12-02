package br.inatel.labs.labrest.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.inatel.labs.labrest.server.model.Produto;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<Produto>();

    @PostConstruct
    private void setup() {
        this.produtos.add(new Produto(1L, "Smartphone", new BigDecimal("999.99")));
        this.produtos.add(new Produto(3L, "Headphones", new BigDecimal("149.99")));
        this.produtos.add(new Produto(2L, "Laptop-t6", new BigDecimal("1999.99")));
    }

    public List<Produto> findAll() {
        return this.produtos;
    }

    public Optional<Produto> findById(Long id) {
        return this.produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
