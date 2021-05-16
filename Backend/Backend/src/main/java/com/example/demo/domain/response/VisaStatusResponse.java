package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // What type of file did the user currently hold
    private String type;

    // Status
    private boolean status;

    // Check if 100 days
    private boolean lessThan100Days;

    // Comment from HR for the most recent document
    private String comment;

    // titles and paths to those files
    private Map<String, String> personalDocuments;
}
