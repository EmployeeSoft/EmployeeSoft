package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private ServiceStatus serviceStatus;
}
