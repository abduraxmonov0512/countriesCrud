package countryCRUD.countrCrud.service;

import countryCRUD.countrCrud.Repository.CountryRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.CountryWithRegionDto;
import countryCRUD.countrCrud.models.Region;
import countryCRUD.countrCrud.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    private RegionRepository regionRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }

    public SuccessResponse getAllRegionByCountryId(Long id) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Country with regions");
        successResponse.setCode(200);
        successResponse.setObject(getRegionsName(id));
        return successResponse;
    }

    private List<CountryWithRegionDto> getRegionsName(Long id) {
        Iterable<Region>  regions =  regionRepository.findAllByCountryId(id);
        List<CountryWithRegionDto> listRegion = new ArrayList<>();
        regions.forEach(region -> {
            CountryWithRegionDto dto = new CountryWithRegionDto();
            dto.setId(region.getId());
            dto.setRegion(region.getRegion());
            listRegion.add(dto);
        });
        return listRegion;
    }
}
