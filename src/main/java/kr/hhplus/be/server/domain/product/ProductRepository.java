package kr.hhplus.be.server.domain.product;

import java.util.List;

import kr.hhplus.be.server.domain.user.UserPoint;

public interface ProductRepository {
	ProductInfo.V1 findById(Long id);
	List<ProductInfo.V1> findProducts();
}
