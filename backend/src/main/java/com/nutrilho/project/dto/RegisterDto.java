package com.nutrilho.project.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RegisterDto(
    @NotBlank String nomeCompleto,
    @Email @NotBlank String email,
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres") String senha,
    @NotNull LocalDate dataNascimento,
    @NotNull @Positive Integer alturaCm
) {}