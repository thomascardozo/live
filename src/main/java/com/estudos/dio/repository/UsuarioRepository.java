package com.estudos.dio.repository;

import com.estudos.dio.model.JornadaTrabalho;
import com.estudos.dio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
