package com.hb.PICOM_hibernate.controller;

import com.hb.PICOM_hibernate.buisness.Country;
import com.hb.PICOM_hibernate.initialisation.exception.InvalidCountryCodeException;
import com.hb.PICOM_hibernate.initialisation.exception.InvalidCountryException;
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
public class CountryRestController {

    private CountryService countryService;

    @GetMapping("countries")
    public List<Country> listCountriesGet(){
        return countryService.fetchCountries();
    }

    @GetMapping("countries/{countryCode}")
    public Country findCountryByIdGet(@PathVariable String countryCode){
        return countryService.findById(countryCode);
    }

    @PostMapping("countries/{countryCode}/{countryName}")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Country addCountryPost(@PathVariable String countryCode, @PathVariable String countryName) throws InvalidCountryException{

        //test if already present
        Country countryCodeTest = countryService.findById(countryCode);
        Country countryNameTest = countryService.findByName(countryName);
        Integer errorCount= 0;
        //if everything checks out => add to db
        if(countryCodeTest!= null){
            //throw exception and increment error count
            errorCount = errorCount + 1;

            throw new InvalidCountryException("This country code already exist in database");

        }
        if(countryNameTest!=null){
            //throw exception and increment error count
            errorCount = errorCount +1;
            throw new InvalidCountryException("This country name already exist in database.");

        }

        if(errorCount==0){
            return countryService.addCountry(new Country(countryCode,countryName));
        }

        return null;
    }


    @DeleteMapping("countries/{countryCode}")
    public void countryDelete(@PathVariable String countryCode) throws InvalidCountryCodeException{
        Country countryToDelete = countryService.findById(countryCode);

        if(countryToDelete!=null){
            countryService.deleteCountry(countryToDelete);
        }
        else{
            throw new InvalidCountryCodeException("404 : This country does not exist.");
        }

    }

    @PatchMapping("countries/{id}/{countryName}/{countryCode}")
    public Country countryPatch(@PathVariable String id, @PathVariable String countryName, @PathVariable String countryCode) throws InvalidCountryCodeException{
        Country countryToPatch = countryService.findById(id);


        if(countryToPatch != null){
             countryToPatch.setCountryName(countryName);
             countryToPatch.setId(countryCode);
             countryService.addCountry(countryToPatch);
        }
        else{

            throw new InvalidCountryCodeException("404 : This country does not exist.");

        }

        return countryToPatch;
    }

    //function to check if the @valid are met ( spring will do the check automatically if something is done in db)
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public  List<String> invalidDataWithDetailsHandler(ConstraintViolationException exception){
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    //Function to handle InvalidCountryException
    @ExceptionHandler(InvalidCountryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String invalidCountryExceptionHandler(InvalidCountryException e){
        return e.getMessage();
    }

    //Function to handle InvalidCountryCodeException
    @ExceptionHandler(InvalidCountryCodeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String invalidCountryCodeExceptionHandler(InvalidCountryCodeException e){
        return e.getMessage();
    }
}
