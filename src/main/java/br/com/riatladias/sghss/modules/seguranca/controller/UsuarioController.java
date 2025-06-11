package br.com.riatladias.sghss.modules.seguranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.seguranca.domain.Usuario;
import br.com.riatladias.sghss.modules.seguranca.dto.AuthUsuarioDTO;
import br.com.riatladias.sghss.modules.seguranca.useCase.AuthUsuarioUseCase;
import br.com.riatladias.sghss.modules.seguranca.useCase.CriarUsuarioUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @Autowired
    private AuthUsuarioUseCase authUsuarioUseCase;

    @PostMapping("/")
    // @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> criarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            var user = this.criarUsuarioUseCase.execute(usuario);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> authUsuario(@Valid @RequestBody AuthUsuarioDTO dto) {
        try {
            var result = this.authUsuarioUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
