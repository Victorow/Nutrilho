package com.nutrilho.project.repository;

import com.nutrilho.project.entity.PerfilPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PerfilPacienteRepository extends JpaRepository<PerfilPaciente, UUID> {}