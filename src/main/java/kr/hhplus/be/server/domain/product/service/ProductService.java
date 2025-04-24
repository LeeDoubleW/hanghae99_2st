package kr.hhplus.be.server.domain.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.product.dto.ProductCommand;
import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepo;
	
	public Product save(Product product) {
		return productRepo.save(product);
	}
	
	public ProductInfo.V1 getProduct(Long productId) {
		Optional<Product> product = productRepo.findById(productId);
		return ProductInfo.V1.of(product.get().id(), product.get().productName(), product.get().price(), product.get().totalQuantity(), product.get().remainQuantity());
	}
	
	public List<ProductInfo.V1> getProductList() {
		List<ProductInfo.V1> products = productRepo.findProducts().stream().map(p->{
			return ProductInfo.V1.of(p.id(), p.productName(), p.price(), p.totalQuantity(), p.remainQuantity());
		}).toList();
		return products;
	}
	
	public List<ProductInfo.V1> getProductListById(ProductCommand.Products products) {
		return products.getProducts().stream().map(p -> {
			Optional<Product> product = productRepo.findById(p.getId());
			return ProductInfo.V1.of(product.get().id(), product.get().productName(), product.get().price(), product.get().totalQuantity(), product.get().remainQuantity());
		}).toList();
	}
	
	public void decreaseQuantity(Long productId, Long quantity) {
		Optional<Product> productData = productRepo.findById(productId);
		ProductInfo.V1 info = ProductInfo.V1.of(productData.get().id(), productData.get().productName(), productData.get().price(), productData.get().totalQuantity(), productData.get().remainQuantity());
		Product product = Product.of(info.getId(), info.getProductName(), info.getPrice(), info.getTotalQuantity(), info.getRemainQuantity());
		product.decreaseQuantity(quantity);
		productRepo.save(product);
	}
}
