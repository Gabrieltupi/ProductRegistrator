package br.com.school.product.domain.repository;

import br.com.school.product.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLoginAndSenha(String login, String senha);

    Optional<UserEntity> findByLogin(String login);
}
