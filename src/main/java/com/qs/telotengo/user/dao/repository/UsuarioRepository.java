package com.qs.telotengo.user.dao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qs.telotengo.user.dao.User;

@Repository
public interface UsuarioRepository extends CrudRepository<User, Long> {
	Iterable<User> findByNameContainingIgnoreCaseAndCountryAndStatusIsTrue(String nombre, String pais);
	Iterable<User> findByCountryAndStatusIsTrue(String pais);
	Optional<User> findByIdAndStatusIsTrue(Long id);
	boolean existsByIdAndStatusIsTrue(Long id);
	boolean existsByRut(String rut);
	
	
	
	
}
