package com.apps.nmec.exceptionhandlers;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -7806029002430564887L;

	private Integer errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;

}
