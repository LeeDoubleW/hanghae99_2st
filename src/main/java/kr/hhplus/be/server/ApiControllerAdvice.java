package kr.hhplus.be.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kr.hhplus.be.server.exception.ErrorResult;
import kr.hhplus.be.server.exception.custom.CustomException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResult> handleCustomException(CustomException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getStatus()).body(
            new ErrorResult(ex.getCode(), ex.getMessage())
        );
    }
}
