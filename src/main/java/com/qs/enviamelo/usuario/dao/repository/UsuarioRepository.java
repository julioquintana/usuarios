package com.qs.enviamelo.usuario.dao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qs.enviamelo.usuario.dao.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {
	Iterable<Usuario> findByNombreContainingIgnoreCaseAndPaisAndEstadoIsTrue(String nombre, String pais);
	Optional<Usuario> findByIdAndEstadoIsTrue(Long id);
}
