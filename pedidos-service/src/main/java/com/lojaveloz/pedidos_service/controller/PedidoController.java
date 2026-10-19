
package com.lojaveloz.pedidos_service.controller;

import com.lojaveloz.pedidos_service.model.Pedido;
import com.lojaveloz.pedidos_service.repository.PedidoRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Listar todos os pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // Consultar pedido pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cadastrar novo pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(
            @Valid @RequestBody Pedido pedido) {

        pedido.setId(null);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoSalvo);
    }

    // Atualizar pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody Pedido dadosAtualizados) {

        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setCliente(dadosAtualizados.getCliente());
                    pedido.setProduto(dadosAtualizados.getProduto());
                    pedido.setQuantidade(dadosAtualizados.getQuantidade());
                    pedido.setValor(dadosAtualizados.getValor());

                    Pedido atualizado = pedidoRepository.save(pedido);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir pedido pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
