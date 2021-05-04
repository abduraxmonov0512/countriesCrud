package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Factories.ValidationErrorBuilder;
import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {
    private CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/countries")
    public ResponseEntity<Iterable<Country>> findAll(){
        return ResponseEntity.ok(countryRepository.findAll());
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id){
        Optional<Country> country = countryRepository.findById(id);
        return country.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/country/addCountry")
    public ResponseEntity<?> addCountry(@Valid @RequestBody Country country, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return ResponseEntity.ok().body(countryRepository.save(country));

    }

    @PutMapping("/county/{id}")
    public ResponseEntity<?> updateCountry(@Valid @RequestBody Country country, Errors errors){
        if(errors.hasErrors()){
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


}
