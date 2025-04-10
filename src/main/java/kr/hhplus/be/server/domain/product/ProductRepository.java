package kr.hhplus.be.server.domain.product;

import java.util.List;

public interface ProductRepository {
	ProductInfo.V1 findById(Long id);
	List<ProductInfo.V1> findProducts();
	Product save(Product product);
	List<Product> findAllByIds(List<Long> ids);
}
