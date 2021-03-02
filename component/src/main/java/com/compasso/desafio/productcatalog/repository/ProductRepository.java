package com.compasso.desafio.productcatalog.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.compasso.desafio.productcatalog.schema.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

	Optional<ProductEntity> findOneById(UUID id);
	
	Optional<List<ProductEntity>> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
	
	Optional<List<ProductEntity>> findByPriceBetween(BigDecimal start, BigDecimal end);
	
	Optional <ProductEntity> findByPrice(BigDecimal price);
}
