package br.com.fiap.produtomvc.repository;

import br.com.fiap.produtomvc.models.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {
}
