package countryCRUD.countrCrud.Repository;

import countryCRUD.countrCrud.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface CountryRepository  extends CrudRepository<Country, Long> {
    void deleteById (Long id);
}
