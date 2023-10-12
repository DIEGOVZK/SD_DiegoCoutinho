package br.inatel.labs.labjpa.entity;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String descricao;

    @ManyToMany(mappedBy = "listaProduto")
    private List<Fornecedor> listaFornecedor;

    public Produto() {

    }

    public Produto(@NotNull @Size(min = 2, max = 100) String descricao) {
        super();
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
