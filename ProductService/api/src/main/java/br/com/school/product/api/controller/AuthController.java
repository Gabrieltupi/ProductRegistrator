package br.com.school.product.api.controller;

import br.com.school.product.api.security.TokenService;
import br.com.school.product.domain.dto.LoginDto;
import br.com.school.product.domain.entity.UserEntity;
import br.com.school.product.domain.exception.NotFoundException;
import br.com.school.product.domain.exception.NotificationException;
import br.com.school.product.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDto loginDTO) throws NotFoundException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        UserEntity usuarioValidado = (UserEntity) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }


    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody @Valid LoginDto usuario) throws NotificationException {
        String cript = passwordEncoder.encode(usuario.getSenha());

        UserEntity usuarioEntity = new UserEntity();
        usuarioEntity.setSenha(cript);
        usuarioEntity.setLogin(usuario.getLogin());
        usuarioService.cadastrar(usuarioEntity);
        return ResponseEntity.ok("Usuario Cadastrado com Sucesso");
    }
}
