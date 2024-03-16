package com.atharva.pwise.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private String errorType;
    @JsonIgnore
    private HttpStatus status;

}
