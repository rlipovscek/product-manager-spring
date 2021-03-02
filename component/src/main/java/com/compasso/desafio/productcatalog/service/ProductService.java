package com.compasso.desafio.productcatalog.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.desafio.productcatalog.dto.ProductDTO;
import com.compasso.desafio.productcatalog.exceptions.EntityException;
import com.compasso.desafio.productcatalog.repository.ProductRepository;
import com.compasso.desafio.productcatalog.schema.ProductEntity;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	private UUID convertId(String id) {
		UUID uuid = null;

		try {
			uuid = UUID.fromString(id);
			return uuid;

		} catch (Exception e) {
			throw new EntityException(String.format("Invalid id: %s", id));
		}
	}

	private ProductEntity getEntity(ProductDTO dto) {

		return ProductEntity.builder().description(dto.getDescription()).name(dto.getName()).price(dto.getPrice())
				.build();
	}

	public ProductEntity savePorduct(ProductDTO dto) {
		ProductEntity model = getEntity(dto);
		ProductEntity entity = repository.save(model);
		return entity;
	}

	public ProductEntity updatePorduct(String id, ProductDTO dto) {
		ProductEntity model = getEntity(dto);
		ProductEntity productFinded = repository.findOneById(convertId(id))
				.orElseThrow(() -> new EntityException(String.format("Not found product with id: %s", id)));

		model.setId(productFinded.getId());

		ProductEntity entity = this.repository.save(model);

		return entity;
	}

	public ProductEntity findOneById(String id) {
		ProductEntity entity = repository.findOneById(convertId(id))
				.orElseThrow(() -> new EntityException(String.format("Not found product with id: %s", id)));

		return entity;

	}
	
	public List<ProductEntity> findAll() {
		List<ProductEntity> list = repository.findAll();
		return list;

	}
	
	
	public List<ProductEntity> findAllByNameOrDescription(String nameDescription){
		List<ProductEntity> list = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(nameDescription, nameDescription).get();
		return list;
	}
	
	public List<ProductEntity> findAllByPriceInterval(BigDecimal minPrice, BigDecimal maxPrice) {
		
		if(minPrice == null) {
			minPrice = BigDecimal.ZERO;
		}
		
		if(maxPrice == null){
			maxPrice = BigDecimal.valueOf(10000000L);
		}
		
		
		List<ProductEntity> list = repository.findByPriceBetween(minPrice, maxPrice).get();
		return list;

	}
	
	
	public Set<ProductEntity> findProductByNameOrDescritionOrPriceInterval(String nameDescription, BigDecimal minPrice, BigDecimal maxPrice) {
		
		Set<ProductEntity> list = new HashSet<>();
		
		if(nameDescription != null) {
			list.addAll(findAllByNameOrDescription(nameDescription));
		}
		
		if(minPrice != null || maxPrice != null) {
			list.addAll(findAllByPriceInterval(minPrice, maxPrice));
		}
		
		
		return list;

	}
	
	
	public void delete(String id) {
		ProductEntity entity = findOneById(id);
		this.repository.delete(entity);
		
	}
	

}
