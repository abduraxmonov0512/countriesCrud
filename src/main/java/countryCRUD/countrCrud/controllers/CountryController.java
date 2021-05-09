package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Factories.ValidationErrorBuilder;
import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.dto.CountryWithRegionDto;
import countryCRUD.countrCrud.response.ErrorResponse;
import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.response.SuccessResponse;
import countryCRUD.countrCrud.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {
    private CountryRepository countryRepository;
    private CountryService countryService;

    @Autowired
    public CountryController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<Iterable<Country>> findAll(){
        return ResponseEntity.ok(countryRepository.findAll());
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<SuccessResponse> getCountryById(@PathVariable Long id){

        return ResponseEntity.ok().body(countryService.getAllRegionByCountryId(id));
    }

    @PostMapping("/country/addCountry")
    public ResponseEntity<?> addCountry(@Valid @RequestBody Country country, BindingResult errors){

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return ResponseEntity.ok().body(countryRepository.save(country));

    }

    @PutMapping("/country")
    public ResponseEntity<?> updateCountry(@Valid @RequestBody Country country, BindingResult errors){
        if(errors.hasErrors()){
            System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        Optional<Country> country1 = countryRepository.findById(country.getId());
        if(country1.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        country1.get().setCountry(country.getCountry());
        countryRepository.save(country1.get());
        return ResponseEntity.ok().body(country1.get());
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id){
        countryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception e){
        System.out.println("Vot tut oshibka1" + e.getLocalizedMessage());
        System.out.println("Vot tut oshibka2" + e.getCause());
        System.out.println("Vot tut oshibka3" + Arrays.toString(e.getSuppressed()));

        return new ErrorResponse(e.getMessage());
    }
}
