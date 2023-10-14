package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.dto.TotalCompradoPorFornecedor;
import br.inatel.labs.labjpa.repository.RelatorioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository repository;

    public List<TotalCompradoPorFornecedor> pesquisarTotalCompradoPorFornecedor() {
        return repository.pesquisarTotalCompradoPorFornecedor();
    };

}
