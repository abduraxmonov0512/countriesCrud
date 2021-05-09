package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.District;
import org.hibernate.criterion.Distinct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {
    List<District> findAllByRegionId(Long id);
}
