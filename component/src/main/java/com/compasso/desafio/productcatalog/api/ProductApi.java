package com.compasso.desafio.productcatalog.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.desafio.productcatalog.dto.ProductDTO;
import com.compasso.desafio.productcatalog.schema.ProductEntity;
import com.compasso.desafio.productcatalog.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("products")
public class ProductApi {

	@Autowired
	private ProductService service;
//	DELETE	/products/{id}	Deleção de um produto

	@ApiOperation("Chamada para criar um novo produto")
	@PostMapping
	public ResponseEntity<ProductEntity> saveProduct(@Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.savePorduct(dto));

	}

	@ApiOperation("Altera um produto com base no id")
	@PutMapping("{id}")
	public ResponseEntity<ProductEntity> updateProduct(@Valid @RequestBody ProductDTO dto,
			@PathVariable("id") String id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updatePorduct(id, dto));
	}

	@ApiOperation("Recupera um produto pelo id")
	@GetMapping("{id}")
	public ResponseEntity<ProductEntity> findOneProduct(@PathVariable("id") String id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findOneById(id));
	}

	@ApiOperation("Lista todos os produtos")
	@GetMapping()
	public ResponseEntity<List<ProductEntity>> findAllProduct() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@ApiOperation("Filtra um produto")
	@GetMapping("search")
	public ResponseEntity<Set<ProductEntity>> findProductByNameOrDescritionOrPriceInterval(
			@RequestParam(name = "q", required = false) String param,
			@RequestParam(name = "min_price", required = false) BigDecimal minPrice,
			@RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.findProductByNameOrDescritionOrPriceInterval(param, minPrice, maxPrice));
	}

	@ApiOperation("Deleta um produto")
	@DeleteMapping("{id}")
	public ResponseEntity<Set<ProductEntity>> findProductByNameOrDescritPriceInterval(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
