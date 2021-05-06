package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.RegionDTO;
import countryCRUD.countrCrud.errors.ValidationError;
import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;

    @Autowired
    public RegionController(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Region> addRegion(@RequestBody RegionDTO region){
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
    public ResponseEntity<Region> getRegionById(@PathVariable Long id){
        Optional<Region> region = regionRepository.findById(id);
        if(region.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(region.get());
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
    public ValidationError handleException(Exception e){
        System.out.println("Vot tut oshibka");
        return new ValidationError(e.getMessage());
    }
}
