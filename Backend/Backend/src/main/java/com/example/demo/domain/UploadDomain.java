package com.example.demo.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadDomain {
    private MultipartFile file;
    private Integer userId;
    private String uploadTo;
    private String fileTitle;
}
