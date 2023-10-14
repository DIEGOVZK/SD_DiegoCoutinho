package br.inatel.labs.labjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.Produto;
import br.inatel.labs.labjpa.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto p) {
        p = repository.save(p);
        return p;
    }

    public Optional<Produto> buscarPeloId(Long id) {
        return repository.findById(id);
    }

    public List<Produto> listar() {
        List<Produto> produtos = repository.findAll();
        return produtos;
    }

    public void remove(Produto p) {
        repository.delete(p);
    }
}
