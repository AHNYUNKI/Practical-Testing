package sampl.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sampl.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sampl.cafekiosk.spring.api.service.product.ProductService;
import sampl.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/product/new")
    public void createProduct(ProductCreateRequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }

}
