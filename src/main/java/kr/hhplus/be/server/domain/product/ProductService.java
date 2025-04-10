package kr.hhplus.be.server.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepo;
	
	public ProductInfo.V1 getProduct(Long productId) {
		return productRepo.findById(productId);
	}
	
	public List<ProductInfo.V1> getProductList() {
		return productRepo.findProducts();
	}
	
	public void decreaseQuantity(Long productId, Long quantity) {
		ProductInfo.V1 info = productRepo.findById(productId);
		Product product = Product.of(info.getId(), info.getProductName(), info.getPrice(), info.getTotalQuantity(), info.getRemainQuantity());
		if(info == null) {
			throw new IllegalArgumentException();
		}
		
		product.decreaseQuantity(quantity);
		productRepo.save(product);
	}
}
