package com.hb.PICOM_hibernate.controller;

import com.hb.PICOM_hibernate.buisness.City;
import com.hb.PICOM_hibernate.buisness.Country;
import com.hb.PICOM_hibernate.dto.CityDto;
import com.hb.PICOM_hibernate.initialisation.exception.InvalidCityException;
import com.hb.PICOM_hibernate.initialisation.exception.InvalidCountryException;
import com.hb.PICOM_hibernate.service.CityService;
import com.hb.PICOM_hibernate.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class CityRestController {

    private CityService cityService;
    private CountryService countryService;

    @GetMapping("cities")
    public List<City> listCitiesGet(){
        return cityService.fetchCities();
    }

    @GetMapping("cities/{id}")
    public City findCityBByIdGet(@PathVariable Integer id){
        return cityService.findById(id);
    }

    @GetMapping("cities/{postalCode}/{countryCode}")
    public City findCityByPostalCodeGet(@PathVariable String postalCode, @PathVariable String countryCode){
        return cityService.findByPostalCodeAndCountry(postalCode,countryCode);
    }

    @GetMapping("countries/{countryCode}/cities")
    public List<City> findCitiesByCountryGet(@PathVariable String countryCode){
        return cityService.findByCountryCode(countryCode);
    }

    @PostMapping("cities")
    @ResponseStatus(HttpStatus.CREATED)
    public City addCity(@RequestBody CityDto cityDto) throws InvalidCityException {
        //turn cityDto into a city object
        City newCity = cityService.convertCityDtoToCity(cityDto);

        //check if city postal code already exist
        City cityTest = cityService.findByPostalCodeAndCountry(newCity.getPostalCode(),newCity.getIdCountry().getId());

        if(cityTest == null){
            cityService.addCity(newCity);
            return newCity;
        }
        else{

            throw new InvalidCityException("This City already exist.");
        }

    }

    @DeleteMapping("cities/{id}")
    public void cityDelete(@PathVariable Integer id) throws InvalidCityException {
        City cityToDelete = cityService.findById(id);

        //check if city exist in db

        if(cityToDelete != null){
            cityService.deleteCity(cityToDelete);
        }
        else{
            throw new InvalidCityException("404 : This city does not exist");
        }
    }

    @PatchMapping("cities/{id}/{cityName}/{postalCode}/{countryCode}")
    public City cityUpdate(@PathVariable Integer id,@PathVariable String cityName, @PathVariable String postalCode, @PathVariable String countryCode) throws InvalidCityException, InvalidCountryException {
        //check if city exists
        City cityToUpdate = cityService.findById(id);

        if(cityToUpdate != null){
            cityToUpdate.setPostalCode(postalCode);
            cityToUpdate.setCityName(cityName);

            //check if country exist
            Country country = countryService.findById(countryCode);
            if(country !=null){
                cityToUpdate.setIdCountry(country);
                //change data
                cityService.addCity(cityToUpdate);

                //check if postal code already exist
                City postalCodeTest =  cityService.findByPostalCodeAndCountry(postalCode,countryCode);
                if(postalCodeTest != null){
                    //change postal code data
                    cityToUpdate.setPostalCode(postalCode);
                    return cityToUpdate;
                }
                else{
                    throw new InvalidCityException("This postal code already exist for this country");
                }

            }
            else{
                throw new InvalidCountryException("404 : This Country does not exist.");
            }

        }
        else{
            throw new InvalidCityException("404 : This city does not exist.");
        }

    }


    //function to check if the @valid are met ( spring will do the check automatically if something is done in db)
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public  List<String> invalidDataWithDetailsHandler(ConstraintViolationException exception){
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    //Function to handle InvalidCityException
    @ExceptionHandler(InvalidCityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String invalidCityExceptionHandler(InvalidCityException e){
        return e.getMessage();
    }

    //Function to handle InvalidCountryException
    @ExceptionHandler(InvalidCountryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String invalidCountryExceptionHandler(InvalidCountryException e){
        return e.getMessage();
    }





}
