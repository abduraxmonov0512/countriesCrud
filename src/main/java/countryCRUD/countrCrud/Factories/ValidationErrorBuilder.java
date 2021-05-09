package countryCRUD.countrCrud.Factories;



import countryCRUD.countrCrud.response.ErrorResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorBuilder {

    public static ErrorResponse fromBindingErrors(BindingResult errors){
        ErrorResponse errorResponse = new ErrorResponse("Ошибка валидации. Количество ошибок " +
                errors.getErrorCount());

        List<FieldError> list = errors.getFieldErrors();
        for(FieldError objectError : list){
            errorResponse.addValidationError(objectError.getField() + ": " + objectError.getDefaultMessage());
        }
        return  errorResponse;
    }
}
