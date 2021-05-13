package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {
    // Http Section
    private ServiceStatus serviceStatus;
    private String url;
}
