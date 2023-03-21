package com.bamboo.adminservice.doamin.admin.presentation.dto.request;

import com.bamboo.adminservice.doamin.admin.domain.type.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminRequestDto {
    @Schema(description = "ALLOWED : 승인, NOT_ALLOWED : 거절")
    private Status status;
}
