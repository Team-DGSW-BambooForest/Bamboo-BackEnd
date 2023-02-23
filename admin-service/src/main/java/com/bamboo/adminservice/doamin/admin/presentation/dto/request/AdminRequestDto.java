package com.bamboo.adminservice.doamin.admin.presentation.dto.request;

import com.bamboo.adminservice.doamin.admin.domain.type.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminRequestDto {
    private Status status;
}
