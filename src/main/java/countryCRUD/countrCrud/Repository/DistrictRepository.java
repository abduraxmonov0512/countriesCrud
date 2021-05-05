package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {
}
