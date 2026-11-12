package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service 
public class Produtoservice {
    private final ProdutoRepository produtoRepository;

    private final RestauranteRepository restauranteRepository;

    public Produtoservice(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrar(ProdutoRequest dadosproduto) {
        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(dadosproduto.getRestauranteId());

        if (restauranteEncontrado.isEmpty()) {
           return Optional.empty();
        }
        
        Produto produto = new Produto();
        produto.setNome(dadosproduto.getNome());
        produto.setPreco(dadosproduto.getPreco());
        produto.setRestaurante(restauranteEncontrado.get());
        return Optional.of(produtoRepository.save(produto));
    }

    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Integer id, ProdutoRequest dadosproduto) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if (!produtoEncontrado.isPresent()) {
            return Optional.empty();
        }

        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(dadosproduto.getRestauranteId());

        if (restauranteEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoEncontrado.get();

        produto.setNome(dadosproduto.getNome());
        produto.setPreco(dadosproduto.getPreco());
        produto.setRestaurante(restauranteEncontrado.get());
        return Optional.of(produtoRepository.save(produto));
    }

    public boolean excluir(Integer id){
        produtoRepository.deleteById(id);
        return true;
    }


}
