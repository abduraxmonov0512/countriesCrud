package countryCRUD.countrCrud.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();
    private final String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError (String errorMessage){
        errors.add(errorMessage);
    }

    public List<String> getErrors(){
        return errors;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
