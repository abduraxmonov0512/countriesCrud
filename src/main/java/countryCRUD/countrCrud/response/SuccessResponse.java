package countryCRUD.countrCrud.response;

import lombok.Data;

@Data
public class SuccessResponse {
    private int code;
    private String message;

    private Object object;
}
