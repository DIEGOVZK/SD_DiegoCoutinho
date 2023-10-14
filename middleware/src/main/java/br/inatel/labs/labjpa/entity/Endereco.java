package br.inatel.labs.labjpa.entity;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Endereco {

    @Id
    private String codigo;

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    private String complemento;

    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    @Size(max = 2)
    private String uf;

    @PrePersist
    private void preencherCodigo() {
        this.codigo = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Endereco endereco = (Endereco) o;
        return codigo.equals(endereco.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
