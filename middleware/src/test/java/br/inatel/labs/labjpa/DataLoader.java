package br.inatel.labs.labjpa;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import br.inatel.labs.labjpa.entity.Produto;
import br.inatel.labs.labjpa.entity.Fornecedor;
import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.service.ProdutoService;
import br.inatel.labs.labjpa.service.FornecedorService;
import br.inatel.labs.labjpa.service.NotaCompraService;

@SpringBootTest
class DataLoader {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private NotaCompraService notaCompraService;

	/**
	 * @brief Test method that loads some products and prints them to the console.
	 *        This method creates some Produto objects, saves them using the
	 *        produtoService,
	 *        lists all the saved products and prints them to the console using
	 *        forEach.
	 */
	@Test
	void loadProduto() {

		// ** Produtos ** \\

		Produto p1 = new Produto("Martelo");
		p1 = produtoService.salvar(p1);

		Produto p2 = new Produto("Chave de fenda");
		p2 = produtoService.salvar(p2);

		Produto p3 = new Produto("Alicate");
		p3 = produtoService.salvar(p3);

		Produto p4 = new Produto("Serra elétrica");
		p4 = produtoService.salvar(p4);

		Produto p5 = new Produto("Furadeira");
		p5 = produtoService.salvar(p5);

		List<Produto> listaProduto = produtoService.listar();
		listaProduto.forEach(System.out::println);

		// ** Fornecedores ** \\

		Fornecedor f1 = new Fornecedor("Loja do mecânico");
		f1 = fornecedorService.salvar(f1);

		Fornecedor f2 = new Fornecedor("Ferramentas LTDA");
		f2 = fornecedorService.salvar(f2);

		Fornecedor f3 = new Fornecedor("Casa das ferramentas");
		f3 = fornecedorService.salvar(f3);

		Fornecedor f4 = new Fornecedor("Ferragens do Brasil");
		f4 = fornecedorService.salvar(f4);

		Fornecedor f5 = new Fornecedor("Ferramentas e Cia");
		f5 = fornecedorService.salvar(f5);

		List<Fornecedor> listaFornecedor = fornecedorService.listar();
		listaFornecedor.forEach(System.out::println);

		// ** Nota compra ** \\

		NotaCompra nc1 = new NotaCompra(LocalDate.of(2021, 1, 15), f1);
		nc1 = notaCompraService.salvar(nc1);

		NotaCompra nc2 = new NotaCompra(LocalDate.of(2021, 2, 20), f2);
		nc2 = notaCompraService.salvar(nc2);

		NotaCompra nc3 = new NotaCompra(LocalDate.of(2021, 3, 25), f3);
		nc3 = notaCompraService.salvar(nc3);

		NotaCompra nc4 = new NotaCompra(LocalDate.of(2021, 4, 30), f4);
		nc4 = notaCompraService.salvar(nc4);

		NotaCompra nc5 = new NotaCompra(LocalDate.of(2021, 5, 5), f5);
		nc5 = notaCompraService.salvar(nc5);

		// ** Nota compra item ** \\

		NotaCompraItem i1_1 = new NotaCompraItem(nc1, p1, new BigDecimal("300.00"), 2);
		i1_1 = notaCompraService.salvarNotaCompraItem(i1_1);

		NotaCompraItem i1_2 = new NotaCompraItem(nc1, p2, new BigDecimal("50.00"), 5);
		i1_2 = notaCompraService.salvarNotaCompraItem(i1_2);

		NotaCompraItem i2_1 = new NotaCompraItem(nc2, p3, new BigDecimal("100.00"), 3);
		i2_1 = notaCompraService.salvarNotaCompraItem(i2_1);

		NotaCompraItem i3_1 = new NotaCompraItem(nc3, p4, new BigDecimal("500.00"), 1);
		i3_1 = notaCompraService.salvarNotaCompraItem(i3_1);

		NotaCompraItem i4_1 = new NotaCompraItem(nc4, p5, new BigDecimal("200.00"), 4);
		i4_1 = notaCompraService.salvarNotaCompraItem(i4_1);

		List<NotaCompraItem> listaNotaCompraItem = notaCompraService.listarNotaCompraItem();
		listaNotaCompraItem.forEach(System.out::println);
	}

}
