package br.com.lab.impacta.account.handlers;

import br.com.lab.impacta.account.handlers.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handlers.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.handlers.exception.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AccountDontExistsException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountDontExistsException accountDontExistsException) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                accountDontExistsException.getMessage(),
                accountDontExistsException.getDescription());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> withoutBalanceException(AccountWithoutBalanceException ex, WebRequest request) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "Não foi possível processar sua requisição.");

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
