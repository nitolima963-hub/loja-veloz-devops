package com.lojaveloz.estoque_service.repository;

import com.lojaveloz.estoque_service.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
