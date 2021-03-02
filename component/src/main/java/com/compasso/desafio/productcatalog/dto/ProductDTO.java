package com.compasso.desafio.productcatalog.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@NotBlank(message = "name not be blank")
	@NotEmpty(message = "name not be empty")
	@ApiModelProperty(value = "Nome do produto", dataType = "String", required = true, example = "produto teste")
	private String name;

	@NotBlank(message = "description not be blank")
	@NotEmpty(message = "description not be empty")
	@ApiModelProperty(value = "Descrição do produto", dataType = "String", required = true, example = "inserção para testar a api")
	private String description;

	@NotNull(message = "price not be null")
	@DecimalMin(value = "0.01", message = "price not be less than 0.01")
	@Positive(message = "price not be less than 0.01")
	@ApiModelProperty(value = "Valor do produto", dataType = "BigDecimal", required = true, example = "10.11")
	private BigDecimal price;

}
