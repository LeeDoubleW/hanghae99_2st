package kr.hhplus.be.server.infrastructure.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{

	private final ProductJpaRepository productJpaRepository; 
	
	@Override
	public Optional<Product> findById(Long id) {

		return productJpaRepository.findById(id);
	}

	@Override
	public List<Product> findProducts() {

		return productJpaRepository.findAll();
	}

	@Override
	public Product save(Product product) {

		return productJpaRepository.save(product);
	}

	@Override
	public List<Product> findAllByIds(List<Long> ids) {

		return productJpaRepository.findAllById(ids);
	}

}
