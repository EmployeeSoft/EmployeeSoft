package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoResponse {
    private ServiceStatus serviceStatus;
}
