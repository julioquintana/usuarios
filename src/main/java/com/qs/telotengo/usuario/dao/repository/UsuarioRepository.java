package com.qs.telotengo.usuario.dao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qs.telotengo.usuario.dao.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Iterable<Usuario> findByNombreContainingIgnoreCaseAndPaisAndEstadoIsTrue(String nombre, String pais);
	Optional<Usuario> findByIdAndEstadoIsTrue(Long id);
}
