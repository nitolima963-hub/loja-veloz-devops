
package com.lojaveloz.pedidos_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste")
    public String testar() {
        return "API de Pedidos da Loja Veloz funcionando!";
    }
}
