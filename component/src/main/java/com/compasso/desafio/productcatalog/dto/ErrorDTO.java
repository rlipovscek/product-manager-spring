package com.compasso.desafio.productcatalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO {
	private String message;
}
