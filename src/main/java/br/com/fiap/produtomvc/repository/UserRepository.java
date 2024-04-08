package br.com.fiap.produtomvc.repository;

import br.com.fiap.produtomvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
