package sampl.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sampl.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sampl.cafekiosk.spring.api.service.order.response.OrderResponse;
import sampl.cafekiosk.spring.domain.order.Order;
import sampl.cafekiosk.spring.domain.order.OrderRepository;
import sampl.cafekiosk.spring.domain.product.Product;
import sampl.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDate) {
        List<String> productNumbers = request.getProductNumbers();
        
        // Product
        List<Product> products = findProductsBy(productNumbers);

        // Order
        Order order = Order.create(products, registeredDate);

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }
}
