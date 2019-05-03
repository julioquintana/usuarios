package com.qs.telotengo.user.dao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.qs.telotengo.user.dao.User;

@Repository
public interface UsuarioRepository extends MongoRepository<User, String> {
	
	Iterable<User> findByNameContainingIgnoreCaseAndAddressesCountryAndStatusIsTrue(String nombre, String pais);
	Iterable<User> findByAddressesCountryAndStatusIsTrue(String pais);
	Optional<User> findByIdAndStatusIsTrue(String id);
	boolean existsByIdAndStatusIsTrue(String id);
	boolean existsByRut(String rut);

	//Addresses method
	Optional<User> findAddressesByIdAndStatusIsTrue(String id);
	Optional<User> findAddressesByAddressesIdAndAddressesStatusIsTrue(String id);
	@Query(delete = true)
	void deleteAddressesByIdAndAddressesId(String idUser, String idAddress);
	Long countAddressesByIdAndStatusIsTrue(String id);

	
	
	
	
}
