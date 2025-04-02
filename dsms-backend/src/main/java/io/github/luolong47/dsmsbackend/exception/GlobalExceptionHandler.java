package io.github.luolong47.dsmsbackend.exception;

import io.github.luolong47.dsmsbackend.model.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public R<Void> handleValidationException(WebExchangeBindException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            message.append(error.getDefaultMessage()).append("; ")
        );
        return R.fail(message.toString());
    }
} 