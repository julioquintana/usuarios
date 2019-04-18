package com.qs.telotengo.user.dao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qs.telotengo.user.dao.User;

@Repository
public interface UsuarioRepository extends MongoRepository<User, Long> {
	Iterable<User> findByNameContainingIgnoreCaseAndCountryAndStatusIsTrue(String nombre, String pais);
	Iterable<User> findByCountryAndStatusIsTrue(String pais);
	Optional<User> findByIdAndStatusIsTrue(String id);
	boolean existsByIdAndStatusIsTrue(String id);
	boolean existsByRut(String rut);
	
	
	
	
}
