package es.auttonberri.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ExerciseException extends HttpStatusCodeException
{

    public ExerciseException(HttpStatus statusCode)
    {
        super(statusCode);
    }

    public ExerciseException(HttpStatus statusCode, String statusText)
    {
        super(statusCode, statusText);
    }

}
