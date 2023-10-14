package br.inatel.labs.labjpa.entity;

import br.inatel.labs.labjpa.entity.Endereco;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Endereco endereco;

    @NotNull
    @Size(min = 2, max = 200)
    private String razaoSocial;

    @ManyToMany
    private List<Produto> listaProduto;

    public Fornecedor() {
    
    }

    public Fornecedor(@NotNull @Size(min = 2, max = 100) String razaoSocial) {
        super();
        this.razaoSocial = razaoSocial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) o;
        return id.equals(fornecedor.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
