package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class NotaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Past
    private LocalDate dataEmissao;

    @ManyToOne
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "notaCompra")
    private List<NotaCompraItem> listaNotaCompraItem;

    public NotaCompra() {

    }

    public NotaCompra(@NotNull @Past LocalDate dataEmissao, Fornecedor fornecedor) {
        super();
        this.fornecedor = fornecedor;
        this.dataEmissao = dataEmissao;
    }

    /**
     * Calculates the total value of the purchase note.
     * @return the total value of the purchase note as a BigDecimal.
     */
    public BigDecimal getCalculoTotalNota() {

        BigDecimal total = this.listaNotaCompraItem.stream()
                .map(i -> i.getCalculoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }

    /**
     * Returns the list of NotaCompraItem objects associated with this NotaCompra.
     * @return the list of NotaCompraItem objects associated with this NotaCompra.
     */
    public List<NotaCompraItem> listaNotaCompraItem() {
        return listaNotaCompraItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaCompra nc = (NotaCompra) o;
        return id.equals(nc.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
