package br.inatel.labs.labrest.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.labs.labrest.server.model.Produto;
import br.inatel.labs.labrest.server.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> getProdutos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Long produtoId) {
        Optional<Produto> produto = service.findById(produtoId);

        if (!produto.isPresent()) {
            String errMsg = String.format("Produto com id %d não encontrado", produtoId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg);
        }

        return produto.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto produto) {
        return service.create(produto);
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putProduto(@RequestBody Produto produto) {
        service.update(produto);
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable("id") Long produtoId) {
        service.delete(produtoId);
    }

}