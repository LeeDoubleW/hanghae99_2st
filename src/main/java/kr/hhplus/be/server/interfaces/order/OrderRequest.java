package kr.hhplus.be.server.interfaces.order;

import lombok.Getter;

@Getter
public class OrderRequest {
	private long userId;
	private long productId;
	private long couponId;
	private int quantity;
}
