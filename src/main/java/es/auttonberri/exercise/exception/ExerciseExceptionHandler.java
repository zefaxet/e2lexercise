package es.auttonberri.exercise.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExerciseExceptionHandler {

    @ExceptionHandler(value = ExerciseException.class)
    public ResponseEntity<?> handleException(ExerciseException e) {
        return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
    }

}
