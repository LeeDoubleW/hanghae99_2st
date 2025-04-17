package kr.hhplus.be.server.domain.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import kr.hhplus.be.server.domain.product.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService getProductService;

    private Optional<Product> product;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        product = Optional.of(new Product(1L, "아디다스 운동화", 10000L, 10L, 10L));
        
        products.add(new Product(1L, "나이키 운동화", 20000L, 10L, 10L));
        products.add(new Product(2L, "언더아머 운동화", 30000L, 10L, 10L));
    }

    @Test
    void getProduct() {
        // given
    	when(productRepository.findById(1L)).thenReturn(product);

        // when
        ProductInfo.V1 response = getProductService.getProduct(1L);

        // then
        assertThat(response.getProductName()).isEqualTo("아디다스 운동화");
        assertThat(response.getPrice()).isEqualTo(10000L);
    }
    
    @Test
    void getProducts() {
        // given
    	when(productRepository.findProducts()).thenReturn(products);

        // when
    	List<ProductInfo.V1> response = getProductService.getProductList();

        // then
        assertThat(response).hasSize(2);
    }
}