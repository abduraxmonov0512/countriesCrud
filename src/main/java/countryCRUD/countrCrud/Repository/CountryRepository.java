package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository  extends CrudRepository<Country, Long> {
}
