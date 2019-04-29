package com.qs.telotengo.product.dao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.qs.telotengo.product.dao.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Iterable<Product> findByNameContainingIgnoreCaseOrTagContainingIgnoreCaseAndStatusIsTrueAndGalleryStatusIsTrue(String name, String tag);
	Iterable<Product> findByIdstoreAndStatusIsTrue(String idStore);
	Optional<Product> findByIdAndStatusIsTrue(String id);

	//Gallery method
	Optional<Product> findGalleryByIdAndStatusIsTrue(String id);
	Optional<Product> findGalleryByGalleryIdAndGalleryStatusIsTrue(String id);
	@Query(delete = true)
	void deleteGalleryByIdAndGalleryId(String idUser, String idPhoto);

	
	
	
	
}
