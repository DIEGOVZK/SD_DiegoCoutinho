package br.inatel.labs.labrest.server.service;

import java.util.List;
import java.util.Random;
import java.util.Optional;
import java.util.ArrayList;

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
    
    // Parte CRUD: Create, Read, Update, Delete

    public Optional<Produto> findById(Long id) {
        return this.produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Produto create(Produto produto) {
        long id = new Random().nextLong();
        produto.setId(id);
        this.produtos.add(produto);
        return produto;
    }

    public void update(Produto produto) {
        this.produtos.removeIf(p -> p.getId().equals(produto.getId()));
        this.produtos.add(produto);
    }

    public void delete(Long id) {
        this.produtos.removeIf(p -> p.getId().equals(id));
    }

}
