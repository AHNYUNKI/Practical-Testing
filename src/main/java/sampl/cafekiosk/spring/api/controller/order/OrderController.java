package sampl.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sampl.cafekiosk.spring.api.ApiResponse;
import sampl.cafekiosk.spring.api.controller.order.request.OrderCreateServiceRequest;
import sampl.cafekiosk.spring.api.service.order.OrderService;
import sampl.cafekiosk.spring.api.service.order.response.OrderResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateServiceRequest request) {
        LocalDateTime registeredDateTime = LocalDateTime.now();

        return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredDateTime));
    }

}
