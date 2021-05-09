package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Factories.ValidationErrorBuilder;
import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.RegionDTO;
import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.Region;
import countryCRUD.countrCrud.response.ErrorResponse;
import countryCRUD.countrCrud.response.SuccessResponse;
import countryCRUD.countrCrud.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;
    private RegionService regionService;

    @Autowired
    public RegionController(RegionRepository regionRepository, CountryRepository countryRepository, RegionService regionService) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
        this.regionService = regionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRegion(@Valid @RequestBody RegionDTO region, BindingResult error){
        if(error.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(error));
        }
        Optional<Country> optionalCountry = countryRepository.findById(region.getCountry_id());
        if(optionalCountry.isEmpty()){
            ResponseEntity.notFound().build();
        }
        Region newRegion = new Region();
        newRegion.setCountry(optionalCountry.get());
        newRegion.setRegion(region.getRegion());

        return ResponseEntity.ok().body(regionRepository.save(newRegion));
    }


    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getRegionById(@PathVariable Long id){


        return ResponseEntity.ok().body(regionService.getAllDistrictsRegion(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Region>> getAllRegions(){
//        Iterable<Region> regions =  regionRepository.findAll();
//        if(!regions.iterator().hasNext()){
//            return ResponseEntity.notFound().build();
//        }

        return  ResponseEntity.ok().body(regionRepository.findAll());
    }

    @PutMapping()
    public ResponseEntity<Region> updateRegion(@RequestBody Region region) {
        Optional<Region> result = regionRepository.findById(region.getId());

        if(result.isEmpty()){
            ResponseEntity.notFound().build();
        }
        result.get().setRegion(region.getRegion());


       return ResponseEntity.ok().body(regionRepository.save(result.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        regionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception e){
        System.out.println("Vot tut oshibka1" + e.getLocalizedMessage());
        System.out.println("Vot tut oshibka2"  + e.getCause());

        return new ErrorResponse(e.getMessage());
    }
}
