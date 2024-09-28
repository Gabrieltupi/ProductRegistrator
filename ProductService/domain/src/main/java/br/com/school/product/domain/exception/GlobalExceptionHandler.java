package br.com.school.product.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException domainException) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(domainException));
    }

    record ApiError(String domain, List<br.com.school.product.domain.validation.Error> errors) {
        static ApiError from(final DomainException domainException) {
            return new ApiError(domainException.getMessage(), domainException.getErrors());
        }
    }

}
