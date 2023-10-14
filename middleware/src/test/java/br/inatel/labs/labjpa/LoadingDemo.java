package br.inatel.labs.labjpa;

import java.time.LocalDate;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.service.NotaCompraService;

@SpringBootTest
public class LoadingDemo {

    @Autowired
    private NotaCompraService service;

    @Test
    public void demoEagerLoading() {

        try {

            NotaCompraItem item = service.buscarNotaCompraItemPeloId(1L);
            LocalDate dataEmissao = item.getNotaCompra().getDataEmissao();

            System.out.println(dataEmissao);
            System.out.println("Aconteceu o carregamento EAGER");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void lazyLoading() {

        try {

            NotaCompra nota = service.buscarNotaCompraPeloId(1L);
            List<NotaCompraItem> listaNotaCompraItem = nota.listaNotaCompraItem();
            int tamanho = listaNotaCompraItem.size();

            System.out.println(tamanho);

        } catch (Exception e) {
            System.out.println("O carregamento foi LAZY");
            e.printStackTrace();
        }

    }

    @Test
    public void lazyLoadingPlanejado() {

        try {

            NotaCompra nota = service.buscarNotaCompraPeloIdComListaItem(1L);
            List<NotaCompraItem> listaNotaCompraItem = nota.listaNotaCompraItem();
            int tamanho = listaNotaCompraItem.size();

            System.out.println(tamanho);
            System.out.println("O carregamento ocorreu normalmente");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
