package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadFileResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // The File
    private ByteArrayResource file;
}
