package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import countryCRUD.countrCrud.models.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAllByCountryId(Long id);
}
