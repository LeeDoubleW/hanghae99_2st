package kr.hhplus.be.server.domain.product;

import java.util.List;

public interface ProductRepository {
	Product findById(Long id);
	List<Product> findProducts();
	Product save(Product product);
	List<Product> findAllByIds(List<Long> ids);
}
