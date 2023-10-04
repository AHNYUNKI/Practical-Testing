package sampl.cafekiosk.spring.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import sampl.cafekiosk.spring.api.service.product.response.ProductResponse;
import sampl.cafekiosk.spring.domain.order.Order;
import sampl.cafekiosk.spring.domain.order.OrderStatus;
import sampl.cafekiosk.spring.domain.orderproduct.OrderProduct;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;

@Getter
public class OrderResponse {

    private Long id;
    private int totalPrice;
    private LocalDateTime registeredDateTime;

    private List<ProductResponse> product;

    @Builder
    private OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> product) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.product = product;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .product(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .collect(Collectors.toList())
                )
                .build();
    }
}
