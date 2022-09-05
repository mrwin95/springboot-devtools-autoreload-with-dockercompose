package com.example.gateway.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String status;
    private Date timestamp;
    private String error;
    private String exception;
    private String message;
    private String path;
}
