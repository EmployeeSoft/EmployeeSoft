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
public class VisaStatusResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // What type of file did the user currently hold
    private String type;

    // Tell the user the next step
    private String comment;
}
