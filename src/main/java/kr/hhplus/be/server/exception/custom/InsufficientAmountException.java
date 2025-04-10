package kr.hhplus.be.server.exception.custom;

import org.springframework.http.HttpStatus;

import kr.hhplus.be.server.exception.ErrorCode;

public class InsufficientAmountException extends CustomException {
    public InsufficientAmountException() {
        super(ErrorCode.INSUFFICIENT_AMOUNT, HttpStatus.BAD_REQUEST);
    }
}
