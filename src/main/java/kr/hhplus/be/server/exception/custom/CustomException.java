package kr.hhplus.be.server.exception.custom;

import org.springframework.http.HttpStatus;

import kr.hhplus.be.server.exception.ErrorCode;

public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;
    private final HttpStatus status;

    public CustomException(ErrorCode errorCode, HttpStatus status) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
