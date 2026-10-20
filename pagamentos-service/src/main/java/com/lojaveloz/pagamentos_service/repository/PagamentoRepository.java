package com.lojaveloz.pagamentos_service.repository;

import com.lojaveloz.pagamentos_service.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
