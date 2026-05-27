package com.akhdani.perdin_app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CityRequest {
    @NotBlank
    private String cityName;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotBlank
    private String province;
    @NotBlank
    private String island;
    private Boolean luarNegeri;
}
