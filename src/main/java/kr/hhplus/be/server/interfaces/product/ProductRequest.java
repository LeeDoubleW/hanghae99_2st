package kr.hhplus.be.server.interfaces.product;

import lombok.Getter;

@Getter
public class ProductRequest {
	private long productId;
	private String productNm;
	private long categoryId;
	private long price;
}
