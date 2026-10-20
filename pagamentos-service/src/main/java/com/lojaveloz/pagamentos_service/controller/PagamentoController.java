
package com.lojaveloz.pagamentos_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lojaveloz.pagamentos_service.model.Pagamento;
import com.lojaveloz.pagamentos_service.repository.PagamentoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoRepository repository;

    public PagamentoController(PagamentoRepository repository) {
        this.repository = repository;
    }

    // Listar todos os pagamentos
    @GetMapping
    public List<Pagamento> listar() {
        return repository.findAll();
    }

    // Consultar pagamento pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cadastrar pagamento
    @PostMapping
    public ResponseEntity<Pagamento> cadastrar(
            @Valid @RequestBody Pagamento pagamento) {

        pagamento.setId(null);

        Pagamento salvo = repository.save(pagamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    // Atualizar pagamento
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pagamento dados) {

        return repository.findById(id)
                .map(pagamento -> {
                    pagamento.setPedidoId(dados.getPedidoId());
                    pagamento.setValor(dados.getValor());
                    pagamento.setFormaPagamento(dados.getFormaPagamento());
                    pagamento.setStatus(dados.getStatus());

                    Pagamento atualizado = repository.save(pagamento);

                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
