package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.service.Produtoservice;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/produtos")
public class ProdutoController {
    private final Produtoservice produtoService;

    public ProdutoController(Produtoservice produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Optional<Produto>> cadastrar(@Valid @RequestBody ProdutoRequest produto) {
        Optional<Produto> produtoCadastrado = produtoService.cadastrar(produto);

        if (produtoCadastrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } 
           
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(produtoCadastrado);
    }

    @GetMapping 
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Produto> atualizar(
        @PathVariable Integer id, 
        @Valid @RequestBody ProdutoRequest novosDados) {
        Optional<Produto> produtoAtualizado = produtoService.atualizar(id, novosDados);

        if (produtoAtualizado.isPresent()) {
            return ResponseEntity.ok(produtoAtualizado.get());
        } 
            return ResponseEntity.notFound().build();
        
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(
        @PathVariable Integer id) {

        boolean deletado = produtoService.excluir(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.notFound().build();
        
    }
    
}  
    

