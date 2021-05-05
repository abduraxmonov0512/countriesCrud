package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.RegionDTO;
import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
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


}
