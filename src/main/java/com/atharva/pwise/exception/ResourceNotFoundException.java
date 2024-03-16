package com.atharva.pwise.exception;

import com.atharva.pwise.entity.response.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = false)
@Data
public class ResourceNotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public ResourceNotFoundException(String elementName, String fieldName, Object fieldValue) {
        super();
        this.errorResponse = new ErrorResponse(String.format("%s not found with %s: '%s'", elementName, fieldName, fieldValue), this.getClass().getName(), HttpStatus.NOT_FOUND);
    }

}
