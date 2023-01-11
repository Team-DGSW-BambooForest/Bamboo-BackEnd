package com.bamboo.userservice.domain.auth.presentation.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class DOpenApiDto implements Serializable {

    @JsonProperty("data")
    private DodamInfoDto dodamInfoData;

    public DOpenApiDto(DodamInfoDto dodamInfoData) {
        this.dodamInfoData = new DodamInfoDto(dodamInfoData);
    }
}