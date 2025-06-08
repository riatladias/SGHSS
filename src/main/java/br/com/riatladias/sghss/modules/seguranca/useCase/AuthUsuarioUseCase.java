package br.com.riatladias.sghss.modules.seguranca.useCase;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.riatladias.sghss.modules.seguranca.dto.AuthUsuarioDTO;
import br.com.riatladias.sghss.modules.seguranca.dto.AuthUsuarioResponseDTO;
import br.com.riatladias.sghss.modules.seguranca.repository.UsuarioRepository;

@Service
public class AuthUsuarioUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUsuarioResponseDTO execute(AuthUsuarioDTO dto) throws AuthenticationException {
        // Validação do usuário
        var user = this.usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorreto.");
                });

        // Validação da senha
        var passwordMatches = this.passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        // Criação do token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expireIn = Instant.now().plus(Duration.ofHours(2));

        var rolesList = user.getPerfis().stream().map(perfis -> {
            return perfis.toString();
        }).toList();

        var token = JWT.create()
                .withIssuer("SGHSS") // Emissor
                .withExpiresAt(expireIn)
                .withSubject(user.getProfissionalId().toString()) // ID do profissional de saúde
                .withClaim("roles", rolesList)
                .sign(algorithm);

        var authUsuarioResponseDTO = AuthUsuarioResponseDTO.builder()
                .access_token(token)
                .expire_in(expireIn.toEpochMilli())
                .build();

        return authUsuarioResponseDTO;

    }
}
