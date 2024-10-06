package br.com.school.product.domain.product;

import br.com.school.product.domain.product.entity.UsuarioEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final ObjectMapper objectMapper;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return repository.findByLoginAndSenha(login, senha);
    }

    public Optional<UsuarioEntity> findByLogin(String username) {
        return repository.findByLogin(username);
    }

    public void cadastrar(UsuarioEntity login) {
        UsuarioEntity usuario = objectMapper.convertValue(login, UsuarioEntity.class);
        repository.save(usuario);
    }

}
