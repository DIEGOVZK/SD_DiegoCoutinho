package br.inatel.labs.labjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.Fornecedor;
import br.inatel.labs.labjpa.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Fornecedor salvar(Fornecedor f) {
        f = repository.save(f);
        return f;
    }

    public Optional<Fornecedor> buscarPeloId(Long id) {
        return repository.findById(id);
    }

    public List<Fornecedor> listar() {
        List<Fornecedor> listaFornecedor = repository.findAll();
        return listaFornecedor;
    }

    public void remover(Fornecedor f) {
        repository.delete(f);
    }

}
