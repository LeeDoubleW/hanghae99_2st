package kr.hhplus.be.server.exception.custom;

import org.springframework.http.HttpStatus;

import kr.hhplus.be.server.exception.ErrorCode;

public class IllegalArgumentException extends CustomException{
	public IllegalArgumentException() {
		super(ErrorCode.ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
	}
}
