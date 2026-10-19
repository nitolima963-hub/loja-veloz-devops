package com.lojaveloz.estoque_service.controller;

import com.lojaveloz.estoque_service.model.Produto;
import com.lojaveloz.estoque_service.repository.ProdutoRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Consultar produto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cadastrar produto
    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(
            @Valid @RequestBody Produto produto) {

        produto.setId(null);

        Produto salvo = produtoRepository.save(produto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody Produto dados) {

        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(dados.getNome());
                    produto.setQuantidade(dados.getQuantidade());
                    produto.setPreco(dados.getPreco());

                    return ResponseEntity.ok(
                            produtoRepository.save(produto));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {

        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
