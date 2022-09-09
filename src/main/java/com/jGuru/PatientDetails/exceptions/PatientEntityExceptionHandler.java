package com.jGuru.PatientDetails.exceptions;

import com.jGuru.PatientDetails.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class PatientEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PatientDataNotFound.class)
    public ResponseEntity<ErrorMessage> patientNotFoundException(PatientDataNotFound patientDataNotFound,
                                                                 WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, patientDataNotFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
