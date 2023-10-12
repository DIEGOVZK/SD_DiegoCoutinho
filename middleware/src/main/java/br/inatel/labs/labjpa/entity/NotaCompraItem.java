package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class NotaCompraItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @ManyToOne
    private NotaCompra notaCompra;

    @NotNull
    @Positive
    private BigDecimal valorCompraProduto;

    public NotaCompraItem() {

    }

    public NotaCompraItem(NotaCompra notaCompra, Produto produto, BigDecimal valorCompraProduto, Integer quantidade) {
        this.notaCompra = notaCompra;
        this.produto = produto;
        this.valorCompraProduto = valorCompraProduto;
        this.quantidade = quantidade;
    }

    /**
     * Calculates the total value of a purchase item by multiplying the purchase
     * product value by its quantity.
     * 
     * @return the total value of the purchase item as a BigDecimal.
     */
    public BigDecimal getCalculoTotal() {
        return valorCompraProduto.multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public NotaCompra getNotaCompra() {
        return notaCompra;
    }

    public void setNotaCompra(NotaCompra notaCompra) {
        this.notaCompra = notaCompra;
    }

    public BigDecimal getValorCompraProduto() {
        return valorCompraProduto;
    }

    public void setValorCompraProduto(BigDecimal valorCompraProduto) {
        this.valorCompraProduto = valorCompraProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NotaCompraItem item = (NotaCompraItem) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
