package br.com.riatladias.sghss.modules.seguranca.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.UserFoundException;
import br.com.riatladias.sghss.modules.seguranca.domain.Usuario;
import br.com.riatladias.sghss.modules.seguranca.dto.UsuarioResponseDTO;
import br.com.riatladias.sghss.modules.seguranca.repository.UsuarioRepository;

@Service
public class CriarUsuarioUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO execute(Usuario usuario) {
        this.usuarioRepository.findByUsername(usuario.getUsername())
                .ifPresent(user -> {
                    throw new UserFoundException("Usuário já existe");
                });

        // Encriptando a senha recebida na requisição
        var password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);

        var user = this.usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getPerfis());
    }
}
