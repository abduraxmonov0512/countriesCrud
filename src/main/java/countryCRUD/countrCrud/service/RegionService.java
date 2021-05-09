package countryCRUD.countrCrud.service;

import countryCRUD.countrCrud.Repository.DistrictRepository;
import countryCRUD.countrCrud.Repository.RegionRepository;
import countryCRUD.countrCrud.dto.RegionWithDistrictDto;
import countryCRUD.countrCrud.models.District;
import countryCRUD.countrCrud.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;
    private DistrictRepository districtRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository, DistrictRepository districtRepository) {
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
    }

    public SuccessResponse getAllDistrictsRegion(Long id){
        SuccessResponse response = new SuccessResponse();
        response.setCode(200);
        response.setMessage("Districts of Region");
        response.setObject(getDistricts(districtRepository.findAllByRegionId(id)));
        System.out.println(districtRepository.findAllByRegionId(id));
        return response;
    }

    private List<RegionWithDistrictDto> getDistricts(Iterable<District> districts){
        List<RegionWithDistrictDto> list = new ArrayList<>();
        districts.forEach(district -> {
            RegionWithDistrictDto dto = new RegionWithDistrictDto();
            dto.setId(district.getId());
            dto.setDistrict(district.getDistrict());
            list.add(dto);
        });
        return list;
    }
}
