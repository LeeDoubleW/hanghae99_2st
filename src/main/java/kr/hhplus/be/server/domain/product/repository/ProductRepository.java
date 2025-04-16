package kr.hhplus.be.server.domain.product.repository;

import java.util.List;

import kr.hhplus.be.server.domain.product.entity.Product;

public interface ProductRepository {
	Product findById(Long id);
	List<Product> findProducts();
	Product save(Product product);
	List<Product> findAllByIds(List<Long> ids);
}
