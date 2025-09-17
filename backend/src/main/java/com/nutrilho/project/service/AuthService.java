package com.nutrilho.project.service;

import com.nutrilho.project.dto.AuthResponseDto;
import com.nutrilho.project.dto.LoginDto;
import com.nutrilho.project.dto.RegisterDto;
import com.nutrilho.project.entity.PerfilPaciente;
import com.nutrilho.project.entity.Role;
import com.nutrilho.project.entity.StatusVerificacao;
import com.nutrilho.project.entity.Usuario;
import com.nutrilho.project.repository.PerfilPacienteRepository;
import com.nutrilho.project.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilPacienteRepository perfilPacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDto register(RegisterDto dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso.");
        }

        var usuario = Usuario.builder()
                .nomeCompleto(dto.nomeCompleto())
                .email(dto.email())
                .senhaHash(passwordEncoder.encode(dto.senha()))
                .role(Role.PACIENTE)
                .statusVerificacao(StatusVerificacao.VERIFICADO)
                .build();
        var usuarioSalvo = usuarioRepository.save(usuario);

        var perfil = PerfilPaciente.builder()
                .usuario(usuarioSalvo)
                .dataNascimento(dto.dataNascimento())
                .alturaCm(dto.alturaCm())
                .build();
        perfilPacienteRepository.save(perfil);

        var token = jwtService.generateToken(usuarioSalvo);
        return new AuthResponseDto(token, usuarioSalvo.getNomeCompleto());
    }

    public AuthResponseDto login(LoginDto dto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );
        var usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado após autenticação."));
        
        var token = jwtService.generateToken(usuario);
        return new AuthResponseDto(token, usuario.getNomeCompleto());
    }
}