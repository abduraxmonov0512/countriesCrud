package countryCRUD.countrCrud.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DistrictDTO {
    @NotNull
    private long region_id;

    @NotNull
    @NotBlank
    private String district;
}
