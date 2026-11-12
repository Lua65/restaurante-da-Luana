package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {

    @NotBlank (message = "Informe o nome do produto")
    private String nome;

    @NotNull (message = "Informe o preço")
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @NotNull (message = "Informe o restaurante")
    private Integer restauranteId;

    public ProdutoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }


    
}
