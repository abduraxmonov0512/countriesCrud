package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Long> {
}