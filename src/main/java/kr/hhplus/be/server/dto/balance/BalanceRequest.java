package kr.hhplus.be.server.dto.balance;

import lombok.Getter;

@Getter
public class BalanceRequest {
	private long userId;
	private long amount;
}
