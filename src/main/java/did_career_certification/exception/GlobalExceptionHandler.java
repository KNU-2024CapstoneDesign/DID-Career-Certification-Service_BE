package did_career_certification.exception;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }
}