package countryCRUD.countrCrud.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegionDTO {
    @NotNull
    private long country_id;
    @NotNull
    @NotBlank
    private String region;
}
