package ru.mephi.gpus_api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

import javax.xml.bind.ValidationException;

@Slf4j
@RestControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler({GpuStoreException.class})
    public ResponseEntity<String> handleGpuStoreExceptions(GpuStoreException e) {
        log.error("Throws exception: ", e);
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.valueOf(400)
        );
    }

    @ExceptionHandler({ValidationException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleJsonExceptions(Exception e) {
        log.error("Throws exception: ", e);
        return new ResponseEntity<>(
                ErrorMessage.VALIDATION_ERROR.toString(),
                HttpStatus.valueOf(400)
        );
    }

    @ExceptionHandler
    public ResponseEntity<String> handleOtherExceptions(Exception e) {
        log.error("Throws exception: ", e);
        return new ResponseEntity<>(
                ErrorMessage.TECHNICAL_ERROR.toString(),
                HttpStatus.valueOf(500)
        );
    }
}
