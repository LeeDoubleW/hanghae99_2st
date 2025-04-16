package kr.hhplus.be.server.domain.product.repository;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.product.entity.Product;

public interface ProductRepository {
	Optional<Product> findById(Long id);
	List<Product> findProducts();
	Product save(Product product);
	List<Product> findAllByIds(List<Long> ids);
}
