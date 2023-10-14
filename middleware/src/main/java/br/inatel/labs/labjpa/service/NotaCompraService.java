package br.inatel.labs.labjpa.service;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;

@Service
@Transactional
public class NotaCompraService {

    @PersistenceContext
    private EntityManager em;

    // ** NotaCompra ** \\

    public NotaCompra salvar(NotaCompra nc) {
        nc = em.merge(nc);
        return nc;
    }

    public NotaCompra buscarNotaCompraPeloId(Long id) {
        return em.find(NotaCompra.class, id);
    }

    public NotaCompra buscarNotaCompraPeloIdComListaItem(Long id) {
        NotaCompra nota = em.find(NotaCompra.class, id);
        nota.listaNotaCompraItem().size();
        return nota;
    }

    public List<NotaCompra> listarNotaCompra() {
        return em.createQuery("SELECT nc FROM NotaCompra nc", NotaCompra.class).getResultList();
    }

    public void remover(NotaCompra nc) {
        nc = em.merge(nc);
        em.remove(nc);
    }

    // ** NotaCompraItem ** \\

    public NotaCompraItem salvarNotaCompraItem(NotaCompraItem nci) {
        nci = em.merge(nci);
        return nci;
    }

    public NotaCompraItem buscarNotaCompraItemPeloId(Long id) {
        return em.find(NotaCompraItem.class, id);
    }

    public List<NotaCompraItem> listarNotaCompraItem() {
        return em.createQuery("SELECT nci FROM NotaCompraItem nci", NotaCompraItem.class).getResultList();
    }

    public void remover(NotaCompraItem nci) {
        nci = em.merge(nci);
        em.remove(nci);
    }
}
