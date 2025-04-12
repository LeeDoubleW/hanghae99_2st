package kr.hhplus.be.server.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepo;
	
	public ProductInfo.V1 getProduct(Long productId) {
		Product product = productRepo.findById(productId);
		return ProductInfo.V1.of(product.id(), product.productName(), product.price(), product.totalQuantity(), product.remainQuantity());
	}
	
	public List<ProductInfo.V1> getProductList() {
		List<ProductInfo.V1> products = productRepo.findProducts().stream().map(p->{
			return ProductInfo.V1.of(p.id(), p.productName(), p.price(), p.totalQuantity(), p.remainQuantity());
		}).toList();
		return products;
	}
	
	public void decreaseQuantity(Long productId, Long quantity) {
		Product productData = productRepo.findById(productId);
		ProductInfo.V1 info = ProductInfo.V1.of(productData.id(), productData.productName(), productData.price(), productData.totalQuantity(), productData.remainQuantity());
		Product product = Product.of(info.getId(), info.getProductName(), info.getPrice(), info.getTotalQuantity(), info.getRemainQuantity());
		product.decreaseQuantity(quantity);
		productRepo.save(product);
	}
}
