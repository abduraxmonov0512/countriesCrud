package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.District;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepository extends CrudRepository<District, Long> {
}
