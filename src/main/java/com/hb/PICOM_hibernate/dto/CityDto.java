package com.hb.PICOM_hibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CityDto {
/*
* This class is used in rest controller to simplify the list of stas asked in the body of the http request
* this is not a component it's a class only used in memory
* */

    @NotNull(message="A country code is required")
    @Pattern(regexp = "^[a-z]{3}$",message = "The countryCode should be composed of only 3 characters")
    @NotBlank(message="A country code is reuqired")
    private String countryCode;



    @NotNull(message="A city name is required")
    @NotBlank(message="A city name is required")
    @Size(min = 2, max = 255, message="A city name should be between 2a nd 255 characters")
    private String cityName;

    @NotNull(message="A postal code is required")
    @NotBlank(message="A postal code is required")
    @Size(min=5, max=9 , message="A zip code should be between 5 and 9 caracters")
    private String postalCode;






}
