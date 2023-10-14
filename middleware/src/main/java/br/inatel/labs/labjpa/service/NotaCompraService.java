package br.inatel.labs.labjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.repository.NotaCompraItemRepository;
import br.inatel.labs.labjpa.repository.NotaCompraRepository;

@Service
@Transactional
public class NotaCompraService {

    @Autowired
    private NotaCompraRepository ncRepository;

    @Autowired
    private NotaCompraItemRepository nciRepository;

    // ** NotaCompra ** \\

    public NotaCompra salvar(NotaCompra nc) {
        nc = ncRepository.save(nc);
        return nc;
    }

    public Optional<NotaCompra> buscarNotaCompraPeloId(Long id) {
        return ncRepository.findById(id);
    }

    // public NotaCompra buscarNotaCompraPeloIdComListaItem(Long id) {
    // NotaCompra nota = em.find(NotaCompra.class, id);
    // nota.listaNotaCompraItem().size();
    // return nota;
    // }

    public List<NotaCompra> listarNotaCompra() {
        return ncRepository.findAll();
    }

    public void remover(NotaCompra nc) {
        ncRepository.delete(nc);
    }

    // ** NotaCompraItem ** \\

    public NotaCompraItem salvarNotaCompraItem(NotaCompraItem nci) {
        nci = nciRepository.save(nci);
        return nci;
    }

    public Optional<NotaCompraItem> buscarNotaCompraItemPeloId(Long id) {
        return nciRepository.findById(id);
    }

    public List<NotaCompraItem> listarNotaCompraItem() {
        return nciRepository.findAll();
    }

    public void remover(NotaCompraItem nci) {
        nciRepository.delete(nci);
    }
}
