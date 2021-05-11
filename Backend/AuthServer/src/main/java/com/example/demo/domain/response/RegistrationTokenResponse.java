package com.example.demo.domain.response;

import com.example.demo.domain.RegistrationTokenDomain;
import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTokenResponse {
    private ServiceStatus serviceStatus;
    private RegistrationTokenDomain registrationTokenDomain;
}
