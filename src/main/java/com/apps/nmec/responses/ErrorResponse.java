package com.apps.nmec.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    @Builder.Default
    private List<AppError> errors = new ArrayList<>();

    public ErrorResponse(HttpStatus status, String message, AppError error) {
        super();
        this.status = status;
        this.message = message;
        this.errors.add(error);
    }

    public ErrorResponse(String message, List<AppError> error) {
        super();
        this.status = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.errors = new ArrayList<>();
        this.errors.addAll(error);
    }
}
