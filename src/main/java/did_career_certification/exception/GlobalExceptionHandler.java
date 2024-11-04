package did_career_certification.exception;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<String> handlePermissionException(PermissionException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<String> handleResponseException(ResponseException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<String> handleBadRequestException(RequestException e) {
        String responseMessage = messageSource.getMessage(e.getMessage(), null,
            Locale.getDefault());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error ->
            errors.append(error.getField())
                .append(":\t")
                .append(error.getDefaultMessage())
                .append("\n")
        );
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}