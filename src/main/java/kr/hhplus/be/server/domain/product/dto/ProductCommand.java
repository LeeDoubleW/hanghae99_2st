package kr.hhplus.be.server.domain.product.dto;

import java.util.List;

import lombok.Getter;

public class ProductCommand {
	@Getter
    public static class Products {

        private final List<Product> products;

        private Products(List<Product> products) {
            this.products = products;
        }

        public static Products of(List<Product> list) {
            return new Products(list);
        }
    }

    @Getter
    public static class Product {

        private final Long id;
        private final Long quantity;

        private Product(Long id, Long quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public static Product of(Long id, Long quantity) {
            return new Product(id, quantity);
        }
    }
}
