package countryCRUD.countrCrud.controllers;

import countryCRUD.countrCrud.Factories.ValidationErrorBuilder;
import countryCRUD.countrCrud.Repository.DistrictRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.DisctrictDTO;
import countryCRUD.countrCrud.models.District;
import countryCRUD.countrCrud.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/district")
public class DistrictController {
    private DistrictRepository districtRepository;
    private RegionRepository regionRepository;

    @Autowired
    public DistrictController(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDistrict(@RequestBody DisctrictDTO district){
        Region region = regionRepository.findById(district.getRegion_id()).get();
        District result = new District();
        result.setDistrict(district.getDisctrict());
        result.setRegion(region);
        return  ResponseEntity.ok().body(districtRepository.save(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getRegionById(@PathVariable Long id){
        Optional<District> result = districtRepository.findById(id);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<District>> getAllDistricts(){
        Iterable<District> districts = districtRepository.findAll();
        return ResponseEntity.ok(districts);
    }

}
