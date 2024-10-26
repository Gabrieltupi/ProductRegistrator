package br.com.school.product.api.security;

import br.com.school.product.domain.entity.UserEntity;
import br.com.school.product.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("authenticationService");
        Optional<UserEntity> usuarioEntityOptional = usuarioService.findByLogin(username);
        if (usuarioEntityOptional.isPresent()) {
            return usuarioEntityOptional.get();
        }

        throw new UsernameNotFoundException("Usuario inválido");
    }

}