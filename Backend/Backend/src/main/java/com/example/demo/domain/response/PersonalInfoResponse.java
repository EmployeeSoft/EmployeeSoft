package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoResponse {
    private ServiceStatus serviceStatus;
    // titles and paths to those files
    private Map<String, String> personalDocuments;
}
