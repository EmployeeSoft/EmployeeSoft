package com.example.demo.domain.response;

import com.example.demo.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OnBoardResponse {
    private ServiceStatus serviceStatus;
}
