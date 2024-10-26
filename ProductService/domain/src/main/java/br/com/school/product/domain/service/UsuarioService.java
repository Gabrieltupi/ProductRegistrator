package br.com.school.product.domain.service;

import br.com.school.product.domain.entity.UserEntity;
import br.com.school.product.domain.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final ObjectMapper objectMapper;

    public Optional<UserEntity> findByLoginAndSenha(String login, String senha) {
        return repository.findByLoginAndSenha(login, senha);
    }

    public Optional<UserEntity> findByLogin(String username) {
        return repository.findByLogin(username);
    }

    public void cadastrar(UserEntity login) {
        UserEntity usuario = objectMapper.convertValue(login, UserEntity.class);
        repository.save(usuario);
    }

}
