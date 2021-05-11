package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceStatus {
    private String statusCode;
    private boolean success;
    private String errorMessage;
}
